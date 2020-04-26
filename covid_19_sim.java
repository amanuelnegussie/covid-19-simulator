import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Random;

public class Game extends JComponent {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    //Title of the window
    String title = "2D Stacks";
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    // YOUR GAME VARIABLES WOULD GO HERE
//draw player
    Rectangle player = new Rectangle(400, 300, 50, 50);
//gravity strength
    int gravity = 1;
    Rectangle[] leftBlocks = new Rectangle[24];
    Rectangle[] rightBlocks = new Rectangle[24];

//boolean for loop
    boolean startGame = false;
    //speed of player
    int playerSpeed = 5;
    int playerXDirection = 1;
    int playerYDirection = 1;
    int playerDX = 0;
    int playerDY = 0;
    boolean jump = false;
    boolean rightCollision = false;
    boolean leftCollision = false;
    //player score
    int playerScore = 0;
    int highScore = 0;
    boolean r = false;
    boolean s = false;
    // GAME VARIABLES END HERE   
    // Constructor to create the Frame and place the panel in
    // You will learn more about this in Grade 12 :)

    public Game() {
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
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        //start game 

        if (!startGame) {

            g.drawString("Press Enter to Start", 400, 300);
            g.drawString("Your Previous Score is: " + playerScore, 400, 325);

        } else {
            g.setColor(Color.BLACK);
            for (int i = 0; i < 24; i++) {
                if (playerXDirection == -1) {
                    g.fillRect(leftBlocks[i].x, leftBlocks[i].y, leftBlocks[i].width, leftBlocks[i].height);
                }
            }
            for (int i = 0; i < 24; i++) {
                //show  right blocks if the player is going to the right
                if (playerXDirection == 1) {

                    g.fillRect(rightBlocks[i].x, rightBlocks[i].y, rightBlocks[i].width, rightBlocks[i].height);
                }
            }
            g.fillRect(player.x, player.y, player.width, player.height);

            g.drawString("Your score is: " + playerScore, 400, 150);
            g.drawString("Press Space To Jump", 400, 175);
        }
    }
    // GAME DRAWING ENDS HERE

    // This method is used to do any pre-setup you might need to do
    // This is run before the game loop begins!
    public void preSetup() {

        // Any of your pre setup before the loop starts should go here
//left block row
        leftBlocks[0] = new Rectangle(-50, 0, 25, 25);
        leftBlocks[1] = new Rectangle(-50, 25, 25, 25);
        leftBlocks[2] = new Rectangle(-50, 50, 25, 25);
        leftBlocks[3] = new Rectangle(-50, 75, 25, 25);
        leftBlocks[4] = new Rectangle(-50, 100, 25, 25);
        leftBlocks[5] = new Rectangle(-50, 125, 25, 25);
        leftBlocks[6] = new Rectangle(-50, 150, 25, 25);
        leftBlocks[7] = new Rectangle(-50, 175, 25, 25);
        leftBlocks[8] = new Rectangle(-50, 200, 25, 25);
        leftBlocks[9] = new Rectangle(-50, 225, 25, 25);
        leftBlocks[10] = new Rectangle(-50, 250, 25, 25);
        leftBlocks[11] = new Rectangle(-50, 275, 25, 25);
        leftBlocks[12] = new Rectangle(-50, 300, 25, 25);
        leftBlocks[13] = new Rectangle(-50, 325, 25, 25);
        leftBlocks[14] = new Rectangle(-50, 350, 25, 25);
        leftBlocks[15] = new Rectangle(-50, 375, 25, 25);
        leftBlocks[16] = new Rectangle(-50, 400, 25, 25);
        leftBlocks[17] = new Rectangle(-50, 425, 25, 25);
        leftBlocks[18] = new Rectangle(-50, 450, 25, 25);
        leftBlocks[19] = new Rectangle(-50, 475, 25, 25);
        leftBlocks[20] = new Rectangle(-50, 500, 25, 25);
        leftBlocks[21] = new Rectangle(-50, 525, 25, 25);
        leftBlocks[22] = new Rectangle(-50, 550, 25, 25);
        leftBlocks[23] = new Rectangle(-50, 575, 25, 25);
        //right block row
        rightBlocks[0] = new Rectangle(825, 0, 25, 25);
        rightBlocks[1] = new Rectangle(825, 25, 25, 25);
        rightBlocks[2] = new Rectangle(825, 50, 25, 25);
        rightBlocks[3] = new Rectangle(825, 75, 25, 25);
        rightBlocks[4] = new Rectangle(825, 100, 25, 25);
        rightBlocks[5] = new Rectangle(825, 125, 25, 25);
        rightBlocks[6] = new Rectangle(825, 150, 25, 25);
        rightBlocks[7] = new Rectangle(825, 175, 25, 25);
        rightBlocks[8] = new Rectangle(825, 200, 25, 25);
        rightBlocks[9] = new Rectangle(825, 225, 25, 25);
        rightBlocks[10] = new Rectangle(825, 250, 25, 25);
        rightBlocks[11] = new Rectangle(825, 275, 25, 25);
        rightBlocks[12] = new Rectangle(825, 300, 25, 25);
        rightBlocks[13] = new Rectangle(825, 325, 25, 25);
        rightBlocks[14] = new Rectangle(825, 350, 25, 25);
        rightBlocks[15] = new Rectangle(825, 375, 25, 25);
        rightBlocks[16] = new Rectangle(825, 400, 25, 25);
        rightBlocks[17] = new Rectangle(825, 425, 25, 25);
        rightBlocks[18] = new Rectangle(825, 450, 25, 25);
        rightBlocks[19] = new Rectangle(825, 475, 25, 25);
        rightBlocks[20] = new Rectangle(825, 500, 25, 25);
        rightBlocks[21] = new Rectangle(825, 525, 25, 25);
        rightBlocks[22] = new Rectangle(825, 550, 25, 25);
        rightBlocks[23] = new Rectangle(825, 575, 25, 25);

    }

