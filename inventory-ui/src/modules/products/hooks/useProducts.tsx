"use client";

import useSWR from "swr";
import { getProducts } from "../../../api/productApi";
import type { Product } from "../../../types/product";

const fetcher = () => getProducts();

const useProducts = () => {
  const { data, error, isLoading, mutate } = useSWR<Product[]>(
    "/products",
    fetcher,
  );

  const refreshProducts = () => mutate();

  return {
    products: data ?? [],
    isLoading,
    error,
    refreshProducts,
  };
};

export default useProducts;
