import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  handleGithubClick(): void {
    window.open("https://github.com/PauloKoglin/angular-todo-list/tree/main/frontend/todo-list", '_blank')
  }

}