    // The main game loop
    // In here is where all the logic for my game will go
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
            if (startGame) {

                if (player.x < 0) {
                    int a = (int) (Math.random() * 24);
                    int b = (int) (Math.random() * 24);
                    int c = (int) (Math.random() * 24);
                    int d = (int) (Math.random() * 24);
                    int e = (int) (Math.random() * 24);
                    int f = (int) (Math.random() * 24);
                    int g = (int) (Math.random() * 24);

                    rightBlocks[a].x = 775;
                    rightBlocks[b].x = 775;
                    rightBlocks[c].x = 775;
                    rightBlocks[d].x = 775;
                    rightBlocks[e].x = 775;
                    rightBlocks[f].x = 775;
                    rightBlocks[g].x = 775;

                }

                if (player.x + player.width > 800) {
                    int i = (int) (Math.random() * 24);
                    int j = (int) (Math.random() * 24);
                    int k = (int) (Math.random() * 24);
                    int l = (int) (Math.random() * 24);
                    int m = (int) (Math.random() * 24);
                    int n = (int) (Math.random() * 24);
                    int o = (int) (Math.random() * 24);

                    leftBlocks[i].x = 0;
                    leftBlocks[j].x = 0;
                    leftBlocks[k].x = 0;
                    leftBlocks[l].x = 0;
                    leftBlocks[m].x = 0;
                    leftBlocks[n].x = 0;
                    leftBlocks[o].x = 0;
                }
//apply gravity
                if (jump) {
                    playerDY = -15;
                }

//update the player
                playerDY += gravity;
                player.x = player.x + playerDX;
                player.y = player.y + playerDY;

                //end game if block goes out of play
                if (player.y + player.height < 0 || player.y > HEIGHT) {
                    startGame = false;

                    int a = (int) (Math.random() * 24);
                    int b = (int) (Math.random() * 24);
                    int c = (int) (Math.random() * 24);
                    int d = (int) (Math.random() * 24);
                    int e = (int) (Math.random() * 24);
                    int f = (int) (Math.random() * 24);
                    int g = (int) (Math.random() * 24);
                    int i = (int) (Math.random() * 24);
                    int j = (int) (Math.random() * 24);
                    int k = (int) (Math.random() * 24);
                    int l = (int) (Math.random() * 24);
                    int m = (int) (Math.random() * 24);
                    int n = (int) (Math.random() * 24);
                    int o = (int) (Math.random() * 24);
                    player.y = 300;
                    player.x = 400;
                    playerSpeed = 5;
                    gravity = 1;
                    playerSpeed = 5;
                    gravity = 1;
                    playerXDirection = 1;
                    playerYDirection = 1;
                    playerDX = 0;
                    playerDY = 0;

                    leftBlocks[0] = new Rectangle(-50, 0, 25, 25);
                    leftBlocks[1] = new Rectangle(-50, 25, 25, 25);
                    leftBlocks[2] = new Rectangle(-50, 50, 25, 25);
                    leftBlocks[3] = new Rectangle(-50, 75, 25, 25);
                    leftBlocks[4] = new Rectangle(-50, 100, 25, 25);
                    leftBlocks[5] = new Rectangle(-50, 125, 25, 25);
                    leftBlocks[6] = new Rectangle(-50, 150, 25, 25);
                    leftBlocks[7] = new Rectangle(-50, 175, 25, 25);
                    leftBlocks[8] = new Rectangle(-50, 200, 25, 25);
                    leftBlocks[9] = new Rectangle(-50, 225, 25, 25);
                    leftBlocks[10] = new Rectangle(-50, 250, 25, 25);
                    leftBlocks[11] = new Rectangle(-50, 275, 25, 25);
                    leftBlocks[12] = new Rectangle(-50, 300, 25, 25);
                    leftBlocks[13] = new Rectangle(-50, 325, 25, 25);
                    leftBlocks[14] = new Rectangle(-50, 350, 25, 25);
                    leftBlocks[15] = new Rectangle(-50, 375, 25, 25);
                    leftBlocks[16] = new Rectangle(-50, 400, 25, 25);
                    leftBlocks[17] = new Rectangle(-50, 425, 25, 25);
                    leftBlocks[18] = new Rectangle(-50, 450, 25, 25);
                    leftBlocks[19] = new Rectangle(-50, 475, 25, 25);
                    leftBlocks[20] = new Rectangle(-50, 500, 25, 25);
                    leftBlocks[21] = new Rectangle(-50, 525, 25, 25);
                    leftBlocks[22] = new Rectangle(-50, 550, 25, 25);
                    leftBlocks[23] = new Rectangle(-50, 575, 25, 25);

                    rightBlocks[0] = new Rectangle(825, 0, 25, 25);
                    rightBlocks[1] = new Rectangle(825, 25, 25, 25);
                    rightBlocks[2] = new Rectangle(825, 50, 25, 25);
                    rightBlocks[3] = new Rectangle(825, 75, 25, 25);
                    rightBlocks[4] = new Rectangle(825, 100, 25, 25);
                    rightBlocks[5] = new Rectangle(825, 125, 25, 25);
                    rightBlocks[6] = new Rectangle(825, 150, 25, 25);
                    rightBlocks[7] = new Rectangle(825, 175, 25, 25);
                    rightBlocks[8] = new Rectangle(825, 200, 25, 25);
                    rightBlocks[9] = new Rectangle(825, 225, 25, 25);
                    rightBlocks[10] = new Rectangle(825, 250, 25, 25);
                    rightBlocks[11] = new Rectangle(825, 275, 25, 25);
                    rightBlocks[12] = new Rectangle(825, 300, 25, 25);
                    rightBlocks[13] = new Rectangle(825, 325, 25, 25);
                    rightBlocks[14] = new Rectangle(825, 350, 25, 25);
                    rightBlocks[15] = new Rectangle(825, 375, 25, 25);
                    rightBlocks[16] = new Rectangle(825, 400, 25, 25);
                    rightBlocks[17] = new Rectangle(825, 425, 25, 25);
                    rightBlocks[18] = new Rectangle(825, 450, 25, 25);
                    rightBlocks[19] = new Rectangle(825, 475, 25, 25);
                    rightBlocks[20] = new Rectangle(825, 500, 25, 25);
                    rightBlocks[21] = new Rectangle(825, 525, 25, 25);
                    rightBlocks[22] = new Rectangle(825, 550, 25, 25);
                    rightBlocks[23] = new Rectangle(825, 575, 25, 25);

                    r = false;
                    s = false;
                }

                //move player horizontally
                //move the ball
                player.x = player.x + playerXDirection * playerSpeed;
                if (player.x + player.width > WIDTH) {
                    playerXDirection = playerXDirection * -1;

                    int i = (int) (Math.random() * 24);
                    int j = (int) (Math.random() * 24);
                    int k = (int) (Math.random() * 24);
                    int l = (int) (Math.random() * 24);
                    int m = (int) (Math.random() * 24);
                    int n = (int) (Math.random() * 24);
                    int o = (int) (Math.random() * 24);
                    playerScore += 1;
                }
                if (player.x < 0) {
                    playerXDirection = playerXDirection * -1;
                    leftBlocks[0] = new Rectangle(-50, 0, 25, 25);
                    leftBlocks[1] = new Rectangle(-50, 25, 25, 25);
                    leftBlocks[2] = new Rectangle(-50, 50, 25, 25);
                    leftBlocks[3] = new Rectangle(-50, 75, 25, 25);
                    leftBlocks[4] = new Rectangle(-50, 100, 25, 25);
                    leftBlocks[5] = new Rectangle(-50, 125, 25, 25);
                    leftBlocks[6] = new Rectangle(-50, 150, 25, 25);
                    leftBlocks[7] = new Rectangle(-50, 175, 25, 25);
                    leftBlocks[8] = new Rectangle(-50, 200, 25, 25);
                    leftBlocks[9] = new Rectangle(-50, 225, 25, 25);
                    leftBlocks[10] = new Rectangle(-50, 250, 25, 25);
                    leftBlocks[11] = new Rectangle(-50, 275, 25, 25);
                    leftBlocks[12] = new Rectangle(-50, 300, 25, 25);
                    leftBlocks[13] = new Rectangle(-50, 325, 25, 25);
                    leftBlocks[14] = new Rectangle(-50, 350, 25, 25);
                    leftBlocks[15] = new Rectangle(-50, 375, 25, 25);
                    leftBlocks[16] = new Rectangle(-50, 400, 25, 25);
                    leftBlocks[17] = new Rectangle(-50, 425, 25, 25);
                    leftBlocks[18] = new Rectangle(-50, 450, 25, 25);
                    leftBlocks[19] = new Rectangle(-50, 475, 25, 25);
                    leftBlocks[20] = new Rectangle(-50, 500, 25, 25);
                    leftBlocks[21] = new Rectangle(-50, 525, 25, 25);
                    leftBlocks[22] = new Rectangle(-50, 550, 25, 25);
                    leftBlocks[23] = new Rectangle(-50, 575, 25, 25);

                    rightBlocks[0] = new Rectangle(825, 0, 25, 25);
                    rightBlocks[1] = new Rectangle(825, 25, 25, 25);
                    rightBlocks[2] = new Rectangle(825, 50, 25, 25);
                    rightBlocks[3] = new Rectangle(825, 75, 25, 25);
                    rightBlocks[4] = new Rectangle(825, 100, 25, 25);
                    rightBlocks[5] = new Rectangle(825, 125, 25, 25);
                    rightBlocks[6] = new Rectangle(825, 150, 25, 25);
                    rightBlocks[7] = new Rectangle(825, 175, 25, 25);
                    rightBlocks[8] = new Rectangle(825, 200, 25, 25);
                    rightBlocks[9] = new Rectangle(825, 225, 25, 25);
                    rightBlocks[10] = new Rectangle(825, 250, 25, 25);
                    rightBlocks[11] = new Rectangle(825, 275, 25, 25);
                    rightBlocks[12] = new Rectangle(825, 300, 25, 25);
                    rightBlocks[13] = new Rectangle(825, 325, 25, 25);
                    rightBlocks[14] = new Rectangle(825, 350, 25, 25);
                    rightBlocks[15] = new Rectangle(825, 375, 25, 25);
                    rightBlocks[16] = new Rectangle(825, 400, 25, 25);
                    rightBlocks[17] = new Rectangle(825, 425, 25, 25);
                    rightBlocks[18] = new Rectangle(825, 450, 25, 25);
                    rightBlocks[19] = new Rectangle(825, 475, 25, 25);
                    rightBlocks[20] = new Rectangle(825, 500, 25, 25);
                    rightBlocks[21] = new Rectangle(825, 525, 25, 25);
                    rightBlocks[22] = new Rectangle(825, 550, 25, 25);
                    rightBlocks[23] = new Rectangle(825, 575, 25, 25);

                    int a = (int) (Math.random() * 24);
                    int b = (int) (Math.random() * 24);
                    int c = (int) (Math.random() * 24);
                    int d = (int) (Math.random() * 24);
                    int e = (int) (Math.random() * 24);
                    int f = (int) (Math.random() * 24);
                    int g = (int) (Math.random() * 24);
                    playerScore += 1;

                }

                //Left Block collisions
                for (int i = 0; i < 24; i++) {
                    //did the player hit a blcok
                    if (player.intersects(leftBlocks[i])) {
                        int cHeight = Math.min(leftBlocks[i].y + leftBlocks[i].height, player.y + player.height) - Math.max(leftBlocks[i].y, player.y);
                        int cWidth = Math.min(leftBlocks[i].x + leftBlocks[i].width, player.x + player.width) - Math.max(leftBlocks[i].x, player.x);

                        //dtermine the smaller one to fi
                        if (cWidth < cHeight) {
                            //fix te width
                            //player on left side
                            if (player.x < leftBlocks[i].x) {
                                player.x = player.x - cWidth;
                            } else {
                                player.x = player.x + cWidth;
                            }

                        } else {
                            if (player.y < leftBlocks[i].y) {
                                player.y = player.y - cHeight;

                                //moving down?
                                if (playerDY >= 0) {
                                    //stop the down motion
                                    playerDY = 0;
                                    //standing on block
                                    leftCollision = true;
                                }
                            } else {
                                player.y = player.y + cHeight;
                            }
                        }
                        leftCollision = true;
                    }

                }
                //Right Block collisions
                for (int i = 0; i < 24; i++) {

                    if (player.intersects(rightBlocks[i])) {

                        int bHeight = Math.min(rightBlocks[i].y + rightBlocks[i].height, player.y + player.height) - Math.max(rightBlocks[i].y, player.y);
                        int bWidth = Math.min(rightBlocks[i].x + rightBlocks[i].width, player.x + player.width) - Math.max(rightBlocks[i].x, player.x);
                        //dtermine the smaller one to fi
                        if (bWidth < bHeight) {
                            //fix te width
                            //player on left side
                            if (player.x - player.height < rightBlocks[i].x) {
                                player.x = player.x - bWidth;
                            } else {
                                player.x = player.x + bWidth;
                            }
                        } else {
                            if (player.y - player.width < rightBlocks[i].y) {
                                player.y = player.y - bHeight;

                                //moving down?
                                if (playerDY >= 0) {
                                    //stop the down motion
                                    playerDY = 0;

                                }
                            } else {
                                player.y = player.y + bHeight;
                            }
                        }
                        rightCollision = true;
                    }

                    //highscore
                }
                //restart game if 
                if (leftCollision == true) {
                    startGame = false;

                    player.y = 300;
                    player.x = 400;
                    playerSpeed = 5;
                    gravity = 1;
                    playerSpeed = 5;
                    gravity = 1;
                    playerXDirection = 1;
                    playerYDirection = 1;
                    playerDX = 0;
                    playerDY = 0;

                    int i = (int) (Math.random() * 24);
                    int j = (int) (Math.random() * 24);
                    int k = (int) (Math.random() * 24);
                    int l = (int) (Math.random() * 24);
                    int m = (int) (Math.random() * 24);
                    int n = (int) (Math.random() * 24);
                    int o = (int) (Math.random() * 24);
                    leftBlocks[0] = new Rectangle(-50, 0, 25, 25);
                    leftBlocks[1] = new Rectangle(-50, 25, 25, 25);
                    leftBlocks[2] = new Rectangle(-50, 50, 25, 25);
                    leftBlocks[3] = new Rectangle(-50, 75, 25, 25);
                    leftBlocks[4] = new Rectangle(-50, 100, 25, 25);
                    leftBlocks[5] = new Rectangle(-50, 125, 25, 25);
                    leftBlocks[6] = new Rectangle(-50, 150, 25, 25);
                    leftBlocks[7] = new Rectangle(-50, 175, 25, 25);
                    leftBlocks[8] = new Rectangle(-50, 200, 25, 25);
                    leftBlocks[9] = new Rectangle(-50, 225, 25, 25);
                    leftBlocks[10] = new Rectangle(-50, 250, 25, 25);
                    leftBlocks[11] = new Rectangle(-50, 275, 25, 25);
                    leftBlocks[12] = new Rectangle(-50, 300, 25, 25);
                    leftBlocks[13] = new Rectangle(-50, 325, 25, 25);
                    leftBlocks[14] = new Rectangle(-50, 350, 25, 25);
                    leftBlocks[15] = new Rectangle(-50, 375, 25, 25);
                    leftBlocks[16] = new Rectangle(-50, 400, 25, 25);
                    leftBlocks[17] = new Rectangle(-50, 425, 25, 25);
                    leftBlocks[18] = new Rectangle(-50, 450, 25, 25);
                    leftBlocks[19] = new Rectangle(-50, 475, 25, 25);
                    leftBlocks[20] = new Rectangle(-50, 500, 25, 25);
                    leftBlocks[21] = new Rectangle(-50, 525, 25, 25);
                    leftBlocks[22] = new Rectangle(-50, 550, 25, 25);
                    leftBlocks[23] = new Rectangle(-50, 575, 25, 25);
                    leftCollision = false;
                    r = false;
                    s = false;

                }
                if (rightCollision == true) {
                    startGame = false;

                    player.y = 300;
                    player.x = 400;
                    playerSpeed = 5;
                    gravity = 1;
                    playerSpeed = 5;
                    gravity = 1;
                    playerXDirection = 1;
                    playerYDirection = 1;
                    playerDX = 0;
                    playerDY = 0;

                    leftBlocks[0] = new Rectangle(-50, 0, 25, 25);
                    leftBlocks[1] = new Rectangle(-50, 25, 25, 25);
                    leftBlocks[2] = new Rectangle(-50, 50, 25, 25);
                    leftBlocks[3] = new Rectangle(-50, 75, 25, 25);
                    leftBlocks[4] = new Rectangle(-50, 100, 25, 25);
                    leftBlocks[5] = new Rectangle(-50, 125, 25, 25);
                    leftBlocks[6] = new Rectangle(-50, 150, 25, 25);
                    leftBlocks[7] = new Rectangle(-50, 175, 25, 25);
                    leftBlocks[8] = new Rectangle(-50, 200, 25, 25);
                    leftBlocks[9] = new Rectangle(-50, 225, 25, 25);
                    leftBlocks[10] = new Rectangle(-50, 250, 25, 25);
                    leftBlocks[11] = new Rectangle(-50, 275, 25, 25);
                    leftBlocks[12] = new Rectangle(-50, 300, 25, 25);
                    leftBlocks[13] = new Rectangle(-50, 325, 25, 25);
                    leftBlocks[14] = new Rectangle(-50, 350, 25, 25);
                    leftBlocks[15] = new Rectangle(-50, 375, 25, 25);
                    leftBlocks[16] = new Rectangle(-50, 400, 25, 25);
                    leftBlocks[17] = new Rectangle(-50, 425, 25, 25);
                    leftBlocks[18] = new Rectangle(-50, 450, 25, 25);
                    leftBlocks[19] = new Rectangle(-50, 475, 25, 25);
                    leftBlocks[20] = new Rectangle(-50, 500, 25, 25);
                    leftBlocks[21] = new Rectangle(-50, 525, 25, 25);
                    leftBlocks[22] = new Rectangle(-50, 550, 25, 25);
                    leftBlocks[23] = new Rectangle(-50, 575, 25, 25);

                    rightBlocks[0] = new Rectangle(825, 0, 25, 25);
                    rightBlocks[1] = new Rectangle(825, 25, 25, 25);
                    rightBlocks[2] = new Rectangle(825, 50, 25, 25);
                    rightBlocks[3] = new Rectangle(825, 75, 25, 25);
                    rightBlocks[4] = new Rectangle(825, 100, 25, 25);
                    rightBlocks[5] = new Rectangle(825, 125, 25, 25);
                    rightBlocks[6] = new Rectangle(825, 150, 25, 25);
                    rightBlocks[7] = new Rectangle(825, 175, 25, 25);
                    rightBlocks[8] = new Rectangle(825, 200, 25, 25);
                    rightBlocks[9] = new Rectangle(825, 225, 25, 25);
                    rightBlocks[10] = new Rectangle(825, 250, 25, 25);
                    rightBlocks[11] = new Rectangle(825, 275, 25, 25);
                    rightBlocks[12] = new Rectangle(825, 300, 25, 25);
                    rightBlocks[13] = new Rectangle(825, 325, 25, 25);
                    rightBlocks[14] = new Rectangle(825, 350, 25, 25);
                    rightBlocks[15] = new Rectangle(825, 375, 25, 25);
                    rightBlocks[16] = new Rectangle(825, 400, 25, 25);
                    rightBlocks[17] = new Rectangle(825, 425, 25, 25);
                    rightBlocks[18] = new Rectangle(825, 450, 25, 25);
                    rightBlocks[19] = new Rectangle(825, 475, 25, 25);
                    rightBlocks[20] = new Rectangle(825, 500, 25, 25);
                    rightBlocks[21] = new Rectangle(825, 525, 25, 25);
                    rightBlocks[22] = new Rectangle(825, 550, 25, 25);
                    rightBlocks[23] = new Rectangle(825, 575, 25, 25);

                    int a = (int) (Math.random() * 24);
                    int b = (int) (Math.random() * 24);
                    int c = (int) (Math.random() * 24);
                    int d = (int) (Math.random() * 24);
                    int e = (int) (Math.random() * 24);
                    int f = (int) (Math.random() * 24);
                    int g = (int) (Math.random() * 24);

                    rightCollision = false;
                    r = false;
                    s = false;
                }

                // GAME LOGIC ENDS HERE 
                // update the drawing (calls paintComponent)
                repaint();

                // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
                // USING SOME SIMPLE MATH
                deltaTime = System.currentTimeMillis() - startTime;
                try {
                    if (deltaTime > desiredTime) {
                        //took too much time, don't wait
                        Thread.sleep(1);
                    } else {
                        // sleep to make up the extra time
                        Thread.sleep(desiredTime - deltaTime);
                    }
                } catch (Exception e) {
                };

            }

