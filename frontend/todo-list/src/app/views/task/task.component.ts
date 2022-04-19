import { TaskService, NotificationService, ConfigurationService } from 'src/app/services';
import { Configuration, Task } from 'src/app/domain/models';

import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit, OnDestroy {
  config: Configuration | undefined
  tasks: Task[] = []
  displayedColumns: string[] = this.getColumnsToDisplay()
  taskDescriptionFormControl: FormControl = new FormControl('', [Validators.required])

  constructor(
      private taskService: TaskService,
      private notificationService: NotificationService,
      private configurationService: ConfigurationService
  ) { }

  ngOnInit(): void {
    this.configurationService
      .get()
      .subscribe({
        next: config => {
          this.config = { ...config }
          this.displayedColumns = this.getColumnsToDisplay()
          this.loadTasks()
        }
      })

    this.configurationService.onChange.subscribe(
      config => {
        this.config = { ...config }
        this.displayedColumns = this.getColumnsToDisplay()
      },
      error => console.log(error)
    )
  }

  ngOnDestroy(): void {
    this.configurationService.onChange.unsubscribe()
  }

  getColumnsToDisplay(): string[] {
    return this.config?.showLineNumber ? ['lineNumber', 'done', 'task', 'buttons']: ['done', 'task', 'buttons']
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
    this.tasks = this.tasks.map(item => (item.id === task.id ? { ...item, done: !item.done } : item))
    this.taskService
      .updateTask({ ...task, done: !task.done })
      .subscribe({
        next: task => this.notificationService.showInfoMessage(`Task marked as ${task.done ? "done" : "undone"}`)
      })
  }

  loadTasks(): void {
    this.taskService
      .getTasks()
      .subscribe(tasks => this.tasks = tasks.reverse())
  }

  handleSubmit(): void {
    if (!this.taskDescriptionFormControl.valid) {
      this.notificationService.showErrorMessage("Task can't be added. Please fill all required fields.")
    } else {
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
}
