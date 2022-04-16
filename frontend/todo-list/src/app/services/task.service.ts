import { Task } from '../domain/models/task';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private url: string = 'http://localhost:3005/tasks'

  constructor(private http: HttpClient) { }

  getTasks(): Observable<Task[]> {
    return this.http
      .get<Task[]>(this.url)
  }

  addTask(task: Task): Observable<Task> {
    return this.http
      .post<Task>(this.url, task)
  }

  removeTask(id: Number): Observable<any> {
    return this.http
      .delete<Number>(`${this.url}/${id}`)
  }

  updateTask(task: Task): Observable<Task> {
    return this.http
      .put<Task>(`${this.url}/${task.id}`, task)
  }
}
