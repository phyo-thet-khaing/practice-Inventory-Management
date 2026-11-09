"use client";

import { useState } from "react";

import useCategories from "../hooks/useCategories";
import useCreateCategory from "../hooks/useCreateCategory";
import useUpdateCategory from "../hooks/useUpdateCategory";

import CategoryDrawer from "./CategoryDrawer";
import CategoryHeader from "./CategoryHeader";
import { CategoryTable } from "./CategoryTable";

import { toast } from "sonner";
import { deleteCategory } from "../../../api/categoryApi";
import type { Category, CategoryRequest } from "../../../types/category";

const CategorySection = () => {
  // Fetch categories
  const { categories, isLoading, error, refreshCategories } = useCategories();

  // Create and update hooks
  const { mutate: create, isCreating } = useCreateCategory();
  const { mutate: update, isUpdating } = useUpdateCategory();

  // Drawer state
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);
  const [selectedCategory, setSelectedCategory] = useState<Category | null>(
    null,
  );

  // Form state
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [formError, setFormError] = useState("");

  const isSaving = isCreating || isUpdating;

  // Open drawer for adding a category
  const handleAddCategory = () => {
    setSelectedCategory(null);
    setName("");
    setDescription("");
    setFormError("");
    setIsDrawerOpen(true);
  };

  // Open drawer for editing a category
  const handleEditCategory = (category: Category) => {
    setSelectedCategory(category);
    setName(category.name);
    setDescription(category.description ?? "");
    setFormError("");
    setIsDrawerOpen(true);
  };

  // Reset form and close drawer
  const resetForm = () => {
    setIsDrawerOpen(false);
    setSelectedCategory(null);
    setName("");
    setDescription("");
    setFormError("");
  };

  const handleClose = () => {
    if (!isSaving) {
      resetForm();
    }
  };

  // Create or update category
  const handleSave = async () => {
    const trimmedName = name.trim();

    if (!trimmedName) {
      setFormError("Category name is required.");
      return;
    }

    const payload: CategoryRequest = {
      name: trimmedName,
      description: description.trim(),
    };

    setFormError("");

    try {
      if (selectedCategory) {
        await update(Number(selectedCategory.id), payload);
      } else {
        await create(payload);
      }

      await refreshCategories();
      resetForm();
    } catch (err) {
      console.error("Failed to save category:", err);

      setFormError(
        err instanceof Error
          ? err.message
          : "Failed to save category. Please try again.",
      );
    }
  };

  const handleDeleteCategory = async (id: number) => {
    try {
      await deleteCategory(id);
      await refreshCategories();

      toast.success("Category deleted successfully!", {
        style: {
          background: "#16a34a",
          color: "#ffffff",
          border: "1px solid #15803d",
        },
      });
    } catch (error) {
      console.error("Failed to delete category:", error);

      toast.error("Failed to delete category!", {
        style: {
          background: "#dc2626",
          color: "#ffffff",
          border: "1px solid #b91c1c",
        },
      });

      throw error;
    }
  };

  return (
    <div className="flex-1 space-y-6 bg-[#f8f9fa] p-6 sm:p-8">
      {/* Header */}
      <CategoryHeader onAddCategory={handleAddCategory} />

      {/* Category table */}
      {isLoading ? (
        <div className="py-10 text-center text-gray-500">
          Loading categories...
        </div>
      ) : error ? (
        <div className="py-10 text-center text-red-500">
          Failed to load categories. Please try again.
        </div>
      ) : (
        <CategoryTable
          categories={categories}
          onEditCategory={handleEditCategory}
          onDeleteCategory={handleDeleteCategory}
        />
      )}

      {/* Add / Edit drawer */}
      <CategoryDrawer
        isOpen={isDrawerOpen}
        isEditing={selectedCategory !== null}
        name={name}
        description={description}
        isSaving={isSaving}
        formError={formError}
        onNameChange={setName}
        onDescriptionChange={setDescription}
        onClose={handleClose}
        onSave={() => void handleSave()}
      />
    </div>
  );
};

export default CategorySection;
