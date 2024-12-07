import { Component } from '@angular/core';
import { Player } from '../interface/Player';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-player-setup',
  imports: [FormsModule],
  templateUrl: './player-setup.component.html',
  styleUrl: './player-setup.component.css'
})
export class PlayerSetupComponent {
    player: Player = { playerName: '', playerEmail: '' };
  
    constructor(private router: Router) {}
  
    startGame(event: Event) {
      event.preventDefault(); 

      if (this.player.playerName.trim() != '' && this.player.playerEmail.trim() !='') {
        this.router.navigate(['/game']); 
        localStorage.setItem('player', JSON.stringify(this.player));

      } else {
        alert('Please enter your name and email!');
      }
    }
  }
  
