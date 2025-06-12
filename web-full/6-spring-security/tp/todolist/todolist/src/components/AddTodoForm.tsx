import { useState } from "react";
import { createTodo } from "../services/authService";
import type { Todo } from "../types/Todo";
import React from "react";

interface AddTodoFormProps {
  token: string;
  onAddTodo: (todo: Todo) => void;
}

export default function AddTodoForm({ token, onAddTodo }: AddTodoFormProps): React.ReactElement {
  const [title, setTitle] = useState<string>("");
  const [description, setDescription] = useState<string>("");

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const todoInput = {
      title,
      description,
      completed: false,
    };

    const newTodo = await createTodo(todoInput, token); // ✅ inversé ici
    onAddTodo(newTodo);

    setTitle("");
    setDescription("");
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Titre"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
        required
      />
      <input
        type="text"
        placeholder="Description"
        value={description}
        onChange={(e) => setDescription(e.target.value)}
        required
      />
      <button type="submit">Ajouter</button>
    </form>
  );
}