import { createBrowserRouter, Navigate } from "react-router-dom";

import LoginPage from "../modules/auth/pages/LoginPage";
import CategoryPage from "../modules/category/pages/CategoryPage";
import DashboardPage from "../modules/dashboard/pages/DashboardPage";

import DashboardLayout from "../layouts/DashboardLayout";
import ProductPage from "../modules/products/pages/ProductPage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Navigate to="/login" replace />,
  },
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    element: <DashboardLayout />,
    children: [
      {
        path: "/dashboard",
        element: <DashboardPage />,
      },
      {
        path: "/categories",
        element: <CategoryPage />,
      },
      {
        path: "/products",
        element: <ProductPage />,
      },
    ],
  },
]);

export default router;
