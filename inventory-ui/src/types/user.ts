import type { Role } from "./role";

export interface User {
  id: number;
  username: string;
  email: string;
  phone: string;
  status: "ACTIVE" | "INACTIVE";
  mustChangePassword: boolean;
  lastLoginAt: string | null;
  createdAt: string;
  role: Role;
}

export interface CreateUserRequest {
  username: string;
  email: string;
  password: string;
  phone?: string;
  roleId?: number;
}

export interface UpdateUserRequest {
  username?: string;
  email?: string;
  password?: string;
  phone?: string;
  roleId?: number;
}
