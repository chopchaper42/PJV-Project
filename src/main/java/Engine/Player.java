package Engine;

import Engine.Entity.LivingEntity;
import Utility.Pythagoras;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.io.File;
import java.util.List;

public class Player extends LivingEntity
{
    private final int SPEED_PER_SECOND = 1000;
    private List<Item> inventory;
    private final Image image = new Image(new File("./src/main/assets/player.png").toURI().toString());

    public Player(Point2D position)
    {
        super(position);
    }

    public Player(double x, double y)
    {
        super(x, y);
    }

    private void moveDiagonal(double distance, int scaleX, int scaleY) {
        moveX(distance * scaleX);
        moveY(distance * scaleY);
    }

    public void move(boolean W_pressed, boolean A_pressed, boolean S_pressed, boolean D_pressed, double dt) {
        double distance = getSpeedPerSecond() * dt;
        if (W_pressed && !(D_pressed || A_pressed))
            moveY(-distance);
        // What if the player is holding down multiple keys?
            // W + D + S + A is a valid input in that case, but the character goes to the upright.
        else if (W_pressed && D_pressed)
            moveDiagonal(Pythagoras.leg(distance), 1, -1);
        //
        else if (W_pressed && A_pressed)
            moveDiagonal(Pythagoras.leg(distance), -1, -1);
        else if (A_pressed && !(W_pressed || S_pressed))
            moveX(-distance);
        else if (S_pressed && !(D_pressed || A_pressed))
            moveY(distance);
        else if (S_pressed && A_pressed)
            moveDiagonal(Pythagoras.leg(distance), -1, 1);
        else if (S_pressed && D_pressed)
            moveDiagonal(Pythagoras.leg(distance), 1, 1);
        else if (D_pressed && !(S_pressed || W_pressed))
            moveX(distance);
    }

    public int getSpeedPerSecond()
    {
        return SPEED_PER_SECOND;
    }

    public List<Item> getInventory()
    {
        return inventory;
    }
}