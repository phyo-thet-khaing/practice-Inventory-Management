"use client";

import { useState } from "react";
import { updateCategory } from "../../../api/categoryApi";
import type { Category, CategoryRequest } from "../../../types/category";

const useUpdateCategory = () => {
  const [isUpdating, setIsUpdating] = useState(false);

  const mutate = async (
    id: number,
    data: CategoryRequest,
  ): Promise<Category> => {
    setIsUpdating(true);

    try {
      return await updateCategory(id, data);
    } finally {
      setIsUpdating(false);
    }
  };

  return { mutate, isUpdating };
};

export default useUpdateCategory;
