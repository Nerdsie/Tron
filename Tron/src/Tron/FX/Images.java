package Tron.FX;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Images {
	String name;
	Image img;
	
	public Images(String n){
		name = n;
		img = getImage(name);
	}
	
	public Images(){
		
	}
	
	public Image get(){
		img = getImage(name);
		return img;
	}
	
	public Image getImage(String name){
		try{
			String loc = "/" + name;
			URL url = getClass().getResource(loc);
			
			return Toolkit.getDefaultToolkit().getImage(url);
		}catch(Exception e){ return null;}
	}
}
