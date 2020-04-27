import javax.swing.JComponent;
import javax.swing.JFrame;
import java.util.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Covid_19_Sim extends JComponent {

	
	private static final long serialVersionUID = 1L;
	// Height and Width of our game
	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	Random r = new Random();
	// Title of the window
	String title = "Covid-19 Simulator";
	// sets the framerate and delay for our game
	// you just need to select an approproate framerate
	long desiredFPS = 60;
	long desiredTime = (1000) / desiredFPS;
	// YOUR GAME VARIABLES WOULD GO HERE

	
	
	//Speed and angle min and max
	double min = -1.0;
	double max = 1.0;
	int number_of_circles = 50;

	//list of directions [x-axis,y-axis]
	double directions[][] = new double[number_of_circles][2];
	
	//list of collision booleans
	boolean collisions[] = new boolean[number_of_circles];
	
	
	//list of circles of type Ellipse2D
	Ellipse2D.Double circle[] = new Ellipse2D.Double[number_of_circles];

	
	boolean startGame = true;
	
	// GAME VARIABLES END HERE
	// Constructor to create the Frame and place the panel in
	// You will learn more about this in Grade 12 :)

	public Covid_19_Sim() {
		// creates a windows to show my game
		JFrame frame = new JFrame(title);

		// sets the size of my game
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		// adds the game to the window
		frame.add(this);

		// sets some options and size of the window automatically
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		// shows the window to the user
		frame.setVisible(true);

		// add listeners for keyboard and mouse
		frame.addKeyListener(new Keyboard());
		Mouse m = new Mouse();

		this.addMouseMotionListener(m);
		this.addMouseWheelListener(m);
		this.addMouseListener(m);
	}

	// drawing of the game happens in here
	// we use the Graphics object, g, to perform the drawing
	// NOTE: This is already double buffered!(helps with framerate/speed)

	//sets all the booleans in the list to false	
	public boolean[] generateBooleanArray(int number_of_circles) {
		for (int i = 0; i < number_of_circles; i++)
			collisions[i] = false;
		return collisions;
	}

	//generates a 2D list of random x and y values between -1 and 1;	
	public double[][] generateRandomDoubleArray(double min, double max, int number_of_circles) {
		for (int i = 0; i < number_of_circles; i++) {
			double x = (Math.random() * (max - min) + min);
			double y = (Math.random() * (max - min) + min);
			directions[i][0] = x;
			directions[i][1] = y;
		}
		return directions;
	}

	
	@Override
	public void paintComponent(Graphics g) {
	// GUI DRAWING START HERE
		Graphics2D g2 = (Graphics2D) g;
		// draws the circles
		for (int i = 0; i < circle.length; i++) {
			g2.draw(circle[i]);
			g2.setPaint(Color.BLACK);
			g2.fill(circle[i]);
			if (collisions[i]) {
				g2.setPaint(Color.RED);
				g2.fill(circle[i]);
			}

		}
	}

	// GUI DRAWING ENDS HERE

	// This method is used to do any pre-setup you might need to do
	// This is run before the game loop begins!
	public void preSetup() {

		// generates an 2D array with random x and y values
		directions = generateRandomDoubleArray(min, max, number_of_circles);
		collisions = generateBooleanArray(number_of_circles);

		// generates circles in random positions
		for (int i = 0; i < circle.length; i++) {
			circle[i] = new Ellipse2D.Double(r.nextDouble() * 800, r.nextDouble() * 600, 10.0, 10.0);
		}

	}

	// The main loop
	// In here is where all the logic for my app will go
	public void run() {
		// Used to keep track of time used to draw and update the game
		// This is used to limit the framerate later on
		long startTime;
		long deltaTime;

		preSetup();

		// the main game loop section
		// game will end if you set done = false;
		boolean done = false;
		while (!done) {
			// determines when we started so we can keep a framerate
			startTime = System.currentTimeMillis();

			// all your game rules and move is done in here
			// GAME LOGIC STARTS HERE

			for (int i = 0; i < circle.length; i++) {

				// moves the circle at random directions 
				circle[i].x += directions[i][0];
				circle[i].y += directions[i][1];

				// changes direction of the x-access if the circle goes outside the width of the screen
				if (circle[i].x + circle[i].width > WIDTH || circle[i].x < 0) {
					directions[i][0] *= -1;
				}

				// changes direction of the y-axis if the circle goes outside the height of the screen
				if (circle[i].y + circle[i].height > HEIGHT || circle[i].y < 0) {
					directions[i][1] *= -1;
				}

				
				//COLLISION DETECTOR
				for (int j = 0; j != i && j < circle.length; j++) {
					if (circle[i].intersects(circle[j].x, circle[j].y, circle[j].width, circle[j].height)) {
						
						int cHeight = (int) (Math.min(circle[j].y + circle[j].height, circle[i].y + circle[i].height)
								- Math.max(circle[j].y, circle[i].y));
						int cWidth = (int) (Math.min(circle[j].x + circle[j].width, circle[i].x + circle[i].width)
								- Math.max(circle[j].x, circle[i].x));

//
						if (cWidth < cHeight) {
							//if the collision is on the left/right side of the circles, negate the x direction
							directions[i][0] *= -1;
							directions[j][0] *= -1;

							if (circle[i].x < circle[j].x) {
								circle[i].x = circle[i].x - cWidth;

							} else {
								circle[i].x = circle[i].x + cWidth;

							}
						} else {
							//if the collision is on the top/bottom side of the circles, negate the y direction
							directions[i][1] *= -1;
							directions[j][1] *= -1;
							if (circle[i].y < circle[j].y) {

								circle[i].y = circle[i].y - cHeight;
								// moving down?
								if (directions[i][1] >= 0.0) {
									collisions[i] = true;
									collisions[j] = true;
								}
							} else {
								circle[i].y = circle[i].y + cHeight;
							}
						}
						
						//set the collision at that circle index to true
						collisions[i] = true;
						collisions[j] = true;
					}
				}
			}

			// GAME LOGIC ENDS HERE
			repaint();
			// update the drawing (calls paintComponent)

			// SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
			// USING SOME SIMPLE MATH
			deltaTime = System.currentTimeMillis() - startTime;
			try {
				if (deltaTime > desiredTime) {
					// took too much time, don't wait
					Thread.sleep(1);
				} else {
					// sleep to make up the extra time
					Thread.sleep(desiredTime - deltaTime);
				}
			} catch (Exception e) {
			}
			;

		}
	}

// Used to implement any of the Mouse Actions
	private class Mouse extends MouseAdapter {
		// if a mouse button has been pressed down

		@Override
		public void mousePressed(MouseEvent e) {
		}

		// if a mouse button has been released
		@Override
		public void mouseReleased(MouseEvent e) {
		}

		// if the scroll wheel has been moved
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
		}

		// if the mouse has moved positions
		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}

// Used to implements any of the Keyboard Actions
	private class Keyboard extends KeyAdapter {

	}

	/**
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		// Run the simulation
		Covid_19_Sim sim = new Covid_19_Sim();
		sim.run();
	}

}
