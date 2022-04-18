import { TaskService, NotificationService, ConfigurationService } from 'src/app/services';
import { Configuration, Task } from 'src/app/domain/models';

import { Component, OnDestroy, OnInit, ViewRef } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit, OnDestroy {
  config: Configuration | undefined
  tasks: Task[] = []
  displayedColumns: string[] = ['done', 'task', 'buttons']
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
          this.loadTasks()
        }
      })

    this.configurationService.onChange.subscribe(
      config => this.config = { ...config },
      error => console.log(error)
    )
  }

  ngOnDestroy(): void {
    this.configurationService.onChange.unsubscribe()
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
      .updateTask({ ...task, done: !task.done })
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
