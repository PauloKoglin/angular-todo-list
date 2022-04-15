import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Task } from 'src/app/domain/models/task';
import { TaskService } from 'src/app/services/task.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  taskDescriptionFormControl: FormControl = new FormControl('', [Validators.required])

  constructor(private taskService: TaskService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const task: Task = { description: this.taskDescriptionFormControl.value } as Task
    this.taskService.addTask(task).subscribe()
  }

}
