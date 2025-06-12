import React, {
  createContext,
  useEffect,
  useState,
  useCallback
} from "react";
import type { ReactNode } from "react";
import { useNavigate } from "react-router-dom"; // ⬅️ ajoute ceci
import * as authService from "../services/authService";

interface AuthContextType {
  token: string | null;
  login: (username: string, password: string) => Promise<boolean>;
  logout: () => void;
}

export const AuthContext = createContext<AuthContextType>({
  token: null,
  login: async () => false,
  logout: () => {},
});

export function AuthProvider({ children }: { children: ReactNode }): React.ReactElement {
  const [token, setToken] = useState<string | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    if (storedToken) setToken(storedToken);
  }, []);

  const login = async (username: string, password: string) => {
    const tok = await authService.login(username, password);
    if (tok) {
      setToken(tok);
      localStorage.setItem("token", tok);
      return true;
    }
    return false;
  };

  const logout = useCallback(() => {
    setToken(null);
    localStorage.removeItem("token");
    navigate("/login"); 
  }, [navigate]);

  return (
    <AuthContext.Provider value={{ token, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}