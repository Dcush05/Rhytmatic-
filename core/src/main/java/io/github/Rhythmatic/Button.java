package io.github.Rhythmatic;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
public class Button 
{
    public Boolean isPressed;
    public String id;
    public int keyCode;
    Button(Rectangle srcRect, int keyCode)
    {
            isPressed = false;
            gotCoffee = false;
            noteTextures = new Texture("assets/spritesheet.png");
            this.srcRect = srcRect;
            this.keyCode = keyCode;
            buttonSprite = new Sprite(noteTextures);
            buttonSprite.setRegion((int)srcRect.x, (int)srcRect.y, (int)srcRect.width, (int)srcRect.height);
           // buttonSprite.setScale(buttonSprite.getScaleX(), buttonSprite.getScaleY());
            buttonSprite.setSize(srcRect.width, srcRect.height);
            shape = new ShapeRenderer();
          
            //buttonSprite.fli
    
    }
    public Rectangle getBoundingBox()
    {
        return boundingBox;
    }
   
    public Boolean getButtonPress()
    {
        return isPressed;
    }
    public void setGotCoffee(Boolean bool)
    {
        gotCoffee = bool;
    }
    public Boolean getGotCoffee() //idk im bad with names
    {
        return gotCoffee;
    }
    public void setPosition(float x, float y)
    {
        buttonSprite.setPosition(x, y);
    }
    public void setScale(float xy)
    {
        buttonSprite.setSize(buttonSprite.getWidth() * xy, buttonSprite.getHeight() * xy);
        bScale = xy;
    }
    public void render(SpriteBatch target)
    {
        buttonSprite.draw(target);

       
    }
    public void update()
    {
        isPressed = Gdx.input.isKeyPressed(keyCode); 
        if(isPressed)
        {
            buttonSprite.setColor(Color.GRAY);
            //System.out.println("Button id: " + id + " has been pressed!");

            
        } else
        {
            buttonSprite.setColor(Color.WHITE);
        }
        boundingBox = new Rectangle(buttonSprite.getX(), buttonSprite.getY(), buttonSprite.getWidth(), buttonSprite.getHeight());

       // System.out.println(getBoundingBox().toString());
    }
    public void dispose()
    {
        noteTextures.dispose();
    }

    private Sprite buttonSprite;
    private Texture noteTextures;
    private Rectangle srcRect;
    private Rectangle boundingBox;
    private ShapeRenderer shape;
    private float bScale;
    private Boolean gotCoffee;
}