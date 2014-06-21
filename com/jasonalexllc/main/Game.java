package com.jasonalexllc.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import com.jasonalexllc.tower.Tower;

/**
 * 
 * @author Jason Carrete
 * @since Jun 18, 2014
 */
public class Game extends JPanel implements MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = -1168167031210268222L;
	
	private Tile[][] grid;
	private Shop shop;
	private boolean paused = false;
	
	public Tower curTower;
	public Point curMousePos;
	
	public Game(Tile[][] grid, Shop s)
	{
		this.grid = grid;
		shop = s;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public Game(int interval, Tile[][] grid, Shop s)
	{
		this(grid, s);
		
		Runnable r = () -> 
		{
			while(true)
			{
				//when paused just infinitely repeat this loop until it is unpaused
				while(paused)
					Thread.yield();
				
				try
				{
					Thread.sleep(interval);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				
				this.repaint();
			}
		};
		
		new Thread(r, "Game Thread").start();
	}
	
	public void pause()
	{
		paused = true;
	}
	
	public void unpause()
	{
		paused = false;
	}
	
	public boolean isPaused()
	{
		return paused;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);
		
		//draw the grid
		for(Tile[] row : grid)
			for(Tile col : row)
				col.draw(g2);
		
		if(curTower != null)
			g2.drawImage(curTower.getImage(), curMousePos.x - 25, curMousePos.y - 25, this);
		
		shop.open(g2);
	}

	public void mouseClicked(MouseEvent e)
	{
		if(shop.opened)
		{
			int col = e.getPoint().x / 50 - 4, row = e.getPoint().y / 50 - 14;
			
			if(col >= 0 && row >= 0)
				curTower = shop.buyTower(row, col);
			
			shop.opened = false;
		}
		else if(curTower != null)
		{
			int col = e.getPoint().x / 50, row = e.getPoint().y / 50;
			
			if(grid[row][col].getType() != Tile.PATH && grid[row][col].getType() != Tile.FAULT)
				grid[row][col].addTower(curTower);
			
			curTower = null;
		}
			
		System.out.println(e.getPoint().x + " " + e.getPoint().y);
	}

	public void mousePressed(MouseEvent e)
	{
		
	}

	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	public void mouseEntered(MouseEvent e)
	{
		
	}
	
	public void mouseExited(MouseEvent e)
	{
		
	}

	public void mouseDragged(MouseEvent e)
	{
		
	}

	public void mouseMoved(MouseEvent e)
	{
		curMousePos = e.getPoint();
	}
}
