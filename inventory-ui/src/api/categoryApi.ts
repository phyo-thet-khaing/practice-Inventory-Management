import axios from "axios";
import type { Category, CategoryRequest } from "../types/category";

const categoryApi = axios.create({
  baseURL: `${import.meta.env.VITE_API_BASE_URL}/api/categories`,
});

export const getCategories = async (): Promise<Category[]> => {
  const response = await categoryApi.get<Category[]>("");
  return response.data;
};

export const getCategoryById = async (id: number): Promise<Category> => {
  const response = await categoryApi.get<Category>(`/${id}`);
  return response.data;
};

export const createCategory = async (
  data: CategoryRequest,
): Promise<Category> => {
  const response = await categoryApi.post<Category>("", data);
  return response.data;
};

export const updateCategory = async (
  id: number,
  data: CategoryRequest,
): Promise<Category> => {
  const response = await categoryApi.put<Category>(`/${id}`, data);
  return response.data;
};

export const deleteCategory = async (id: number): Promise<void> => {
  await categoryApi.delete(`/${id}`);
};
