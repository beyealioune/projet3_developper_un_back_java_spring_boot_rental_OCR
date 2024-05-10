import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/interfaces/user.interface';
import { SessionService } from 'src/app/services/session.service';
import { RentalsService } from '../../services/rentals.service';
import { Observable } from 'rxjs';
import { RentalsResponse } from '../../interfaces/api/rentalsResponse.interface';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  public rentals$: Observable<RentalsResponse | null> = this.rentalsService.all();

  constructor(
    private sessionService: SessionService,
    private rentalsService: RentalsService
  ) { }
  ngOnInit(): void {
    console.log('rental', this.rentals$);
  }

  get user(): User | undefined {
    return this.sessionService.user;
  }
}
