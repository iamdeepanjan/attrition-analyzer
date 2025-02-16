import { Component, inject, OnInit } from '@angular/core';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { CurrencyPipe, DecimalPipe } from '@angular/common';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { EmployeeService } from '../../services/employee.service';
import { Chart, registerables } from 'chart.js';
import ChartDataLabels from 'chartjs-plugin-datalabels';
import { CommonService } from '../../services/common.service';

Chart.register(...registerables, ChartDataLabels);
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
  standalone: true,
  imports: [MatGridListModule, MatCardModule, DecimalPipe, CurrencyPipe],
})
export class DashboardComponent implements OnInit {
  private employeeService = inject(EmployeeService);
  private breakpointObserver = inject(BreakpointObserver);
  private commonService = inject(CommonService);

  summaryCols: number = 0;
  chartCols: number = 0;
  chartColSpan: number = 0;

  numberOfEmployees: number = 0;
  attrition: number = 0;
  attritionRate: number = 0;
  avgAge: number = 0;
  avgSalary: number = 0;
  avgYears: number = 0;
  attritionFemale: number = 0;
  attritionMale: number = 0;
  attritionOverTimeYes: number = 0;
  attritionOverTimeNo: number = 0;

  //salary
  salaries = [
    'Less than $50K',
    '$50K - $75K',
    '$75K - $100K',
    '$100K - $150K',
    'More than $150K',
  ];
  attritionBySalary: { [key: string]: number } = {};

  //educationField
  educationFields = [
    'Technical Degree',
    'Information Systems',
    'Computer Science',
    'Marketing',
    'Other',
  ];
  attritionByEducation: { [key: string]: number } = {};

  //JobRole
  jobRoles = [
    'Sales Executive',
    'Data Scientist',
    'Software Engineer',
    'Recruiter',
    'Manager',
    'Machine Learning Engineer',
  ];
  attritionByJobRole: { [key: string]: number } = {};

  //Age Group
  ageGroups = ['18-25', '26-35', '36-45', '46-55'];
  attritionByAgeGroup: { [key: string]: number } = {};

  //Year at Company
  yearsAtCompany = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  attritionByYearsAtCompany: { [key: number]: number } = {};
  constructor() {
    this.educationFields.forEach(
      (field) => (this.attritionByEducation[field] = 0)
    );
    this.jobRoles.forEach((role) => (this.attritionByJobRole[role] = 0));
    this.ageGroups.forEach((group) => (this.attritionByAgeGroup[group] = 0));
    this.salaries.forEach((salary) => (this.attritionBySalary[salary] = 0));
    this.yearsAtCompany.forEach(
      (years) => (this.attritionByYearsAtCompany[years] = 0)
    );
  }

  genderChart: any;
  salaryChart: any;
  educationChart: any;
  jobRoleChart: any;
  ageGroupChart: any;
  yearAtCompanyChart: any;
  overTimeChart: any;

  ngOnInit(): void {
    this.breakpointObserver
      .observe([Breakpoints.Handset])
      .subscribe((result) => {
        if (result.matches) {
          this.summaryCols = 6;
          this.chartCols = 1;
          this.chartColSpan = 1;
        } else {
          this.summaryCols = 12;
          this.chartCols = 3;
          this.chartColSpan = 2;
        }
      });
    this.employeeService.getEmployees().subscribe((data) => {
      this.numberOfEmployees = data.length;
      this.loadAttritionEmployeeData();
    });
    this.commonService.changeTitle("HR Analytics Dashboard");
  }

