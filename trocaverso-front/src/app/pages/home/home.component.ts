// src/app/pages/home/home.component.ts
import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import {PostCreatorComponent} from '../../components/post-creator/post-creator.component';
import {FeedComponent} from '../../components/feed/feed.component';



@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    PostCreatorComponent,
    FeedComponent,
  ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  ngOnInit(): void {
  }

}

