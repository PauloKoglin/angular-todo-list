import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Task } from 'src/app/domain/models/task';
import { TaskService } from 'src/app/services/task.service';

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
      private snackBar: MatSnackBar
  ) { }

  ngOnInit(): void {
    this.loadTasks()
  }

  handleDeleteButtonClick(task: Task): void {
    this.taskService
      .removeTask(task.id)
      .subscribe()
      .add(() => this.loadTasks())
  }

  handleToggleTaskDone(task: Task): void {
    this.taskService
      .updateTask({
        ...task,
        done: !task.done
      })
      .subscribe()
  }

  loadTasks(): void {
    this.taskService
      .getTasks()
      .subscribe(tasks => this.tasks = tasks.reverse())
  }

  onSubmit(): void {
    const task: Task = { description: this.taskDescriptionFormControl.value } as Task
    this.taskDescriptionFormControl.reset()
    this.taskService
      .addTask(task)
      .subscribe({
        complete: () => {
          this.openSnackBar()
          this.loadTasks()
        }
      })
  }

  openSnackBar() {
    this.snackBar.open('Task added', 'OK', {
      horizontalPosition: 'center',
      verticalPosition: 'top',
      duration: 2000
    });
  }

}
