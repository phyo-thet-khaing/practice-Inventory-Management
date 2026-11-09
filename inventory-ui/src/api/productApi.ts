import axios from "axios";
import type { Product, ProductRequest } from "../types/product";

const productApi = axios.create({
  baseURL: `${import.meta.env.VITE_API_BASE_URL}/api/products`,
});

export const getProducts = async (): Promise<Product[]> => {
  const response = await productApi.get<Product[]>("");
  return response.data;
};

export const getProductById = async (id: number): Promise<Product> => {
  const response = await productApi.get<Product>(`/${id}`);
  return response.data;
};

export const createProduct = async (data: ProductRequest): Promise<Product> => {
  const formData = new FormData();

  formData.append("categoryId", String(data.categoryId));
  formData.append("supplierId", String(data.supplierId));
  formData.append("name", data.name);

  if (data.unit != null) {
    formData.append("unit", data.unit);
  }

  if (data.price != null) {
    formData.append("price", String(data.price));
  }

  if (data.status) {
    formData.append("status", data.status);
  }

  if (data.image) {
    formData.append("image", data.image);
  }

  const response = await productApi.post<Product>("", formData);
  return response.data;
};

export const updateProduct = async (
  id: number,
  data: ProductRequest,
): Promise<Product> => {
  const formData = new FormData();

  formData.append("categoryId", String(data.categoryId));
  formData.append("supplierId", String(data.supplierId));
  formData.append("name", data.name);

  if (data.unit != null) {
    formData.append("unit", data.unit);
  }

  if (data.price != null) {
    formData.append("price", String(data.price));
  }

  if (data.status) {
    formData.append("status", data.status);
  }

  if (data.image) {
    formData.append("image", data.image);
  }

  const response = await productApi.put<Product>(`/${id}`, formData);
  return response.data;
};

export const deleteProduct = async (id: number): Promise<void> => {
  await productApi.delete(`/${id}`);
};