  loadAttritionEmployeeData() {
    this.employeeService.getAttritionEmployee().subscribe((data) => {
      this.attrition = data.length;
      this.attritionRate = (this.attrition / this.numberOfEmployees) * 100;
      const totalAge = data.reduce((sum, employee) => sum + employee.Age, 0);
      this.avgAge = totalAge / this.attrition;
      const totalSalary = data.reduce(
        (sum, employee) => sum + employee.Salary,
        0
      );
      this.avgSalary = totalSalary / this.attrition;
      const totalYears = data.reduce(
        (sum, employee) => sum + employee.YearsAtCompany,
        0
      );
      this.avgYears = totalYears / this.attrition;
      this.attritionFemale = data.filter(
        (employee) => employee.Gender === 'Female'
      ).length;
      this.attritionMale = data.filter(
        (employee) => employee.Gender === 'Male'
      ).length;
      this.attritionOverTimeYes = data.filter(
        (employee) => employee.OverTime === 'Yes'
      ).length;
      this.attritionOverTimeNo = data.filter(
        (employee) => employee.OverTime === 'No'
      ).length;
      this.loadGenderAttritionData();
      data.forEach((employee) => {
        const salary = employee.Salary;
        if (salary < 50000) this.attritionBySalary['Less than $50K']++;
        else if (salary >= 50000 && salary < 75000)
          this.attritionBySalary['$50K - $75K']++;
        else if (salary >= 75000 && salary < 100000)
          this.attritionBySalary['$75K - $100K']++;
        else if (salary >= 100000 && salary < 150000)
          this.attritionBySalary['$100K - $150K']++;
        else this.attritionBySalary['More than $150K']++;
      });
      this.loadSalaryAttritionData();
      data.forEach((employee) => {
        const education = employee.EducationField;
        this.attritionByEducation[education]++;
      });
      this.loadEducationAttritionData();
      data.forEach((employee) => {
        const jobRole = employee.JobRole;
        this.attritionByJobRole[jobRole]++;
      });
      this.loadJobRoleAttritionData();
      data.forEach((employee) => {
        const age = employee.Age;
        if (age >= 18 && age <= 25) this.attritionByAgeGroup['18-25']++;
        else if (age >= 26 && age <= 35) this.attritionByAgeGroup['26-35']++;
        else if (age >= 36 && age <= 45) this.attritionByAgeGroup['36-45']++;
        else if (age >= 46 && age <= 55) this.attritionByAgeGroup['46-55']++;
      });
      this.loadAgeGroupAttritionData();
      data.forEach((employee) => {
        const years = employee.YearsAtCompany;
        this.attritionByYearsAtCompany[years]++;
      });
      this.loadYearAtCompanyAttritionData();
      this.loadOvetimeAttritionData();
    });
  }

  loadGenderAttritionData() {
    this.genderChart = new Chart('attritionByGender', {
      type: 'pie',
      data: {
        labels: ['Female', 'Male'],
        datasets: [
          {
            label: 'Count of Employees',
            data: [this.attritionFemale, this.attritionMale],
            backgroundColor: ['rgba(255, 99, 132)', 'rgba(54, 162, 235)'],
            hoverOffset: 4,
          },
        ],
      },
      options: {
        plugins: {
          datalabels: {
            display: true,
            color: 'white',
            align: 'center',
            offset: 5,
          },
        },
      },
    });
  }

  loadSalaryAttritionData() {
    this.salaryChart = new Chart('attritionBySalary', {
      type: 'bar',
      data: {
        labels: this.salaries,
        datasets: [
          {
            label: 'Count of Employees',
            data: Object.values(this.attritionBySalary),
            backgroundColor: [
              'rgba(255, 159, 64)',
              'rgba(54, 162, 235)',
              'rgba(255, 99, 132)',
              'rgba(75, 192, 192)',
              'rgba(153, 102, 255)',
            ],
          },
        ],
      },
      options: {
        indexAxis: 'y',
        layout: {
          padding: { right: 20 },
        },
        plugins: {
          legend: {
            display: false,
          },
          datalabels: {
            display: true,
            align: 'right',
            anchor: 'end',
            offset: 5,
          },
        },
        scales: {
          x: {
            display: false,
          },
          y: {
            grid: {
              display: false,
            },
            border: {
              display: false,
            },
          },
        },
      },
    });
  }

