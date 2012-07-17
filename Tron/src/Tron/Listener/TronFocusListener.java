package Tron.Listener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import Tron.Tron;

public class TronFocusListener implements FocusListener{
	public Tron game;
	
	public TronFocusListener(Tron g){
		game = g;
	}

	@Override
	public void focusGained(FocusEvent e) {
		game.focus = true;
	}

	@Override
	public void focusLost(FocusEvent e) {
		game.focus = false;
	}
}
