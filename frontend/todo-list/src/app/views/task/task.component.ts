import { TaskService, NotificationService } from 'src/app/services';
import { Task } from 'src/app/domain/models';

import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {
  tasks: Task[] = []
  displayedColumns: string[] = ['done', 'task', 'buttons']
  taskDescriptionFormControl: FormControl = new FormControl('', [Validators.required])

  constructor(
      private taskService: TaskService,
      private notificationService: NotificationService
  ) { }

  ngOnInit(): void {
    this.loadTasks()
  }

  handleDeleteButtonClick(task: Task): void {
    this.taskService
      .removeTask(task.id)
      .subscribe({
        complete: () => {
          this.notificationService.showInfoMessage("Task removed")
          this.loadTasks()
        }
      })
  }

  handleToggleTaskDone(task: Task): void {
    this.taskService
      .updateTask({
        ...task,
        done: !task.done
      })
      .subscribe({
        complete: () => this.notificationService.showInfoMessage("Task marked as done")
      })
  }

  loadTasks(): void {
    this.taskService
      .getTasks()
      .subscribe(tasks => this.tasks = tasks.reverse())
  }

  handleSubmit(): void {
    const task: Task = { description: this.taskDescriptionFormControl.value } as Task
    this.taskDescriptionFormControl.reset()
    this.taskService
      .addTask(task)
      .subscribe({
        complete: () => {
          this.notificationService.showInfoMessage("Task added")
          this.loadTasks()
        }
      })
  }
}
