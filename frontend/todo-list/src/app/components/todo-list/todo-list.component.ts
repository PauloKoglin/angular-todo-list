import { Component, OnInit } from '@angular/core';
import { Task } from 'src/app/domain/models/task';
import { TaskService } from 'src/app/services/task.service';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {
  tasks: Task[] = []
  displayedColumns: string[] = ['id', 'task', 'done']

  constructor(private taskService: TaskService) { }

  ngOnInit(): void {
    this.taskService
      .getTasks()
      .subscribe(tasks => this.tasks = tasks)
  }

}
