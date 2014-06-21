package com.jasonalexllc.main;

import java.awt.Color;
import java.awt.Graphics2D;
import com.jasonalexllc.tower.PickaxeTower;
import com.jasonalexllc.tower.Tower;

/**
 * 
 * @author Jason Carrete, Alex Berman
 * @since Jun 19, 2014
 */
public class Shop
{
	public boolean opened = false;
	public static int money = 850;
	
	Tower[][] towers = new Tower[2][8];
	
	public Shop()
	{
		towers[0][0] = new PickaxeTower();
	}
	
	public void open(Graphics2D g2)
	{
		if(opened)
		{
			g2.setColor(new Color(0, 0, 0, 100));
			g2.fillRect(200, 700, 400, 200);
			
			//Tower info display
			g2.fillRect(0, 700, 150, 100);
			
			for(int row = 0; row < towers.length; row++)
				for(int col = 0; col < towers[0].length; col++)
					if(towers[row][col] != null)
						g2.drawImage(towers[row][col].getImage(), col * 50 + 200, row * 50 + 700, null);
		}
	}
	
	public Tower buyTower(int row, int col)
	{
		if(money >= towers[row][col].getCost())
		{
			money -= towers[row][col].getCost();
			return towers[row][col];
		}
		else
			return null;
	}
}
