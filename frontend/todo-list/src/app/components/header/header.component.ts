import { ConfigurationService } from 'src/app/services';
import { ConfigurationDialogComponent } from 'src/app/views';

import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  constructor(
    private configurationService: ConfigurationService,
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
  }

  handleConfigClick() {
    const dialogRef = this.dialog.open(ConfigurationDialogComponent)
    dialogRef.afterClosed().subscribe(() => console.log("config closed"))

    // this.configurationService
    //   .update({
    //     enableRemoveTask: true,
    //     showLineNumber: true
    //   })
    //   .subscribe()
  }

}
