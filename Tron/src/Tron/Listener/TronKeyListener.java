package Tron.Listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Tron.Tron;

public class TronKeyListener implements KeyListener{
	public Tron game;
	
	public TronKeyListener(Tron g){
		game = g;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 's' && Tron.people.get(0).preXD!=0){
			Tron.people.get(0).xD = 0;
			Tron.people.get(0).yD = 1;
		}
		if(e.getKeyChar() == 'w' && Tron.people.get(0).preXD!=0){
			Tron.people.get(0).xD = 0;
			Tron.people.get(0).yD = -1;
		}
		if(e.getKeyChar() == 'a' && Tron.people.get(0).preYD!=0){
			Tron.people.get(0).xD = -1;
			Tron.people.get(0).yD = 0;
		}
		if(e.getKeyChar() == 'd' && Tron.people.get(0).preYD!=0){
			Tron.people.get(0).xD = 1;
			Tron.people.get(0).yD = 0;
		}

		if(e.getKeyCode() == KeyEvent.VK_DOWN && Tron.people.get(1).preXD!=0){
			Tron.people.get(1).xD = 0;
			Tron.people.get(1).yD = 1;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP && Tron.people.get(1).preXD!=0){
			Tron.people.get(1).xD = 0;
			Tron.people.get(1).yD = -1;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && Tron.people.get(1).preYD!=0){
			Tron.people.get(1).xD = -1;
			Tron.people.get(1).yD = 0;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && Tron.people.get(1).preYD!=0){
			Tron.people.get(1).xD = 1;
			Tron.people.get(1).yD = 0;
		}

		if(e.getKeyCode() == KeyEvent.VK_R && Tron.winner != -1){
			game.startUp();
		}

		if(e.getKeyCode() == KeyEvent.VK_T && Tron.winner != -1){
			game.startSuper();
		}
		
		//Cheats/Easter Egss
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD0){
			Tron.playerSpeed++;
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD2){
			Tron.playerSpeed = 3;
		}
		if(e.getKeyCode() == KeyEvent.VK_NUMPAD3){
			Tron.playerSpeed++;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
