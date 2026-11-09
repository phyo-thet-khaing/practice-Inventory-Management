import type { CreateUserRequest, UpdateUserRequest, User } from "../types/user";
import api from "./api";

export const getUsers = async (): Promise<User[]> => {
  const response = await api.get<User[]>("/api/users");

  return response.data;
};

export const getUserById = async (id: number): Promise<User> => {
  const response = await api.get<User>(`/api/users/${id}`);

  return response.data;
};

export const createUser = async (data: CreateUserRequest): Promise<User> => {
  const response = await api.post<User>("/api/users", data);

  return response.data;
};

export const updateUser = async (
  id: number,
  data: UpdateUserRequest,
): Promise<User> => {
  const response = await api.put<User>(`/api/users/${id}`, data);

  return response.data;
};

export const deleteUser = async (id: number): Promise<void> => {
  await api.delete(`/api/users/${id}`);
};
