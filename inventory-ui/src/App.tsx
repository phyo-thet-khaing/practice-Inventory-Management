import { RouterProvider } from "react-router-dom";
import { Toaster } from "sonner";
import router from "./routes/AppRoutes";
const App = () => {
  return (
    <>
      <Toaster position="top-right" />
      <RouterProvider router={router} />
    </>
  );
};

export default App;
