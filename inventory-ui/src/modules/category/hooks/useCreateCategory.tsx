"use client";

import { useState } from "react";
import { createCategory } from "../../../api/categoryApi";
import type { Category, CategoryRequest } from "../../../types/category";

const useCreateCategory = () => {
  const [isCreating, setIsCreating] = useState(false);

  const mutate = async (data: CategoryRequest): Promise<Category> => {
    setIsCreating(true);

    try {
      return await createCategory(data);
    } finally {
      setIsCreating(false);
    }
  };

  return { mutate, isCreating };
};

export default useCreateCategory;
