import { Routes } from '@angular/router';
import { MainLayoutComponent } from './layouts/main-layout/main-layout.component';
import { HomeComponent } from './pages/home/home.component';
import { MyItemsComponent } from './pages/my-items/my-items.component';
import { MyServicesComponent } from './pages/my-services/my-services.component';
import { SentProposalsComponent } from './pages/sent-proposals/sent-proposals.component';
import { ReceivedProposalsComponent } from './pages/received-proposals/received-proposals.component';
import { EvaluationsComponent } from './pages/evaluations/evaluations.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { ExploreComponent } from './pages/explore/explore.component';

export const routes: Routes = [
  {
    path: '',
    component: MainLayoutComponent,
    children: [
      { path: '', component: HomeComponent },
      { path: 'my-items', component: MyItemsComponent },
      { path: 'my-services', component: MyServicesComponent },
      { path: 'sent-proposals', component: SentProposalsComponent },
      { path: 'received-proposals', component: ReceivedProposalsComponent },
      { path: 'evaluations', component: EvaluationsComponent },
      { path: 'profile', component: ProfileComponent },
      { path: 'explore', component: ExploreComponent }
    ]
  }
];
