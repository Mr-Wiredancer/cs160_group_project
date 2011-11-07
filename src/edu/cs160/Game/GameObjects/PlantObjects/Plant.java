package edu.cs160.Game.GameObjects.PlantObjects;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.widget.*;
import edu.cs160.Game.GameObjects.GameObjects;
import edu.cs160.Game.GameObjects.Sprites.Sprite;

public class Plant implements Comparable{
	public String name;
	public int currentXP,maxXP, growthStage;
	public boolean grounded = false;
	public Pot p;
	public Sprite sprite;
	public int id;
	
	public Plant(String name,int currentXP,int maxXP,int growthStage,int type){
		this.name = name;
		this.currentXP=currentXP;
		this.maxXP = maxXP;
		this.growthStage = growthStage;
		this.sprite = PlantMaker.makePlant(type, growthStage);	
		if(growthStage<2){
			//this.grounded= true;
			p = new Pot(this,100,100);
		}
	}
	
	public void draw(Resources res, Canvas c){
		if(grounded){
			p.update();
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

	@Override
	public int compareTo(Object arg0) {
		Plant comp = (Plant)arg0;
		if(sprite.y+sprite.imgHeight>comp.sprite.y+comp.sprite.imgHeight){
			return 1;
		}
		return -1;
	}
}
