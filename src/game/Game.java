// 325516193 Cathrine Abu-Elazam
package arkanoid.game;

import arkanoid.collections.GameEnvironment;
import arkanoid.collections.SpriteCollection;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
import arkanoid.geometry.Velocity;
import arkanoid.logic.AddBall;
import arkanoid.logic.BallRemover;
import arkanoid.logic.BlockRemover;
import arkanoid.logic.Collidable;
import arkanoid.logic.ScoreTrackingListener;
import arkanoid.sprites.Ball;
import arkanoid.sprites.Block;
import arkanoid.sprites.Paddle;
import arkanoid.sprites.ScoreIndicator;
import arkanoid.sprites.Sprite;
import arkanoid.utils.Counter;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The arkanoid.game.Game class represents the main game logic and elements.
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter remainingBlocks;
    private final Counter remainingBalls;
    private final Counter score;


    private static final int HEIGHT = 600;
    private static final int WIDTH = 800;
    private static final int MAX_ROWS = 6;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 30;
    private static final int MAX_BLOCKS = 12;
    private static final int FRAME_HEIGHT_WIDTH = 30;
    private static final int PADDLE_HEIGHT = 15;
    private static final int PADDLE_WIDTH = 150;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECONDS_PER_FRAME = 1000;

    /**
     * Constructs a new {@code arkanoid.game.Game} instance, initializing the sprites,
     * environment, and counters for blocks, balls, and score.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.score = new Counter();
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and arkanoid.sprites.Ball (and arkanoid.sprites.Paddle)
     * and add them to the game.
     */
    public void initialize() {

        // Create a arkanoid.logic.BlockRemover instance
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);

        // Create a arkanoid.logic.BallRemover instance
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);

        AddBall addBall = new AddBall(this, this.remainingBalls, environment);

        Ball newBall = new Ball(new Point(500, 350), 5, Color.WHITE, this.environment);
        newBall.setVelocity(new Velocity(4, 4));
        newBall.addHitListener(addBall);

        Block backGroundBlock = new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT),
                new Color(51, 204, 225), Color.BLUE);
        backGroundBlock.addToGame(this);


        Ball ball2 = new Ball(new Point(220, 250), 5, Color.WHITE, this.environment);
        ball2.setVelocity(new Velocity(4, 4));
        ball2.addToGame(this);
        ball2.addHitListener(ballRemover);
        this.remainingBalls.increase(1);

        Ball ball1 = new Ball(new Point(740, 300), 5, Color.WHITE, this.environment);
        ball1.setVelocity(new Velocity(4, 4));
        ball1.addToGame(this);
        ball1.addHitListener(ballRemover);
        this.remainingBalls.increase(1);

        Ball ball = new Ball(new Point(500, 300), 5, Color.WHITE, this.environment);
        ball.setVelocity(new Velocity(4, 4));
        ball.addToGame(this);
        ball.addHitListener(ballRemover);
        this.remainingBalls.increase(1);

        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);

        Color[] colorBlock = {new Color(255, 102, 102), new Color(0, 204, 0),
                new Color(255, 152, 0), new Color(153, 102, 0), new Color(102, 0, 153),
                new Color(255, 225, 153)};
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int k = 1; k <= MAX_BLOCKS - i; k++) {
                if (k == i * 4) {
                    Block block = new Block(new Rectangle(new Point(WIDTH - BLOCK_HEIGHT - BLOCK_WIDTH * k,
                            100 + BLOCK_HEIGHT * i),
                            BLOCK_WIDTH, BLOCK_HEIGHT), Color.BLACK, Color.BLACK);
                    block.addToGame(this);
                    block.addHitListener(ballRemover);
                    block.addHitListener(blockRemover);
                    block.addHitListener(scoreTrackingListener);
                    continue;
                }
                if (k == i) {
                    Block block = new Block(new Rectangle(new Point(WIDTH - BLOCK_HEIGHT - BLOCK_WIDTH * k,
                            100 + BLOCK_HEIGHT * i),
                            BLOCK_WIDTH, BLOCK_HEIGHT), Color.WHITE, Color.BLACK);
                    block.addToGame(this);
                    block.addHitListener(addBall);
                    block.addHitListener(scoreTrackingListener);
                    continue;
                }

                Block block = new Block(new Rectangle(new Point(WIDTH - BLOCK_HEIGHT - BLOCK_WIDTH * k,
                        100 + BLOCK_HEIGHT * i),
                        BLOCK_WIDTH, BLOCK_HEIGHT), colorBlock[i], Color.BLACK);
                block.addToGame(this);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);

                this.remainingBlocks.increase(1);
            }
        }

        // Create edge blocks (top, bottom, left, right)
        Block topEdge = new Block(new Rectangle(new Point(0, 0), WIDTH, FRAME_HEIGHT_WIDTH), Color.GRAY, Color.BLACK);
        topEdge.addToGame(this);

        Block bottomEdge = new Block(new Rectangle(new Point(0, 570), WIDTH, FRAME_HEIGHT_WIDTH),
                new Color(51, 204, 225), new Color(51, 204, 225));
        bottomEdge.addToGame(this);
        bottomEdge.addHitListener(ballRemover);

        Block leftEdge = new Block(new Rectangle(new Point(0, 0), FRAME_HEIGHT_WIDTH, HEIGHT), Color.GRAY, Color.BLACK);
        leftEdge.addToGame(this);

        Block rightEdge = new Block(new Rectangle(new Point(770, 0), FRAME_HEIGHT_WIDTH, HEIGHT), Color.GRAY,
                Color.BLACK);
        rightEdge.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(score, new Rectangle(0, 0, 800, 17));
        scoreIndicator.addToGame(this);
    }

    /**
     * Runs the game loop, which continuously updates and renders the game state.
     */
    public void run() {
        GUI gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        KeyboardSensor keyboard = gui.getKeyboardSensor();

        Paddle paddle = new Paddle(new Rectangle(new Point(370, 555), PADDLE_WIDTH, PADDLE_HEIGHT), Color.WHITE,
                keyboard, Color.BLACK, 5);
        this.addCollidable(paddle);
        this.addSprite(paddle);
        paddle.addToGame(this);

        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = MILLISECONDS_PER_FRAME / FRAMES_PER_SECOND;

        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            // Exit the game loop if no more blocks are available
            if (this.remainingBlocks.getValue() == 0) {
                score.increase(100);
                gui.close();
                return;
            }

            // Exit the game loop if no more balls are available
            if (this.remainingBalls.getValue() == 0) {
                gui.close();
                return;
            }

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Removes a collidable from the {@code environment}.
     *
     * @param c a collidable we want to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the {@code environment}.
     *
     * @param s a sprite we want to remove.
     */
    public void
    removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

}
