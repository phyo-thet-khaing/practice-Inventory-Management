import type { Role } from "../types/role";
import api from "./api";

export const getRoles = async (): Promise<Role[]> => {
  const response = await api.get<Role[]>("/api/roles");

  return response.data;
};

export const getRoleById = async (id: number): Promise<Role> => {
  const response = await api.get<Role>(`/api/roles/${id}`);

  return response.data;
};
 