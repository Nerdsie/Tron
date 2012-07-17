package Tron;

import java.awt.Color;
import java.awt.Graphics;

public class Tile {
	public Color color = Color.BLACK;
	
	public Tile(){
		color = Color.BLACK;
	}
	
	public void tryUpdate(Player p){
		if(p.x + p.xD >= Tron.WIDTH / Tron.playerSize || p.x + p.xD < 0){
			Tron.playerDie(p);
			return;
		}
		if(p.y + p.yD >= Tron.HEIGHT / Tron.playerSize || p.y + p.yD < 0){
			Tron.playerDie(p);
			return;
		}
		
		int x = 0, y = 0;

		for(int xx = 0; xx < Tron.WIDTH / Tron.playerSize; xx++){
			for(int yy = 0; yy < Tron.HEIGHT / Tron.playerSize; yy++){
				if(Tron.tiles[xx][yy] == this){
					x = xx;
					y = yy;
					break;
				}
			}
		}
		
		Tile preV = Tron.tiles[x + p.xD][y + p.yD];

		if(preV.color != Color.BLACK){
			Tron.playerDie(p);
			return;
		}
		
		color = p.color;
	}
	
	public void render(int x, int y, Graphics g){
		if(color == Color.BLACK)
			return;
		
		g.setColor(color.brighter());
		g.fillRect(x * Tron.playerSize, y * Tron.playerSize, Tron.playerSize, Tron.playerSize);
	}
}
