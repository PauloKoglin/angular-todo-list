import { Component, OnInit } from '@angular/core';
import { Task } from 'src/app/domain/models/task';

const getDescription = (): String => {
  return `task ${new Date().toISOString()}`;
}

const fakeData: Task[] = [
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true },
  { id: 1, description: getDescription(), done: true }
]

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {

  tasks: Task[]
  displayedColumns: string[] = ['id', 'task', 'done']

  constructor() {
    this.tasks = fakeData
   }

  ngOnInit(): void {

  }

}
