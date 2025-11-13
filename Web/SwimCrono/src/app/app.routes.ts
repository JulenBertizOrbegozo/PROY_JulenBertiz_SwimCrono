import { Routes } from '@angular/router';
import { FunctionalitiesComponent } from './functionalities/functionalities.component';
import { QuestionsComponent } from './questions/questions.component';
import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', component: HomeComponent },
  { path: 'functionalities', component: FunctionalitiesComponent },
  { path: 'questions', component: QuestionsComponent },
  { path: 'about', component: AboutComponent },
  { path: '**', redirectTo: '' }
];
