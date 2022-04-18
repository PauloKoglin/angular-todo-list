import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Configuration } from 'src/app/domain/models';
import { ConfigurationService } from 'src/app/services';

@Component({
  selector: 'app-configuration-dialog',
  templateUrl: './configuration-dialog.component.html',
  styleUrls: ['./configuration-dialog.component.css']
})
export class ConfigurationDialogComponent implements OnInit {
  configurations: FormGroup;

  constructor(
    private config: ConfigurationService,
    private formBuilder: FormBuilder
  ) {
    this.configurations = this.formBuilder.group({
      enableRemoveTask: false,
      showLineNumber: false
    })
  }

  ngOnInit(): void {
    this.config
      .get()
      .subscribe({
        next: config => this.configurations.setValue({ ...config })
      })
  }
}