  loadEducationAttritionData() {
    this.educationChart = new Chart('attritionByEducation', {
      type: 'doughnut',
      data: {
        labels: this.educationFields,
        datasets: [
          {
            label: 'Count of Employees',
            data: Object.values(this.attritionByEducation),
            backgroundColor: [
              'rgba(255, 99, 132)',
              'rgba(54, 162, 235)',
              'rgba(255, 206, 86)',
              'rgba(75, 192, 192)',
              'rgba(153, 102, 255)',
            ],
            hoverOffset: 4,
          },
        ],
      },
      options: {
        plugins: {
          datalabels: {
            color: 'white',
            display: function (context) {
              var dataset = context.dataset;
              var count = dataset.data.length;
              var value = dataset.data[context.dataIndex];
              return typeof value === 'number' && value > count * 1.5;
            },
          },
        },
      },
    });
  }

  loadJobRoleAttritionData() {
    this.jobRoleChart = new Chart('attritionByJobRole', {
      type: 'doughnut',
      data: {
        labels: this.jobRoles,
        datasets: [
          {
            label: 'Count of Employees',
            data: Object.values(this.attritionByJobRole),
            backgroundColor: [
              'rgba(255, 99, 132)',
              'rgba(54, 162, 235)',
              'rgba(255, 206, 86)',
              'rgba(75, 192, 192)',
              'rgba(153, 102, 255)',
              'rgba(255, 159, 64)',
            ],
            hoverOffset: 4,
          },
        ],
      },
      options: {
        plugins: {
          datalabels: {
            color: 'white',
            display: function (context) {
              var dataset = context.dataset;
              var count = dataset.data.length;
              var value = dataset.data[context.dataIndex];
              return typeof value === 'number' && value > count * 1.5;
            },
          },
        },
      },
    });
  }

  loadAgeGroupAttritionData() {
    this.ageGroupChart = new Chart('attritionByAgeGroup', {
      type: 'bar',
      data: {
        labels: this.ageGroups,
        datasets: [
          {
            label: 'Count of Employees',
            data: Object.values(this.attritionByAgeGroup),
            backgroundColor: [
              'rgba(255, 99, 132)',
              'rgba(54, 162, 235)',
              'rgba(255, 206, 86)',
              'rgba(75, 192, 192)',
            ],
            barThickness: 50,
          },
        ],
      },
      options: {
        layout: {
          padding: { top: 20 },
        },
        plugins: {
          legend: {
            display: false,
          },
          datalabels: {
            display: true,
            align: 'end',
            anchor: 'end',
            offset: 5,
          },
        },
        scales: {
          x: {
            grid: {
              display: false,
            },
            border: {
              display: false,
            },
          },
          y: {
            display: false,
          },
        },
      },
    });
  }

  loadYearAtCompanyAttritionData() {
    this.yearAtCompanyChart = new Chart('attritionByYearsAtCompany', {
      type: 'line',
      data: {
        labels: this.yearsAtCompany,
        datasets: [
          {
            label: 'Count of Employees',
            data: Object.values(this.attritionByYearsAtCompany),
            backgroundColor: 'rgba(255, 159, 64, 0.2)',
            borderColor: 'rgba(255, 159, 64)',
            borderWidth: 1,
            pointRadius: 0,
            tension: 0.1,
            fill: 'origin',
          },
        ],
      },
      options: {
        plugins: {
          legend: {
            display: false,
          },
          datalabels: {
            display: true,
            color: 'rgba(255, 159, 64)',
            align: 'top',
            offset: 5,
          },
        },
        scales: {
          x: {
            grid: {
              display: false,
            },
            border: {
              display: false,
            },
            ticks: {
              stepSize: 5,
              callback: function (value) {
                if (value === 0 || value === 5 || value === 10) {
                  return value;
                }
                return '';
              },
            },
          },
          y: {
            display: false,
          },
        },
      },
    });
  }

  loadOvetimeAttritionData() {
    this.overTimeChart = new Chart('attriotionByOverTime', {
      type: 'pie',
      data: {
        labels: ['Yes', 'No'],
        datasets: [
          {
            label: 'Count of Employees',
            data: [this.attritionOverTimeYes, this.attritionOverTimeNo],
            backgroundColor: ['rgba(75, 192, 192)', 'rgba(255, 99, 132)'],
            hoverOffset: 4,
          },
        ],
      },
      options: {
        plugins: {
          datalabels: {
            display: true,
            color: 'white',
            align: 'center',
            offset: 5,
          },
        },
      },
    });
  }
}
