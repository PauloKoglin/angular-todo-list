import { Component, OnInit } from '@angular/core';
import { Task } from 'src/app/domain/models';
import { TaskService } from 'src/app/services';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  tasks: Task[];
  amountTasksDone: number;
  amountTasksTodo: number;

  constructor(private readonly taskService: TaskService) {
    this.tasks = [];
    this.amountTasksDone = 0;
    this.amountTasksTodo = 0;
  }

  ngOnInit(): void {
    this.taskService.getTasks()
      .subscribe(tasks => {
        this.tasks = tasks;

        tasks.forEach(task => {
          task.done && this.amountTasksDone++;
          !task.done && this.amountTasksTodo++;
        });
      });
    }
}
