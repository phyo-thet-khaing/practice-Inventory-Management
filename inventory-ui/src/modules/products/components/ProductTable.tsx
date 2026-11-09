import type { Product } from "../../../types/product";

interface ProductTableProps {
  products: Product[];
}

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export default function ProductTable({ products }: ProductTableProps) {
  return (
    <div className="w-full overflow-x-auto">
      <table className="w-full min-w-[700px] border-collapse text-left">
        <thead>
          <tr className="border-b border-gray-200 bg-gray-50">
            <th className="px-4 py-3 text-xs font-semibold uppercase tracking-wide text-gray-500">
              ID
            </th>
            <th className="px-4 py-3 text-xs font-semibold uppercase tracking-wide text-gray-500">
              Product
            </th>
            <th className="px-4 py-3 text-xs font-semibold uppercase tracking-wide text-gray-500">
              Category ID
            </th>
            <th className="px-4 py-3 text-xs font-semibold uppercase tracking-wide text-gray-500">
              Price
            </th>
            <th className="px-4 py-3 text-xs font-semibold uppercase tracking-wide text-gray-500">
              Status
            </th>
            <th className="px-4 py-3 text-right text-xs font-semibold uppercase tracking-wide text-gray-500">
              Actions
            </th>
          </tr>
        </thead>

        <tbody className="divide-y divide-gray-100">
          {products.map((product) => {
            const imageSrc = product.imageUrl
              ? product.imageUrl.startsWith("http")
                ? product.imageUrl
                : `${API_BASE_URL?.replace(/\/$/, "")}/${product.imageUrl.replace(/^\//, "")}`
              : null;

            return (
              <tr
                key={product.id}
                className="transition-colors hover:bg-gray-50"
              >
                <td className="whitespace-nowrap px-4 py-4 text-sm text-gray-500">
                  #{product.id}
                </td>

                <td className="px-4 py-4">
                  <div className="flex items-center gap-3">
                    {imageSrc ? (
                      <img
                        src={imageSrc}
                        alt={product.name}
                        className="h-10 w-10 rounded-lg border border-gray-200 object-cover"
                        onError={(event) => {
                          console.error(
                            "Failed to load product image:",
                            event.currentTarget.src,
                          );
                        }}
                      />
                    ) : (
                      <div className="flex h-10 w-10 items-center justify-center rounded-lg bg-blue-50 text-blue-600">
                        <span className="text-lg font-semibold">
                          {product.name?.charAt(0).toUpperCase() || "P"}
                        </span>
                      </div>
                    )}

                    <div>
                      <p className="font-medium text-gray-900">
                        {product.name}
                      </p>
                      <p className="text-xs text-gray-500">
                        {product.unit || "No unit"}
                      </p>
                    </div>
                  </div>
                </td>

                <td className="px-4 py-4 text-sm text-gray-600">
                  {product.categoryId ?? "-"}
                </td>

                <td className="whitespace-nowrap px-4 py-4 text-sm font-medium text-gray-900">
                  {product.price != null
                    ? Number(product.price).toLocaleString()
                    : "-"}
                </td>

                <td className="px-4 py-4">
                  <span
                    className={`inline-flex rounded-full px-2.5 py-1 text-xs font-medium ${
                      product.status === "Active"
                        ? "bg-green-50 text-green-700"
                        : "bg-gray-100 text-gray-600"
                    }`}
                  >
                    {product.status || "Unknown"}
                  </span>
                </td>

                <td className="px-4 py-4 text-right">
                  <div className="flex justify-end gap-2">
                    <button
                      type="button"
                      onClick={() => console.log("View product:", product.id)}
                      className="rounded-md px-3 py-1.5 text-sm font-medium text-blue-600 hover:bg-blue-50"
                    >
                      View
                    </button>

                    <button
                      type="button"
                      onClick={() => console.log("Edit product:", product.id)}
                      className="rounded-md px-3 py-1.5 text-sm font-medium text-gray-600 hover:bg-gray-100"
                    >
                      Edit
                    </button>
                  </div>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}
