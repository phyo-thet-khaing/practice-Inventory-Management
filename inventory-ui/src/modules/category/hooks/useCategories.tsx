"use client";
import useSWR from "swr";
import { getCategories } from "../../../api/categoryApi";
import type { Category } from "../../../types/category";

const fetcher = () => getCategories();
const useCategories = () => {
  const { data, error, isLoading, mutate } = useSWR<Category[]>(
    "/categories",
    fetcher,
  );

  const refreshCategories = () => mutate();
  return {
    categories: data ?? [],
    isLoading,
    error,
    refreshCategories,
  };
};

export default useCategories;
