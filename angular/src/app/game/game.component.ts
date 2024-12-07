import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { GameService } from '../services/game.service';
import { Player } from '../interface/Player';
import { WebSocketService } from '../services/web-socket.service';
import { Subscription } from 'rxjs';
  
  @Component({
    selector: 'app-game',
    standalone: true,
    templateUrl: './game.component.html',
    styleUrls: ['./game.component.css'],
    imports: [CommonModule, FormsModule]
  })

  export class GameComponent implements OnInit, OnDestroy {
    player!: Player;
    guessedLetter: string = '';
    guessedLetters: string[] = [];
    remainingAttempts: number = 6;
    incorrectGuesses: string[] = [];
    activeGame: boolean = true;
    gameId: number = 0;
    message: string = '';
    inviteUrl: string | null = null;
    sessionId: string | null = null;
    isSession: boolean = false;
    private subscriptions = new Subscription();

    createSession(): void {
      var gameId = Math.random().toString(36).substr(2, 9);
      this.inviteUrl = `${window.location.origin}/join/${gameId}`;
  
      this.webSocketService.sendMessage(this.inviteUrl);
    }
  
    extractSessionIdFromUrl(url: string): string {
      return url.split('/join/')[1];
    }
  
  
    constructor(private webSocketService: WebSocketService, private gameService: GameService) {
    }

  
  sendMessage(type: string, value: string): void {
    this.webSocketService.sendMessage( value );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
    this.webSocketService.closeConnection();
  }

    ngOnInit() {
      if(this.isSession)
      {

        this.webSocketService.connect();//('ws://localhost:8083/socket-endpoint');
        // const sub = this.webSocketService..messages$.subscribe((message) => {
        // console.log('Received:', message);
        // });
        // this.subscriptions.add(sub);
      }   
      const playerData = localStorage.getItem('player');
      if (playerData) {
        this.player = JSON.parse(playerData);
        const savedGame = localStorage.getItem(this.player.playerEmail);
        if (savedGame) {
          const game = JSON.parse(savedGame);
          this.guessedLetters = game.guessedLetters;
          this.incorrectGuesses = game.incorrectGuesses;
          this.gameId = game.gameId;
        } else {
          this.startGame();
        }
  
      } else {
        alert('Please set up your player first!');
        window.location.href = '/';
      } 
    }  

    handleMessage(message: any): void {
      if (message.type === 'acceptInvite') {
        console.log(`${message.payload.player} has joined the game!`);
        this.startGame();
      } else if (message.type === 'turn') {
        console.log(`It's ${message.payload.player}'s turn.`);
      }
    }
  
    saveGame() {
      const game = {
        guessedLetters: this.guessedLetters,
        incorrectGuesses: this.incorrectGuesses,
        gameId: this.gameId
      };
      localStorage.setItem(this.player.playerEmail, JSON.stringify(game));
    }
    

    startGame() {
      this.gameService.startGame(this.player).subscribe((data) => {
        this.guessedLetters = data.guessedWord;
        console.log(this.guessedLetters)
        this.incorrectGuesses = data.incorrectGuesses;
        this.gameId = data.gameId;
        this.saveGame();
      });
    }
  
    onGuess() {
       if (!this.guessedLetter || !this.activeGame)
         return;
      this.gameService.makeGuess(this.guessedLetter, this.gameId).subscribe((data) => {

      if(this.guessedLetters == data.guessedWord)
      {
        if(this.incorrectGuesses == data.incorrectGuesses)
        {
          this.message = 'duplicate';
          console.log("duplicate");
        } else
        {
          this.message = 'mistake';
          console.log("mistake");
          this.incorrectGuesses = data.incorrectGuesses;
        }

      }
      else
      {
        this.message = 'correct';
          console.log("correct");
          this.guessedLetters = data.guessedWord;

      }

        if(data.activeGame)
            this.saveGame()
        else
        {
          localStorage.removeItem(this.player.playerEmail);
          if(!this.guessedLetters.includes('_'))
            this.message = "You won!!!"
          else
            this.message = "Too bad, you've done all the experiments."
            this.activeGame =false;
        }
      if(this.isSession)
        this.sendMessage('turn',this.player.playerName);
      });
    }
  logout(): void{
    localStorage.removeItem(this.player.playerEmail)
    localStorage.removeItem('player');
    window.location.href = '/';
  }
}
  
