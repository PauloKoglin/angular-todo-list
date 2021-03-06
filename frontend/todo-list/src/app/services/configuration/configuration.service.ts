import { Configuration } from 'src/app/domain/models';

import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable, Output } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {
  private url: string = 'http://localhost:3005/configuration'
  @Output() onChange: EventEmitter<Configuration> = new EventEmitter()

  constructor(private http: HttpClient) { }

  update(config: Configuration): Observable<Configuration> {
    return new Observable(subscriber => {
      this.http
        .post<Configuration>(this.url, config)
        .subscribe({
          next: config => {
            this.onChange.next(config)
            subscriber.next(config)
          }
        })
    })
  }

  get(): Observable<Configuration> {
    return this.http.get<Configuration>(this.url)
  }
}
