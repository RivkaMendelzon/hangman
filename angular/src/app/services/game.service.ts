import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Player } from '../interface/Player';
import { webSocket } from 'rxjs/webSocket';
import { WebSocketService } from './web-socket.service';

@Injectable({
  providedIn: 'root',
})
export class GameService {
  private baseUrl = 'http://localhost:8083/api/game'; 
  private baseSessionUrl = 'http://localhost:8083/game';

  constructor(private http: HttpClient) {}

  startGame(player: Player): Observable<any> {
    return this.http.post(`${this.baseUrl}/start`, player,{ 
      headers: { 'Content-Type': 'application/json' },
    });
  }

  makeGuess(letter: string, gameId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/guess/`+  letter ,gameId);
  }

  createSession(player: { playerName: string; playerEmail: string }) {
    return this.http.post<string>(`${this.baseSessionUrl}/create-session`, player);
  }

  joinSession(sessionId: string, player: { playerName: string; playerEmail: string }) {
    return this.http.post(`${this.baseSessionUrl}/join-session/${sessionId}`, player);
  }

  makeGuessSession(sessionId: string, letter: string) {
    return this.http.post(`${this.baseSessionUrl}/make-guess/${sessionId}`, { letter });
  }
}
