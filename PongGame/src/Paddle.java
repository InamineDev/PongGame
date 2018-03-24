import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle extends Thread implements KeyListener
{
	int size, speed, location, dx, score;
	boolean firstPlayer;
	
	Paddle (int size, boolean firstPlayer, int speed, int location)
	{
		this.size = size;
		this.speed = speed;
		this.firstPlayer = firstPlayer;
		this.location = location;
	}
	
	public void run()
	{
		boolean active = true;
		while(active)
		{
			move(this);
			sleepAlittle(5);
		}
	}
	
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

	public void move(Paddle paddle) 
	{
		int newloc = paddle.getLocation() + (paddle.getdx() * paddle.getSpeed());
		if (newloc < 0)
		{
			newloc = 0;
		}
		else if (newloc+200 > SinglePlayer.width)
		{
			newloc = (SinglePlayer.width-200);
		}
		
	    paddle.setLocation(newloc);
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getdx() {
		return dx;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean isFirstPlayer() {
		return firstPlayer;
	}



	@Override
	public void keyPressed(KeyEvent e) 
	{

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			dx = 1;
		} 
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			dx = -1;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			dx = 0;
		} 
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			dx = 0;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	void Paint(Graphics g, DrawHere d)
	{
		g.setColor(Color.GREEN);
//		g.fillRect(d.getWidth()/2, d.getHeight()-25, size, 25);
		g.fillRect(location, d.getHeight()-45, size, 15);
		g.setColor(Color.WHITE);
		g.drawString("Points: " + score, 50, 50);

	}
	
}
