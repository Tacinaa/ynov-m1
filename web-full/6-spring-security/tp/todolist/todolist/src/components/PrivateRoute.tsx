import { useContext } from "react";
import { Navigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";
import type { ReactElement } from "react";

export default function PrivateRoute({ children }: { children: ReactElement }) {
  const { token } = useContext(AuthContext);
  return token ? children : <Navigate to="/login" />;
}