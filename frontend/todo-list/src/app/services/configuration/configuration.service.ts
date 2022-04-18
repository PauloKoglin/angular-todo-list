import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { Observable } from 'rxjs';
import { Configuration } from 'src/app/domain/models';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {
  private url: string = 'http://localhost:3005/configuration'
  @Output() onChange: EventEmitter<Configuration> = new EventEmitter()

  constructor(private http: HttpClient) { }

  update(config: Configuration): void {
    this.http
      .post<Configuration>(this.url, config)
      .subscribe({
        next: config => {
          this.onChange.next(config)
        }
      })
  }

  get(): Observable<Configuration> {
    return this.http.get<Configuration>(this.url)
  }
}
