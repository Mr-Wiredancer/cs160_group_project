package edu.cs160.Game.GameObjects.PlantObjects;

import edu.cs160.Game.GameObjects.GameObjects;

public class Pot {
Plant p;
public int x, y;
public int imgWidth,imgHeight;
	public Pot(Plant p,int x, int y){
		this.p = p;
		p.p=this;
		p.grounded= true;
		this.x=x;
		this.y=y;
		
		imgHeight= GameObjects.Data.get("Pot_Small").image.getHeight();
		imgWidth=GameObjects.Data.get("Pot_Small").image.getWidth();
		
		int acceptableY = y+imgHeight/4;
		int acceptableX = x+imgWidth/2;
		
		p.sprite.x = acceptableX-p.sprite.imgWidth/2;
		p.sprite.y = acceptableY-p.sprite.imgHeight;
	}
	
	public void move(int x, int y){
		this.x=x;
		this.y=y;
		int imgHeight= GameObjects.Data.get("Pot_Small").image.getHeight(), imgWidth=GameObjects.Data.get("Pot_Small").image.getWidth();
		
		int acceptableY = y+imgHeight/4;
		int acceptableX = x+imgWidth/2;
		
		p.sprite.x = acceptableX-p.sprite.imgWidth/2;
		p.sprite.y = acceptableY-p.sprite.imgHeight;
	}
	
	public void update(){
		int plantCenter =p.sprite.x+p.sprite.imgWidth/2;
		int plantBot = p.sprite.y+p.sprite.imgHeight;
		
		this.x = plantCenter-imgWidth/2;
		this.y = plantBot-imgHeight/4;
		
	}
	
	public void free(){
		p.grounded = false;
		p.p = null;
		
	}
	
	public void giveXP(int amt){
		p.currentXP+=amt;
		if(p.currentXP>=p.maxXP){
			p.currentXP = p.maxXP;
			free();
		}
	}
}