            // GAME LOGIC ENDS HERE 
            // update the drawing (calls paintComponent)
            repaint();

            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            try {
                if (deltaTime > desiredTime) {
                    //took too much time, don't wait
                    Thread.sleep(1);
                } else {
                    // sleep to make up the extra time
                    Thread.sleep(desiredTime - deltaTime);
                }
            } catch (Exception e) {
            };
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
        // if a key has been pressed down

        @Override
        public void keyPressed(KeyEvent x) {
            //store the key being pressed
            int key = x.getKeyCode();
            //determine which key it is

            if (key == KeyEvent.VK_SPACE) {

                jump = true;

            }
            //start key

            int start = x.getKeyCode();
            if (start == KeyEvent.VK_ENTER) {

                if (key == KeyEvent.VK_ENTER) {
                    int a = (int) (Math.random() * 24);
                    int b = (int) (Math.random() * 24);
                    int c = (int) (Math.random() * 24);
                    int d = (int) (Math.random() * 24);
                    int e = (int) (Math.random() * 24);
                    int f = (int) (Math.random() * 24);
                    int g = (int) (Math.random() * 24);

                    rightBlocks[a].x = 775;
                    rightBlocks[b].x = 775;
                    rightBlocks[c].x = 775;
                    rightBlocks[d].x = 775;
                    rightBlocks[e].x = 775;
                    rightBlocks[f].x = 775;
                    rightBlocks[g].x = 775;
                }
                startGame = true;
                playerScore = 0;

            }
        }
        // if a key has been released

        @Override
        public void keyReleased(KeyEvent e) {

            //store the key being pressed
            int key = e.getKeyCode();
            //determine which key it is
            if (key == KeyEvent.VK_SPACE) {
                jump = false;

            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}