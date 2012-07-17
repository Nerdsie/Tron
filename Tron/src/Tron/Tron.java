package Tron;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import Tron.FX.TextFX;
import Tron.Listener.TronFocusListener;
import Tron.Listener.TronKeyListener;

public class Tron extends Applet implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public Image backBuffer;
	public static Graphics canvas;
	
	public boolean running = false;
	
	public static Color backColor = Color.BLACK;
	public static final int WIDTH = 640, HEIGHT = 480;

	public static int playerSize = 4;

	public static int p1Score = 0;
	public static int p2Score = 0;
	public static Tile[][] tiles = new Tile[WIDTH / playerSize][HEIGHT / playerSize];
	public static ArrayList<Player> people = new ArrayList<Player>();
	
	public boolean focus = false;
	public int yFocOff = 0, yFocDir = 1;
	
	public static int winner = -1;
	public int amountOfPeople = 2;
	public static int playerSpeed = 3;
	public static int preSpeed = -1;
	
	public static boolean superTron = false;
	public boolean setUp = false;
	
	public void init(){
		
	}
	
	public static void tick(){
		if(winner != -1){
			return;
		}
		
		for(Player p: people){
			p.updatePosition();

			if(p.x > Tron.WIDTH / Tron.playerSize - 1 || p.x<0 || p.y > Tron.HEIGHT / Tron.playerSize - 1 || p.y < 0){
				Tron.playerDie(p);
				return;
			}
			
			tiles[p.x][p.y].tryUpdate(p);
		}
		
		if(people.size()>1){
			if(people.get(0).x == people.get(1).x && people.get(0).y == people.get(1).y){
				winner = -2;
			}
		}
	}
	
	public static void render(){
		if(superTron)
			canvas.setColor(new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256)));
		else
			canvas.setColor(Color.BLACK);
		
		canvas.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int x = 0; x < WIDTH / playerSize; x++){
			for(int y = 0; y < HEIGHT / playerSize; y++){
				if(tiles[x][y] == null)
					tiles[x][y] = new Tile();
				
				tiles[x][y].render(x, y, canvas);
			}
		}
		
		for(Player p: people){
			canvas.setColor(p.color.darker());
			canvas.fillRect(p.x * playerSize - 1, p.y * playerSize - 1, playerSize + 2, playerSize + 2);
		}
		
		if(winner!=-1){
			superTron = false;
			
			canvas.setColor(new Color(0, 0, 0, 160));
			canvas.fillRect(0, 0, WIDTH, HEIGHT);

			if(winner == 0)
				TextFX.drawC3DString("BLUE WINS!", HEIGHT / 2 - 48);	
			else
				TextFX.drawC3DString("RED WINS!", HEIGHT / 2 - 48);	
				
			TextFX.drawC3DString("BLUE'S SCORE = " + p1Score + " | RED'S SCORE = " + p2Score, HEIGHT / 2 - 16);	
			TextFX.drawC3DString("Press 'r' to replay.", HEIGHT / 2 + 16);			
			TextFX.drawC3DString("Press 't' for Super Tron!", HEIGHT / 2 + 48);			
		}
	}
	
	public static void playerDie(Player p){
		/*for(int x = 0; x < WIDTH / playerSize; x++){
			for(int y = 0; y < HEIGHT / playerSize; y++){
				if(tiles[x][y].color == p.color){
					tiles[x][y].color = Color.BLACK;
				}
			}
		}*/
		if(winner != -1)
			return;
		
		if(people.get(0) == p){
			winner = 0;
			p1Score++;
		}else{
			winner = 1;
			p2Score++;
		}
	}
	
	public void startSuper(){
		setUp = true;
		superTron = true;
		
		preSpeed = playerSpeed;
		
		playerSize = 3;
		playerSpeed = 4;
		
		restartDef();
	}
	
	public void startUp(){
		setSize(WIDTH, HEIGHT);
		
		setUp = true;
		
		playerSize = 4;
		superTron = false;

		if(preSpeed != -1)
			playerSpeed = preSpeed;
		
		preSpeed = -1;
		
		restartDef();
	}
	
	public void restartDef(){
		tiles = new Tile[WIDTH / playerSize][HEIGHT / playerSize];

		for(int x = 0; x < WIDTH / playerSize; x++){
			for(int y = 0; y < HEIGHT / playerSize; y++){
				tiles[x][y] = new Tile();
			}
		}

		people.clear();
		for(int i = 0; i < amountOfPeople; i++){
			people.add(new Player());
		}
		
		people.get(0).color = Color.RED.brighter();
		
		people.get(1).xD = -1;
		people.get(1).yD = 0;
		people.get(1).defX = (WIDTH) / playerSize - 1;
		people.get(1).defY = (HEIGHT) / playerSize - 2;
		people.get(1).x = people.get(1).defX;
		people.get(1).y = people.get(1).defY;
		people.get(1).defXD = -1;
		people.get(1).defYD = 0;
		people.get(1).color = Color.CYAN;
		
		winner = -1;
		setUp= false;
	}
	
	public void focusTick(){
		yFocOff+=yFocDir;
		
		if(yFocOff > 20){
			yFocDir = -yFocDir;
		}
		if(yFocOff < 0){
			yFocDir = -yFocDir;
		}
	}
	
	public void focusRender(){
		if(!focus){
			canvas.setColor(Color.BLACK);
			canvas.fillRect(0, 0, WIDTH, HEIGHT);

			int yOff = yFocOff - 10;
			
			canvas.setFont(new Font("Arial", 1, 30));
			TextFX.drawC3DString("Click Here to Play!", Color.WHITE, HEIGHT / 2 + yOff, 3);
			
			repaint();
		}
	}
	
	public void run(){
		startUp();
		
		while(running){
			if(!focus){
				focusTick();
				focusRender();
			}else{
				if(setUp)
					return;
				
				tick();
				render();
				repaint();
			}
			
			try{ Thread.sleep(17); }catch(Exception e){}
		}
	}
	
	public void start(){
		backBuffer = createImage(WIDTH, HEIGHT);
		canvas = backBuffer.getGraphics();
		
		running = true;
		
		//Add listeners here.
		addKeyListener(new TronKeyListener(this));
		addFocusListener(new TronFocusListener(this));
		
		new Thread(this).start();
	}
	
	public void stop(){
		running = false;
	}
	
	public void paint(Graphics g){
		g.drawImage(backBuffer, 0, 0, null);
	}
	
	public void update(Graphics g){	
		paint(g);
	}
}
