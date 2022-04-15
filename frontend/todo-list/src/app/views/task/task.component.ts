import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  taskDescriptionFormControl: FormControl = new FormControl('', [Validators.required])

  constructor() { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    alert('Test')
  }

}
