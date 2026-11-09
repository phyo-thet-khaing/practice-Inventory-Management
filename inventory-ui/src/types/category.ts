export interface Category {
  id: number;
  name: string;
  description: string;
  createdAt: string;
  updateAt: string;
}

export interface CategoryRequest {
  name: string;
  description: string;
}
