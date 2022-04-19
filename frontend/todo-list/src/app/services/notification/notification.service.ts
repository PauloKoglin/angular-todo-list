import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private snackBar: MatSnackBar) { }

  showInfoMessage(message: String) {
    this.snackBar.open(message as string, 'OK', {
      horizontalPosition: 'center',
      verticalPosition: 'top',
      duration: 2000,
      panelClass: 'info-notification-bar'
    });
  }

  showErrorMessage(message: String) {
    this.snackBar.open(message as string, 'OK', {
      horizontalPosition: 'center',
      verticalPosition: 'top',
      duration: 3000,
      panelClass: 'error-notification-bar'
    });
  }
}
