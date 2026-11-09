export type ProductStatus = "Active" | "Inactive";

export interface Product {
  id: number;
  categoryId: number;
  supplierId: number;
  name: string;
  unit: string | null;
  price: string | null;
  imageId: number | null;
  imageUrl: string | null;
  status: ProductStatus;
  createdAt: string;
  updatedAt: string;
}

export interface ProductRequest {
  categoryId: number;
  supplierId: number;
  name: string;
  unit?: string;
  price?: string;
  status?: string;
  image?: File | null;
}
