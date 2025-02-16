export class LoginResponse {
  userId!: number;
  user!: User;
  token!: string;
}

interface User {
  userId: number;
  name: string;
  username: string;
}
