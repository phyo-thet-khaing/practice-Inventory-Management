const DashboardSection = () => {
  return (
    <section className="w-full min-w-0 px-2 py-2 sm:px-0 sm:py-4">
      {/* Dashboard Header */}
      <div className="mb-6 min-w-0">
        <h1 className="break-words text-2xl font-bold text-gray-900 sm:text-3xl">
          Dashboard
        </h1>

        <p className="mt-2 break-words text-sm leading-6 text-gray-500 sm:text-base">
          Welcome to EasyStock Inventory Management System.
        </p>
      </div>

      {/* Dashboard content goes here */}
    </section>
  );
};

export default DashboardSection;
