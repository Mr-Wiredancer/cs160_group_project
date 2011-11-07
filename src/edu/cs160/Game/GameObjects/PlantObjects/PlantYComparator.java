package edu.cs160.Game.GameObjects.PlantObjects;

import java.util.Comparator;

public class PlantYComparator implements Comparator{

	@Override
	public int compare(Object lhs, Object rhs) {
		// TODO Auto-generated method stub
		Plant p1 = (Plant)lhs;
		Plant p2 = (Plant)rhs;
		
		if(p1.sprite.y>p2.sprite.y){
			return 1;
		}else if(p1.sprite.y<p2.sprite.y){
			return -1;
		}
		return 0;
	}

}
