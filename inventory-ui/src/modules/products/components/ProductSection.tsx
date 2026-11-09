import useProducts from "../hooks/useProducts";
import ProductTable from "./ProductTable";

export default function ProductSection() {
  const { products, isLoading, error, refreshProducts } = useProducts();

  if (isLoading) {
    return (
      <section className="p-6">
        <p className="text-sm text-gray-500">Loading products...</p>
      </section>
    );
  }

  if (error) {
    return (
      <section className="p-6">
        <div className="rounded-lg border border-red-200 bg-red-50 p-4">
          <p className="text-sm text-red-600">Failed to load products.</p>

          <button
            type="button"
            onClick={() => refreshProducts()}
            className="mt-3 rounded-lg bg-red-600 px-4 py-2 text-sm text-white hover:bg-red-700"
          >
            Retry
          </button>
        </div>
      </section>
    );
  }

  return (
    <section className="space-y-6 p-6">
      {/* Page Header */}
      <div className="flex flex-col justify-between gap-4 sm:flex-row sm:items-center">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">Products</h1>
          <p className="mt-1 text-sm text-gray-500">
            Manage your inventory products.
          </p>
        </div>

        <button
          type="button"
          onClick={() => refreshProducts()}
          className="rounded-lg border border-gray-300 bg-white px-4 py-2 text-sm font-medium text-gray-700 transition hover:bg-gray-50"
        >
          <span className="mr-2">↻</span>
          Refresh
        </button>
      </div>

      {/* Product Summary */}
      <div className="grid grid-cols-1 gap-4 sm:grid-cols-2">
        <div className="rounded-xl border border-gray-200 bg-white p-5 shadow-sm">
          <p className="text-sm font-medium text-gray-500">Total Products</p>
          <p className="mt-2 text-3xl font-bold text-gray-900">
            {products.length}
          </p>
        </div>

        <div className="rounded-xl border border-gray-200 bg-white p-5 shadow-sm">
          <p className="text-sm font-medium text-gray-500">Active Products</p>
          <p className="mt-2 text-3xl font-bold text-green-600">
            {products.filter((product) => product.status === "Active").length}
          </p>
        </div>
      </div>

      {/* Product Table */}
      <div className="overflow-hidden rounded-xl border border-gray-200 bg-white shadow-sm">
        <div className="flex flex-col justify-between gap-2 border-b border-gray-200 px-5 py-4 sm:flex-row sm:items-center">
          <div>
            <h2 className="text-lg font-semibold text-gray-900">
              Product List
            </h2>
            <p className="mt-1 text-sm text-gray-500">
              View all products in your inventory.
            </p>
          </div>

          <span className="text-sm text-gray-500">
            {products.length} product(s)
          </span>
        </div>

        {products.length === 0 ? (
          <div className="px-5 py-12 text-center">
            <p className="font-medium text-gray-700">No products found</p>
            <p className="mt-1 text-sm text-gray-500">
              Products will appear here when available.
            </p>
          </div>
        ) : (
          <ProductTable products={products} />
        )}
      </div>
    </section>
  );
}
