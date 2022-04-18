import { Component, OnInit } from '@angular/core';
import { ConfigurationService } from 'src/app/services/configuration/configuration.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  constructor(private configurationService: ConfigurationService) { }

  ngOnInit(): void {
  }

  handleConfigClick() {
    alert("this will be the config button")
    this.configurationService.update({
      enableRemoveTask: true,
      showLineNumber: true
    })
  }

}
