package edu.cs160.Game.GameObjects.PlantObjects;

import edu.cs160.Game.GameObjects.GameObjects;

public class Pot {
Plant p;
int x, y;
	public Pot(Plant p,int x, int y){
		this.p = p;
		p.p=this;
		p.grounded= true;
		this.x=x;
		this.y=y;
		
		int imgHeight= GameObjects.Data.get("Pot_Small").image.getHeight(), imgWidth=GameObjects.Data.get("Pot_Small").image.getWidth();
		
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
