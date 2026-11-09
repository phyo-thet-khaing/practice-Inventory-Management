"use client";
import { toast } from "sonner";
type CategoryDrawerProps = {
  isOpen: boolean;
  isEditing: boolean;
  name: string;
  description: string;
  isSaving: boolean;
  formError: string;
  onNameChange: (value: string) => void;
  onDescriptionChange: (value: string) => void;
  onClose: () => void;
  onSave: () => void;
};

const CategoryDrawer = ({
  isOpen,
  isEditing,
  name,
  description,
  isSaving,
  formError,
  onNameChange,
  onDescriptionChange,
  onClose,
  onSave,
}: CategoryDrawerProps) => {
  if (!isOpen) return null;

  return (
    <div
      className="fixed inset-0 z-50 bg-black/50"
      onMouseDown={(e) => {
        if (e.target === e.currentTarget) onClose();
      }}
    >
      <div className="absolute inset-y-0 right-0 flex w-full max-w-md flex-col bg-white shadow-xl">
        <div className="flex items-center justify-between border-b p-6">
          <h2 className="text-lg font-bold text-gray-900">
            {isEditing ? "Edit Category" : "Add New Category"}
          </h2>

          <button
            type="button"
            onClick={onClose}
            disabled={isSaving}
            aria-label="Close drawer"
            className="text-xl text-gray-500 hover:text-gray-900 disabled:opacity-50"
          >
            &times;
          </button>
        </div>

        <form
          className="flex flex-1 flex-col"
          onSubmit={async (e) => {
            e.preventDefault();

            try {
              await onSave();

              toast.success(
                isEditing
                  ? "Category updated successfully!"
                  : "Category created successfully!",
                {
                  style: {
                    background: "#16a34a",
                    color: "#ffffff",
                    border: "1px solid #15803d",
                  },
                },
              );
            } catch {
              toast.error("Failed to save category.", {
                style: {
                  background: "#dc2626",
                  color: "#ffffff",
                  border: "1px solid #b91c1c",
                },
              });
            }
          }}
        >
          <div className="flex-1 space-y-5 p-6">
            <div>
              <label
                htmlFor="category-name"
                className="block text-sm font-medium text-gray-700"
              >
                Category Name
              </label>

              <input
                id="category-name"
                value={name}
                onChange={(e) => onNameChange(e.target.value)}
                placeholder="e.g. Networking Equipment"
                required
                disabled={isSaving}
                className="mt-1 w-full rounded-md border border-gray-300 p-2.5 text-sm outline-none focus:border-gray-500"
              />
            </div>

            <div>
              <label
                htmlFor="category-description"
                className="block text-sm font-medium text-gray-700"
              >
                Description
              </label>

              <textarea
                id="category-description"
                value={description}
                onChange={(e) => onDescriptionChange(e.target.value)}
                placeholder="Enter category description"
                rows={4}
                disabled={isSaving}
                className="mt-1 w-full rounded-md border border-gray-300 p-2.5 text-sm outline-none focus:border-gray-500"
              />
            </div>

            {formError && (
              <p role="alert" className="text-sm text-red-600">
                {formError}
              </p>
            )}
          </div>

          <div className="flex justify-end gap-3 border-t p-6">
            <button
              type="button"
              onClick={onClose}
              disabled={isSaving}
              className="rounded-lg border border-gray-300 px-4 py-2 text-sm hover:bg-gray-50 disabled:opacity-50"
            >
              Cancel
            </button>

            <button
              type="submit"
              disabled={isSaving}
              className="rounded-lg bg-black px-4 py-2 text-sm font-semibold text-white hover:bg-gray-800 disabled:cursor-not-allowed disabled:opacity-50"
            >
              {isSaving
                ? "Saving..."
                : isEditing
                  ? "Update Category"
                  : "Save Category"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CategoryDrawer;
