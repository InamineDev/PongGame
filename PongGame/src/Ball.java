
import java.awt.Color;
import java.awt.Graphics;
/**
 * This object extends Projectile and when it hits it slows down.
 * The color is orange, and when it explodes it becomes red.
 * @author William Bartlett
 */
public class Ball extends Projectile
{

	public Ball(String name, int xv, int yv)
	{
		super(name, Color.ORANGE, xv, yv);
	}
	@Override
	void Paint(Graphics g)
	{
		if (explode)
		{
			color = Color.RED;
		}
		g.setColor(color);
		g.fillOval((int) xLocation, (int) yLocation, size, size);

	}
}
