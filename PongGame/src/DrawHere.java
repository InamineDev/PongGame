
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

/**
 * This class does all the painting and object creation.
 * It creates the random balls for each object the amount of times as is dictated by ballCount
 * @author William Bartlett
 */
@SuppressWarnings("serial")
public class DrawHere extends JPanel implements ActionListener
{
	boolean singleplayer = true;
	Projectile prj;
	static Paddle paddle1;
	
	/** The height of the screen */
	static int height;
	/** The width of the screen */
	static int width;
	
	/**
	 * This class creates all of the objects with random velocities. Once created it starts their processes and gets the focus of the window.
	 */
	public DrawHere()
	{
		int maxVel = 5;
		int minVel = 3;
		Random r = new Random();
		prj = new Ball("Bottle" + 1, r.nextInt(maxVel - minVel) + minVel,
					r.nextInt(maxVel - minVel) + minVel);

		prj.start();
		paddle1 = new Paddle(200, true, 3, 275);
		paddle1.start();
		this.addKeyListener(paddle1);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	/**
	 * This method takes graphics and paints the objects that were created in DrawHere
	 */
	public void paintComponent(Graphics g)
	{
		SinglePlayer.width = this.getWidth();
		SinglePlayer.height = this.getHeight();

		BufferedImage bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_BGR);
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		// Draw on the buffer
		prj.Paint(g2d);
		paddle1.Paint(g2d, this);
		Graphics2D g2dComponent = (Graphics2D) g;
		g2dComponent.drawImage(bufferedImage, null, 0, 0);
	}

	/**
	 * This method is called when the timer fires The Timer sets off an
	 * "actionPerformed" event every so many milliseconds.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.repaint();
		if (!this.isFocusOwner())
		{
			this.requestFocusInWindow();
		}
	}
	
}
