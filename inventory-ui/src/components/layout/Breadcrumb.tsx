import { ChevronLeft, ChevronRight, LayoutDashboard } from "lucide-react";
import { Link, useNavigate } from "react-router-dom";

interface BreadcrumbLink {
  title: string;
  path: string;
}

interface BreadcrumbProps {
  currentPageTitle: string;
  links?: BreadcrumbLink[];
}

const Breadcrumb = ({ currentPageTitle, links = [] }: BreadcrumbProps) => {
  const nav = useNavigate();

  return (
    <div className="mb-8 flex w-full items-center justify-between gap-3 border-b border-gray-200 py-4">
      {/* {Breadcrumb Navigation} */}
      <nav aria-label="Breadcrumb">
        <ol className="flex flex-wrap items-center gap-1 sm:gap-2">
          <li className="flex items-center">
            <Link
              to="/dashboard"
              className="inline-flex items-center gap-1 text-sm font-medium text-gray-600 transition-colors hover:text-gray-800"
            >
              <LayoutDashboard size={16} />
              Dashboard
            </Link>
          </li>
          {/* Previous Links */}
          {links.map((link) => (
            <li key={link.path} className="flex items-center gap-4">
              <ChevronRight size={16} className="text-gray-400" />

              <Link
                to={link.path}
                className="text-sm font-medium text-gray-600 transition-colors hover:text-gray-800"
              >
                {link.title}
              </Link>
            </li>
          ))}

          {/* Current Page Title */}
          <li aria-current="page" className="flex items-center gap-1">
            <ChevronRight size={16} className="text-gray-400" />

            <span className="text-sm font-medium text-gray-500">
              {currentPageTitle}
            </span>
          </li>
        </ol>
      </nav>

      {/* {back forward buttons} */}
      <div className="flex items-center">
        <button
          type="button"
          onClick={() => nav(-1)}
          aria-label="Go back"
          className="flex size-8 items-center justify-center border border-gray-200 bg-white text-gray-600 transition-colors hover:bg-gray-100 hover:text-gray-800"
        >
          <ChevronLeft size={16} />
        </button>

        <button
          type="button"
          onClick={() => nav(1)}
          aria-label="Go forward"
          className="flex size-8 items-center justify-center border border-gray-200 bg-white text-gray-600 transition-colors hover:bg-gray-100 hover:text-gray-800"
        >
          <ChevronRight size={16} />
        </button>
      </div>
    </div>
  );
};

export default Breadcrumb;
