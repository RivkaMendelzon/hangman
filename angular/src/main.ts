import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { GameComponent } from './app/game/game.component';
import { provideRouter, Route } from '@angular/router';
import { appConfig } from './app/app.config';
import { PlayerSetupComponent } from './app/player-setup/player-setup.component';
import { JoinGameComponent } from './app/join-game/join-game.component';
import { HttpClient, provideHttpClient } from '@angular/common/http';

const routes: Route[] = [
  { path: '', component: PlayerSetupComponent },
  { path: 'join/:gameId', component: JoinGameComponent },
  { path: 'game', component: GameComponent }
];

bootstrapApplication(AppComponent,
   { providers: [provideRouter(routes),provideHttpClient()]})

  .catch((err) => console.error(err));
  

