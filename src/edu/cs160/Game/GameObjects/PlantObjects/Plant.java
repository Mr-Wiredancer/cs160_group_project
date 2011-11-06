package edu.cs160.Game.GameObjects.PlantObjects;

import edu.cs160.Game.GameObjects.GameObjects;
import edu.cs160.Game.GameObjects.Sprites.Sprite;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.widget.*;

public class Plant {
	public String name;
	public int currentXP,maxXP, growthStage;
	public boolean grounded;
	public Pot p;
	public Sprite sprite;
	public int id;
	
	public Plant(String name,int currentXP,int maxXP,int growthStage,int type){
		this.name = name;
		this.currentXP=currentXP;
		this.maxXP = maxXP;
		this.growthStage = growthStage;
		this.sprite = new Sprite("Sapling1_anim",3,1,3,true);//PlantMaker.makePlant(type, growthStage);	
	}
	
	public void draw(Resources res, Canvas c){
		if(grounded){
			GameObjects.draw(res, c, "Pot_Small", p.x, p.y);
			sprite.draw(c, res);
		}else{
			sprite.draw(c, res);
		}
	}
	
	public void Update(TextView txt, TextView txt2, ProgressBar pb){
		txt.setText(name);
		txt2.setText(currentXP+"/"+maxXP);
		pb.setProgress(100000*currentXP/maxXP);
		
	}
}
