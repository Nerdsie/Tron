package Tron.FX;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	String name;

	private AudioClip clip;

	private Sound(String name) {
		name = "/" + name;
		
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
