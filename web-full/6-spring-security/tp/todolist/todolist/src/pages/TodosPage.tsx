import React, { useContext, useEffect, useState } from "react";
import { AuthContext } from "../context/AuthContext";
import type { Todo } from "../types/Todo";
import {
  getTodos,
  createTodo,
  deleteTodo,
  updateTodo,
} from "../services/authService";
import { toast } from "react-toastify";

export default function TodosPage(): React.ReactElement {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [newTitle, setNewTitle] = useState("");
  const [newDescription, setNewDescription] = useState("");
  const { token, logout } = useContext(AuthContext);

  useEffect(() => {
    if (token) {
      getTodos(token)
        .then(setTodos)
        .catch(() => toast.error("Échec du chargement des tâches."));
    }
  }, [token]);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!newTitle || !newDescription || !token) return;

    try {
      const newTodo = await createTodo(
        {
          title: newTitle,
          description: newDescription,
          completed: false,
        },
        token
      );
      setTodos([...todos, newTodo]);
      setNewTitle("");
      setNewDescription("");
    } catch {
      toast.error("Vous n'avez pas l'autorisation de créer une tâche.");
    }
  };

  const handleDelete = async (id: number) => {
    if (!token) return;
    try {
      await deleteTodo(id, token);
      setTodos(todos.filter((todo) => todo.id !== id));
    } catch {
      toast.error("Suppression non autorisée.");
    }
  };

  const handleToggleCompleted = async (todo: Todo) => {
    if (!token) return;

    try {
      const updated = await updateTodo(
        todo.id,
        {
          title: todo.title,
          description: todo.description,
          completed: !todo.completed,
        },
        token
      );
      setTodos(todos.map((t) => (t.id === updated.id ? updated : t)));
    } catch {
      toast.error("Modification non autorisée.");
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4">
        📝 <strong>Mes tâches</strong>
      </h2>

      <div className="d-flex justify-content-end mb-3">
        <button className="btn btn-secondary" onClick={logout}>
          Se déconnecter
        </button>
      </div>

      <ul className="list-group mb-4">
        {todos.map((todo) => (
          <li
            key={todo.id}
            className={`list-group-item d-flex justify-content-between align-items-center ${
              todo.completed ? "bg-light text-muted" : ""
            }`}
          >
            <div className="flex-grow-1">
              <div
                className={todo.completed ? "text-decoration-line-through" : ""}
              >
                <strong>{todo.title}</strong> – {todo.description}
              </div>
              {todo.completed && (
                <span className="badge bg-success mt-1">Complétée</span>
              )}
            </div>
            <div>
              <button
                className={`btn btn-sm me-2 ${
                  todo.completed ? "btn-success" : "btn-outline-success"
                }`}
                onClick={() => handleToggleCompleted(todo)}
              >
                {todo.completed ? "✅" : "✔"}
              </button>
              <button
                className="btn btn-sm btn-outline-danger"
                onClick={() => handleDelete(todo.id)}
              >
                🗑
              </button>
            </div>
          </li>
        ))}
      </ul>

      <form className="row g-2" onSubmit={handleSubmit}>
        <div className="col-sm">
          <input
            className="form-control"
            placeholder="Titre"
            value={newTitle}
            onChange={(e) => setNewTitle(e.target.value)}
          />
        </div>
        <div className="col-sm">
          <input
            className="form-control"
            placeholder="Description"
            value={newDescription}
            onChange={(e) => setNewDescription(e.target.value)}
          />
        </div>
        <div className="col-auto">
          <button type="submit" className="btn btn-primary">
            Ajouter
          </button>
        </div>
      </form>
    </div>
  );
}