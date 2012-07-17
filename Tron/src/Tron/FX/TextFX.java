package Tron.FX;

import java.awt.Color;
import java.awt.FontMetrics;

import Tron.Tron;

public class TextFX {
	public static int getSW(String s){
		FontMetrics FM = Tron.canvas.getFontMetrics(Tron.canvas.getFont());
		return FM.stringWidth(s);
	}
	
	public static void drawCString(String m, int y){
		Tron.canvas.drawString(m, Tron.WIDTH / 2 - getSW(m) / 2, y);
	}
	
	public static void drawCString(String m, int x, int y){
		Tron.canvas.drawString(m, Tron.WIDTH / 2 - getSW(m) / 2 + x, y);
	}
	
	public static void drawC3DString(String m, int y){
		Tron.canvas.setColor(Color.GRAY);
		drawCString(m, 2, y + 2);
		Tron.canvas.setColor(Color.WHITE);
		drawCString(m, y);
	}

	public static void drawC3DString(String m, Color f, int y, int sep){
		Tron.canvas.setColor(f.darker().darker().darker());
		drawCString(m, sep, y + sep);
		Tron.canvas.setColor(f);
		drawCString(m, y);
	}

	public static void draw3DString(String m, int x, int y, int sep){
		Tron.canvas.setColor(Color.GRAY);
		Tron.canvas.drawString(m, x + sep, y + sep);
		Tron.canvas.setColor(Color.WHITE);
		Tron.canvas.drawString(m, x, y);
	}

	public static void draw3DString(String m, Color f, int x, int y, int sep){
		Tron.canvas.setColor(f.darker().darker().darker());
		Tron.canvas.drawString(m, x + sep, y + sep);
		Tron.canvas.setColor(f);
		Tron.canvas.drawString(m, x, y);
	}
}
