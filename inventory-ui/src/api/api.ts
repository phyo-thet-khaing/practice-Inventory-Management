import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },

  // HttpOnly Cookie ကို request နဲ့အတူ ပို့/လက်ခံရန်
  withCredentials: true,
});

export default api;
