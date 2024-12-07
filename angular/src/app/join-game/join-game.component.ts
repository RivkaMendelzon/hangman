import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WebSocketService } from '../services/web-socket.service';
import { PlayerSetupComponent } from '../player-setup/player-setup.component';

@Component({
  selector: 'app-join-game',
  imports: [PlayerSetupComponent],
  templateUrl: './join-game.component.html',
  styleUrl: './join-game.component.css'
})
export class JoinGameComponent {
  public gameId: string = '';
  public currentPlayer: string = 'player2';

  constructor(private route: ActivatedRoute, private webSocketService: WebSocketService) {}

  ngOnInit(): void {
    this.webSocketService.connect()//.connect('ws://localhost:8083/socket-endpoint');
    this.gameId = this.route.snapshot.params['gameId'];
    this.webSocketService.sendMessage('acceptInvite');
  }
}
