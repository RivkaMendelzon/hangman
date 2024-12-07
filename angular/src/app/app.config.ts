// import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
// import { provideRouter } from '@angular/router';

// import { routes } from './app.routes';

// export const appConfig: ApplicationConfig = {
//   providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes)]
// };
import { ApplicationConfig } from '@angular/core';

import { provideHttpClient } from '@angular/common/http';
import { provideRouter, Routes } from '@angular/router';
import { GameComponent } from './game/game.component';
import { PlayerSetupComponent } from './player-setup/player-setup.component';
import { JoinGameComponent } from './join-game/join-game.component';

const routes: Routes = [
  { path: '', component: PlayerSetupComponent },
  { path: 'join/:gameId', component: JoinGameComponent },
  { path: 'game', component: GameComponent }
];

export const AppConfig = [
  provideRouter(routes),
];

export const appConfig: ApplicationConfig = {
  providers: [provideRouter(routes)
    ,provideHttpClient(), 
    
]
};