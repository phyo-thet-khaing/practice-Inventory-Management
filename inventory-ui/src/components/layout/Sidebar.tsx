import {
  BarChart3,
  Boxes,
  Grid2X2,
  LayoutDashboard,
  MapPin,
  Package,
  Settings,
  ShoppingCart,
  Truck,
  Users,
  Warehouse,
  X,
} from "lucide-react";
import { NavLink } from "react-router-dom";

interface SidebarProps {
  isOpen: boolean;
  onClose: () => void;
}

const navigation = [
  {
    title: "Dashboard",
    path: "/dashboard",
    icon: LayoutDashboard,
  },
  {
    title: "Users",
    path: "/users",
    icon: Users,
  },
  {
    title: "Products",
    path: "/products",
    icon: Package,
  },
  {
    title: "Categories",
    path: "/categories",
    icon: Grid2X2,
  },
  {
    title: "Suppliers",
    path: "/suppliers",
    icon: Truck,
  },
  {
    title: "Locations",
    path: "/locations",
    icon: MapPin,
  },
  {
    title: "Purchase Orders",
    path: "/purchase-orders",
    icon: ShoppingCart,
  },
  {
    title: "Inventory",
    path: "/inventory",
    icon: Boxes,
  },
  {
    title: "Reports",
    path: "/reports",
    icon: BarChart3,
  },
];

const Sidebar = ({ isOpen, onClose }: SidebarProps) => {
  return (
    <>
      {/* Mobile Overlay */}
      {isOpen && (
        <div
          className="fixed inset-0 z-40 bg-black/40 lg:hidden"
          onClick={onClose}
        />
      )}

      <aside
        className={`fixed inset-y-0 left-0 z-50 flex w-64 flex-col
          border-r border-gray-200 bg-white transition-transform duration-300
          ${isOpen ? "translate-x-0" : "-translate-x-full"}
          lg:translate-x-0`}
      >
        {/* Logo */}
        <div className="flex h-16 items-center justify-between border-b border-gray-200 px-5">
          <NavLink
            to="/dashboard"
            onClick={onClose}
            className="flex items-center gap-3"
          >
            <div className="flex h-9 w-9 items-center justify-center rounded-lg bg-black text-white">
              <Warehouse size={21} />
            </div>

            <div>
              <h1 className="text-base font-bold text-gray-900">Easy Stock</h1>
              <p className="text-xs text-gray-500">Cloud Native IMS</p>
            </div>
          </NavLink>

          <button
            type="button"
            onClick={onClose}
            className="rounded-md p-1 text-gray-500 hover:bg-gray-100 lg:hidden"
            aria-label="Close sidebar"
          >
            <X size={20} />
          </button>
        </div>

        {/* Navigation */}
        <nav className="flex-1 space-y-1 overflow-y-auto px-3 py-5">
          <p className="mb-3 px-3 text-xs font-semibold uppercase tracking-wider text-gray-400">
            Main Menu
          </p>

          {navigation.map((item) => {
            const Icon = item.icon;

            return (
              <NavLink
                key={item.path}
                to={item.path}
                onClick={onClose}
                className={({ isActive }) =>
                  `flex items-center gap-3 rounded-lg px-3 py-2.5 text-sm font-medium transition-colors ${
                    isActive
                      ? "bg-gray-900 text-white"
                      : "text-gray-600 hover:bg-gray-100 hover:text-gray-900"
                  }`
                }
              >
                <Icon size={19} strokeWidth={1.8} />
                <span>{item.title}</span>
              </NavLink>
            );
          })}
        </nav>

        {/* Settings */}
        <div className="border-t border-gray-200 p-3">
          <NavLink
            to="/settings"
            onClick={onClose}
            className={({ isActive }) =>
              `flex items-center gap-3 rounded-lg px-3 py-2.5 text-sm font-medium ${
                isActive
                  ? "bg-gray-900 text-white"
                  : "text-gray-600 hover:bg-gray-100 hover:text-gray-900"
              }`
            }
          >
            <Settings size={19} strokeWidth={1.8} />
            <span>Settings</span>
          </NavLink>

          <div className="mt-3 flex items-center gap-3 rounded-lg bg-gray-50 p-3">
            <div className="flex h-9 w-9 items-center justify-center rounded-full bg-gray-200 text-sm font-semibold text-gray-700">
              A
            </div>

            <div className="min-w-0">
              <p className="truncate text-sm font-semibold text-gray-900">
                Administrator
              </p>
              <p className="truncate text-xs text-gray-500">Admin Account</p>
            </div>
          </div>
        </div>
      </aside>
    </>
  );
};

export default Sidebar;
