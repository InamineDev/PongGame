

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * This is the object that is bounced around the screen. 
 * This is abstract, so there are
 * other objects under this that are made with some of their own variables and methods.
 * The objects that use extend this are Orange, Water Bomb, Bottle, and Flubber.
 * @author William Bartlett
 */
public abstract class Projectile extends Thread
{
	/** True/False Is the game running or not */
	volatile boolean active = false;
	/** The name of the projectile */
	protected String name;
	/** The delay/refresh rate of the movement */
	private int delay = 25;
	/** The velocity of this projectile on the x axis */
	private double xVel;
	/** The velocity of this projectile on the y axis */
	private double yVel;
	/** The amount the speed is modified when the ball hits the wall */
	protected double modifier;
	/** The location of the object on the x axis */
	protected double xLocation;
	/** The location of the object on the y axis */
	protected double yLocation;
	/** The colour of the projectile */
	protected Color color;
	/** Stores if the projectile has exploded or not */
	protected boolean explode = false;
	/** Stores if the projectile has hit something. This is reset after handling */
	protected boolean hit = false;
	protected boolean justHit = false;
	/** The number of times the object has hit the wall */
	protected int count = 0;
	/** The maximum number of times the ball can hit a wall before exploding */
	protected int hit_count = 5;
	/** 
	 * The maximum speed a projectile can go before exploding.
	 * Should only effect the flubber objects.
	 */
	protected double explode_speed = 20.0d;
	/** 
	 * Keeps track of which side the ball recently hit.
	 * This is to prevent the ball from hitting the same side before being able to move away.
	 * Was created to prevent a bug where the ball would hit the wall but would go too far past
	 * and would hit back and forth on the same boundary until it exploded 
	 * */
	protected int recentHit;
	/** The size of the projectile */
	final protected static int size = 40;

	
	/**
	 * This is the main object in this application.
	 * WaterBomb, Bottle, Orange, and Flubber all extend this object
	 * @param name Name of the object
	 * @param color Color of the object
	 * @param xVel Velocity on the x axis
	 * @param yVel Velocity on the y axis
	 * @param modifier The amount the objects speed changes on each hit. This is a multiplier for their speed.
	 */
	public Projectile(String name, Color color, double xVel, double yVel)
	{
		this.name = name;
		this.color = color;
		this.xVel = xVel;
		this.yVel = yVel;
	}
	
	@Override
	/**
	 * This starts the projectile on it's path to become a red circle on a screen.
	 * It sets up the random locations for each ball seperately so they appear on different parts of the window.
	 * This method also watches for when the object hits a wall and tracks the hit count.
	 * After this method runs its code it sleeps for an amount of time set by 'delay'.
	 * This method is also responsible for checking if the ball has exploded and setting that variable accordingly.
	 */
	public void run()
	{
		int edge = 7;
		int xMin = 0;
		int yMin = 0;
		xLocation = (double) SinglePlayer.width / 2;
		yLocation = (double) SinglePlayer.height / 4;
		active = true;
		while (active)
		{
			double xMax = SinglePlayer.width - size - edge;
			double yMax = SinglePlayer.height - size - edge;
			xLocation = xLocation + xVel;
			yLocation = yLocation + yVel;
			hit = hitWall(xMax, xMin, yMax, yMin);
			hitPaddle(DrawHere.paddle1);
			if (recentHit == 4 && hit)
			{
				DrawHere.paddle1.setScore(DrawHere.paddle1.getScore() + 1);
			}

			if (recentHit == 3 && hit)
			{
				DrawHere.paddle1.setScore(0);
			}
			hit = false;
			justHit = false;
			sleepAlittle(delay);
		}
	}
	

	private boolean hitPaddle(Paddle p1)
	{
		boolean isHit = false;
		if ((xLocation >= p1.getLocation() && xLocation <= (p1.getLocation() + p1.getSize() )) && yLocation >= (SinglePlayer.height - size - 50) && recentHit != 5)
		{
			recentHit = 5;
			yVel = -yVel;
			isHit = true;
			System.out.println("hit!");
		}
		return isHit;
	}

	/**
	 * This method checks if a ball has hit a wall. It uses an extra value saved in the object to check what wall the object has recently
	 * hit to make sure that it doesn't get stuck in an endless loop and explode on the first impact. 
	 * @param xMax The maximum x value
	 * @param xMin The minimum x value
	 * @param yMax The maximum y value
	 * @param yMin The minimum y valye
	 * @return Returns boolean if the ball has hit a wall or not.
	 */
	private boolean hitWall(double xMax, int xMin, double yMax, int yMin)
	{
		boolean isHit = false;
		// recentHit is used to make sure the object isn't hitting the same wall
		// multiple times before moving away enough.
		// This is mostly because when the ball goes past the line we catch it, but then
		// we reduce their speed so they sometimes
		// cannot escape before losing a lot of speed and making many hits.
		if (xLocation >= xMax && recentHit != 1)
		{
			recentHit = 1;
			xVel = -xVel;
			isHit = true;
		}
		if (xLocation <= xMin && recentHit != 2)
		{
			recentHit = 2;
			xVel = -xVel;
			isHit = true;
		}
		if (yLocation >= yMax && recentHit != 3)
		{
			recentHit = 3;
			yVel = -yVel;
			isHit = true;
		}
		if (yLocation <= yMin && recentHit != 4)
		{
			recentHit = 4;
			yVel = -yVel;
			isHit = true;
		}
		return isHit;
	}

	/**
	 * Sleeps for a set amount of time
	 * @param s Time in milliseconds
	 */
	private void sleepAlittle(int s)
	{
		try
		{
			sleep(s);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/** This is an abstract method that the object uses to paint itself on the screen */
	abstract void Paint(Graphics g);

}