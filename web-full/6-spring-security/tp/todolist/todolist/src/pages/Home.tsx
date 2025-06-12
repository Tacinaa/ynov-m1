import { useContext, useEffect, useState } from 'react';
import { getTodos } from '../services/todoService';
import { AuthContext } from '../context/AuthContext';
import type { Todo } from '../types/Todo';

export default function Home() {
  const { token } = useContext(AuthContext);
  const [todos, setTodos] = useState<Todo[]>([]);

  useEffect(() => {
    if (token) {
      getTodos(token)
        .then(setTodos)
        .catch((err) => console.error('Erreur chargement todos :', err));
    }
  }, [token]);

  return (
    <div>
      <h1>Liste des tâches</h1>
      {todos.length === 0 ? (
        <p>Aucune tâche disponible</p>
      ) : (
        <ul>
          {todos.map((todo) => (
            <li key={todo.id}>
              <strong>{todo.title}</strong> – {todo.description}
              {todo.completed && ' ✅'}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}