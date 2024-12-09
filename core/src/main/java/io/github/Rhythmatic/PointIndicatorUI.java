package io.github.Rhythmatic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import io.github.Rhythmatic.Util.BaseUI;;
public class PointIndicatorUI extends BaseUI{


    //set position based on which button got a note. Make it so that it shows +1(in green) after getting a point and if the player miss show "miss!" but in red.
    PointIndicatorUI()
    {
        super();
        positions = new Vector2();
        message = new String();
        indicator = new Color();
    }
    @Override
    public void render(SpriteBatch target) {
        // TODO Auto-generated method stub
        font12.setColor(indicator);
        font12.draw(target, message, positions.x, positions.y);
        font12.setColor(Color.WHITE);

    }

    @Override
    public void input() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'input'");
    }

    @Override
    public void update(float dt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    public void setText(Array<Button> buttons) {
        boolean pointScored = false;
        boolean missed = false;

        for (Button button : buttons) {
            if (button.getButtonPress()) {
                if (button.getGotCoffee()) {
                    positions.set(button.getBoundingBox().x, button.getBoundingBox().y + button.getBoundingBox().height);
                    indicator = Color.GREEN;
                    message = "+1";
                    pointScored = true;
                } else {
                    positions.set(button.getBoundingBox().x, button.getBoundingBox().y + button.getBoundingBox().height);
                    indicator = Color.RED;
                    message = "Miss";
                    missed = true;
                }
            }
        }

        // Reset the message if no point was scored and no miss happened
        if (!pointScored && !missed) {
            message = "";
        }
    }
    private Color indicator ;
    private Vector2 positions;
    private Boolean gotPoint; 
    private String message;


}
