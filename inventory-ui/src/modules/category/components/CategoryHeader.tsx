import { Plus } from "lucide-react";
import Breadcrumb from "../../../components/layout/Breadcrumb";

interface CategoryHeaderProps {
  onAddCategory: () => void;
}

const CategoryHeader = ({ onAddCategory }: CategoryHeaderProps) => {
  return (
    <div className="flex flex-col gap-4 border-b border-gray-200 pb-6 sm:flex-row sm:items-center sm:justify-between">
      {/* Page Title */}
      <div className="min-w-0">
        <h1 className="text-xl font-bold tracking-tight text-gray-900 sm:text-2xl">
          Category Management
        </h1>

        {/* Breadcrumb */}
        <Breadcrumb currentPageTitle="Category Management" />
      </div>

      {/* Add Category Button */}
      <button
        type="button"
        onClick={onAddCategory}
        className="inline-flex w-full shrink-0 items-center justify-center gap-2 rounded-lg bg-black px-4 py-2.5 text-sm font-semibold text-white shadow-sm transition-colors hover:bg-gray-800 active:scale-[0.98] sm:w-auto"
      >
        <Plus size={16} strokeWidth={2} />
        <span>Add Category</span>
      </button>
    </div>
  );
};

export default CategoryHeader;
