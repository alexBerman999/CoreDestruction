package com.jasonalexllc.main;

import java.awt.Graphics2D;
import java.net.URL;
import javax.swing.ImageIcon;
import com.jasonalexllc.tower.Tower;

/**
 * 
 * @author Jason Carrete, Alex Berman
 * @since Jun 18, 2014
 */
public class Tile extends ImageIcon
{
	private static final long serialVersionUID = 2780222818493615777L;
	public static final int PATH = 0, STONE = 1, MOUND = 2, FAULT = 3;

	private int x, y, type;
	private Tower tower;
	
	public Tile(URL path, int x, int y, int type)
	{
		super(path);
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getType()
	{
		return type;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean hasTower()
	{
		return tower != null;
	}
	
	public void addTower(Tower t)
	{
		tower = t;
	}
	
	public void removeTower()
	{
		tower = null;
	}
	
	public void draw(Graphics2D g2)
	{
		g2.drawImage(this.getImage(), x, y, null);
		if(hasTower())
			g2.drawImage(tower.getImage(), x, y, null);
	}
	
	public String toString()
	{
		return "x pos: " + x + "\ny pos: " + y;
	}
}
