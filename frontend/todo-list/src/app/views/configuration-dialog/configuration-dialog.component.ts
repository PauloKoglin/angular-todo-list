import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Configuration } from 'src/app/domain/models';
import { ConfigurationService, NotificationService } from 'src/app/services';

@Component({
  selector: 'app-configuration-dialog',
  templateUrl: './configuration-dialog.component.html',
  styleUrls: ['./configuration-dialog.component.css']
})
export class ConfigurationDialogComponent implements OnInit {
  configurationsGroup: FormGroup;

  constructor(
    private configurationService: ConfigurationService,
    private notificationService: NotificationService,
    private formBuilder: FormBuilder
  ) {
    this.configurationsGroup = this.formBuilder.group({
      enableRemoveTask: false,
      showLineNumber: false
    })
  }

  ngOnInit(): void {
    this.configurationService
      .get()
      .subscribe({
        next: config => this.configurationsGroup.setValue({ ...config })
      })
  }

  handleSaveAndClose(): void {
    const configuration: Configuration = { ...this.configurationsGroup.value }
    this.configurationService
      .update(configuration)
      .subscribe({
        complete: () => this.notificationService.showInfoMessage("Configuration saved")
      })
  }
}
