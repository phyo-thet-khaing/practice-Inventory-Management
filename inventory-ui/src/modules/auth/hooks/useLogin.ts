import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { login } from "../../../api/authApi";

export const useLogin = () => {
  const navigate = useNavigate();

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleLogin = async (username: string, password: string) => {
    try {
      setLoading(true);
      setError("");

      const data = await login({
        username,
        password,
      });

      localStorage.setItem("token", data.token);
      localStorage.setItem("username", data.username);
      localStorage.setItem("roles", JSON.stringify(data.roles));

      navigate("/dashboard");
    } catch (error) {
      setLoading(false);
      setError("Login failed");
    } finally {
      setLoading(false);
    }
  };

  return {
    handleLogin,
    loading,
    error,
  };
};
