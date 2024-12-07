import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private socket!: WebSocket;

  constructor() {
    this.connect
  }

  connect(): void {
    this.socket = new WebSocket('ws://localhost:8083/game');
    this.socket.onopen = () => {
      console.log('WebSocket connection established');
    };

    this.socket.onmessage = (event) => {
      console.log('Message received:', event.data);
    };

    this.socket.onclose = () => {
      console.log('WebSocket connection closed');
    };

    this.socket.onerror = (error) => {
      console.error('WebSocket error:', error);
    };

  }
  
  sendMessage(message: string) {
    this.socket.send(message);
  }

  receiveMessages() {
    this.socket.onmessage = (event) => {
      console.log('Received message:', event.data);
    };
  }

  closeConnection() {
    this.socket.close();
  }

}
