// 325516193 Cathrine Abu-Elazam
package arkanoid.sprites;

import arkanoid.collections.GameEnvironment;
import arkanoid.game.Game;
import arkanoid.geometry.Point;
import arkanoid.geometry.Rectangle;
import arkanoid.geometry.Velocity;
import arkanoid.logic.Collidable;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The arkanoid.sprites.Paddle class represents a paddle in a game.
 * It implements both the arkanoid.sprites.Sprite and arkanoid.logic.Collidable interfaces.
 */
public class Paddle implements Sprite, Collidable {
    private GameEnvironment gameEnvironment;
    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private final Color color;
    private final Color frameColor;
    private final Velocity velocity;
    private static final double THRESHOLD = 0.0001;
    private static final int SCREEN_WIDTH = 800;
    private static final int FRAME_WIDTH = 30;
    private static final int ANGLE_300_DEGREE = 300;
    private static final int ANGLE_330_DEGREE = 330;
    private static final int ANGLE_30_DEGREE = 30;
    private static final int ANGLE_60_DEGREE = 60;


    /**
     * Constructs a new arkanoid.sprites.Paddle with the specified parameters.
     *
     * @param rectangle  the rectangle representing the paddle's shape and position.
     * @param color      the color of the paddle.
     * @param keyboard   the keyboard sensor to control the paddle's movement.
     * @param frameColor the color of the paddle's frame.
     * @param moveSpeed  the speed at which the paddle moves.
     */
    public Paddle(Rectangle rectangle, Color color, KeyboardSensor keyboard, Color frameColor, double moveSpeed) {
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = keyboard;
        this.frameColor = frameColor;
        this.velocity = new Velocity(moveSpeed, 0);
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {
        double newX = this.rectangle.getUpperLeft().getX() - velocity.getDx();
        if (newX < FRAME_WIDTH + THRESHOLD) {
            newX = SCREEN_WIDTH - this.rectangle.getWidth() - FRAME_WIDTH; // Wrap around to the right edge
        }

        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()),
                this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {
        double newX = this.rectangle.getUpperLeft().getX() + velocity.getDx();
        if (newX + this.rectangle.getWidth() > SCREEN_WIDTH - FRAME_WIDTH) {
            newX = FRAME_WIDTH + THRESHOLD; // Wrap around to the left edge
        }
        this.rectangle = new Rectangle(new Point(newX, this.rectangle.getUpperLeft().getY()),
                this.rectangle.getWidth(), this.rectangle.getHeight());
    }


    /**
     * Returns the "collision shape" of the object, represented as a rectangle.
     *
     * @return the rectangle representing the collision shape of the object
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notifies the object that a collision has occurred at the specified point
     * with the given velocity. The method calculates the new velocity expected
     * after the hit, based on the force the object inflicts.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the velocity of the object at the time of collision
     * @return the new velocity expected after the hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double blockPartsWidth = this.rectangle.getWidth() / 5;
        double collisionX = collisionPoint.getX();

        if (this.rectangle.getXStart() - THRESHOLD <= collisionX && collisionX < this.rectangle.getXStart()
                + blockPartsWidth + THRESHOLD) {
            // Region 1: 300 degrees (-60 degrees)
            return Velocity.fromAngleAndSpeed(ANGLE_300_DEGREE, currentVelocity.getSpeed());

        } else if (this.rectangle.getXStart() + blockPartsWidth - THRESHOLD
                <= collisionX && collisionX < this.rectangle.getXStart() + 2 * blockPartsWidth + THRESHOLD) {
            // Region 2: 330 degrees (-30 degrees)
            return Velocity.fromAngleAndSpeed(ANGLE_330_DEGREE, currentVelocity.getSpeed());

        } else if (this.rectangle.getXStart() + 2 * blockPartsWidth - THRESHOLD <= collisionX && collisionX
                < this.rectangle.getXStart() + 3 * blockPartsWidth + THRESHOLD) {
            // Region 3: Reverse vertical direction
            return new Velocity(dx, -dy);

        } else if (this.rectangle.getXStart() + 3 * blockPartsWidth - THRESHOLD <= collisionX && collisionX
                < this.rectangle.getXStart() + 4 * blockPartsWidth + THRESHOLD) {
            // Region 4: 30 degrees
            return Velocity.fromAngleAndSpeed(ANGLE_30_DEGREE, currentVelocity.getSpeed());

        } else if (this.rectangle.getXStart() + 4 * blockPartsWidth - THRESHOLD <= collisionX && collisionX
                <= this.rectangle.getXStart() + 5 * blockPartsWidth + THRESHOLD) {
            // Region 5: 60 degrees
            return Velocity.fromAngleAndSpeed(ANGLE_60_DEGREE, currentVelocity.getSpeed());
        }

        // Default case: No change in velocity (this should not happen if the collision is properly detected)
        return new Velocity(dx, dy);
    }

    /**
     * @return true because it is a paddle.
     */
    @Override
    public Boolean isPaddle() {
        return true;
    }

    /**
     * Draws the sprite onto the given DrawSurface.
     *
     * @param d the DrawSurface on which the sprite should be drawn
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getXStart(), (int) this.rectangle.getYStart(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(this.frameColor);
        d.drawRectangle((int) this.rectangle.getXStart(), (int) this.rectangle.getYStart(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Notifies the sprite that time has passed. This method is used to update
     * the sprite's state, such as moving its position, changing its appearance,
     * or performing any other time-based updates.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game we add ti it the paddle
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
