import axios from 'axios';
import type { Todo } from '../types/Todo';

const API_URL = 'http://localhost:8080/todos';

export async function getTodos(token: string): Promise<Todo[]> {
  const response = await axios.get<Todo[]>(API_URL, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
  return response.data;
}