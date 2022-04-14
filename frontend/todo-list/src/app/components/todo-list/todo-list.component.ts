import { Component, OnInit } from '@angular/core';

const fakeData = [
  {
    id: 1,
    description: "my task 1",
    done: true
  },
  {
    id: 2,
    description: "my task 2",
    done: true
  },
  {
    id: 3,
    description: "my task 3",
    done: false
  },
  {
    id: 4,
    description: "my task 4",
    done: true
  }
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
