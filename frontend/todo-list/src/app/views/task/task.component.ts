import { TaskService, NotificationService, ConfigurationService } from 'src/app/services';
import { Configuration, Task } from 'src/app/domain/models';

import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit, OnDestroy {
  filterDone: boolean | undefined
  config: Configuration | undefined
  tasks: Task[] = []
  displayedColumns: string[] = this.getColumnsToDisplay()
  taskDescriptionFormControl: FormControl = new FormControl('', [Validators.required])

  constructor(
      private route: ActivatedRoute,
      private taskService: TaskService,
      private notificationService: NotificationService,
      private configurationService: ConfigurationService
  ) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.filterDone = params['done'] ? params['done'] === 'true' : undefined
      this.loadConfiguration()
    })

    this.configurationService.onChange.subscribe(config => {
        this.config = { ...config }
        this.displayedColumns = this.getColumnsToDisplay()
      },
      error => console.log(error)
    )
  }

  loadConfiguration() {
    this.configurationService.get().subscribe({
      next: config => {
        this.config = { ...config }
        this.displayedColumns = this.getColumnsToDisplay()
        this.loadTasks()
      }
    })
  }

  ngOnDestroy(): void {
    this.configurationService.onChange.unsubscribe()
  }

  getColumnsToDisplay(): string[] {
    return this.config?.showLineNumber ? ['lineNumber', 'done', 'task', 'buttons']: ['done', 'task', 'buttons']
  }

  handleDeleteButtonClick(task: Task): void {
    this.taskService.removeTask(task.id)
      .subscribe({
        complete: () => {
          this.notificationService.showInfoMessage("Task removed")
          this.loadTasks()
        }
      })
  }

  handleToggleTaskDone(task: Task): void {
    this.tasks = this.tasks.map(item => (item.id === task.id ? { ...item, done: !item.done } : item))
    this.taskService.updateTask({ ...task, done: !task.done }).subscribe({
      next: task => this.notificationService.showInfoMessage(`Task marked as ${task.done ? "done" : "undone"}`)
    })
  }

  loadTasks(): void {
    this.taskService.getTasks()
      .subscribe(tasks => this.tasks = tasks.filter(task => this.filterDone !== undefined ? task.done === this.filterDone : true).reverse())
  }

  handleSubmit(): void {
    if (!this.taskDescriptionFormControl.valid) {
      this.notificationService.showErrorMessage("Task can't be added. Please fill all required fields.")
    } else {
      const task: Task = { description: this.taskDescriptionFormControl.value } as Task
      this.taskDescriptionFormControl.reset()
      this.taskService.addTask(task)
        .subscribe({
          complete: () => {
            this.notificationService.showInfoMessage("Task added")
            this.loadTasks()
          }
        })
    }
  }
}
