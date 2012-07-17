package Tron;

import java.awt.Color;

public class Player {
	public int id = 0;
	public int xD = 1;
	public int yD = 0;
	public int preXD = 1;
	public int preYD = 0;
	public int x = 0;
	public int y = 1;
	public int defX = x, defY = y, defXD = xD, defYD = yD;
	public Color color = Color.RED;
	
	public void updatePosition(){
		
		for(int i = 1; i <= Tron.playerSpeed; i++){
			x+=xD;
			y+=yD;
			
			updateLine();
		}

		preXD = xD;
		preYD = yD;
	}
	
	public void updateLine(){
		if(x > Tron.WIDTH / Tron.playerSize - 1 || x<0 || y > Tron.HEIGHT / Tron.playerSize - 1 || y < 0){
			Tron.playerDie(this);
			return;
		}
		
		Tron.tiles[x][y].tryUpdate(this);
	}
}
