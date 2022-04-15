import { Component, OnInit } from '@angular/core';

const fakeData = [
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true },
  { id: 1, description: "my task 1", done: true }
]

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {

  displayedColumns: string[] = ['id', 'task', 'done'];
  tasks = fakeData;

  constructor() { }

  ngOnInit(): void {
  }

}
