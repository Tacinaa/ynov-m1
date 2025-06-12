import axios from "axios";
import type { Todo } from "../types/Todo";

const API_URL = "http://localhost:8080";

// ✅ Login
export async function login(username: string, password: string): Promise<string | null> {
  try {
    const response = await axios.post<{ token: string }>(`${API_URL}/auth/login`, {
      username,
      password,
    }, {
      headers: {
        "Content-Type": "application/json",
      },
    });

    return response.data.token;
  } catch {
    return null;
  }
}

// ✅ Récupération des todos
export async function getTodos(token: string): Promise<Todo[]> {
  const response = await axios.get<Todo[]>(`${API_URL}/todos`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
}

// ✅ Création d'un todo
export async function createTodo(todo: Omit<Todo, "id" | "user">, token: string): Promise<Todo> {
  const response = await axios.post<Todo>(`${API_URL}/todos`, todo, {
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
  });
  return response.data;
}

// ✅ Suppression d'un todo
export async function deleteTodo(id: number, token: string): Promise<void> {
  await axios.delete(`${API_URL}/todos/${id}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
}

// ✅ Mise à jour d'un todo
export async function updateTodo(id: number, updatedTodo: Partial<Todo>, token: string): Promise<Todo> {
  const response = await axios.put<Todo>(`${API_URL}/todos/${id}`, updatedTodo, {
    headers: {
      Authorization: `Bearer ${token}`,
      "Content-Type": "application/json",
    },
  });
  return response.data;
}