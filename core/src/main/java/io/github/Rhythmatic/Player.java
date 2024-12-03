package io.github.Rhythmatic;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {
   
    Player()
    {
       
        noteTextures = new Texture("assets/spritesheet.png");
        buttons.put("A", new  Sprite(noteTextures, 0,0,16,16));
        buttons.put("S", new Sprite(noteTextures, 0,0,16,16));
        buttons.put("D", new Sprite(noteTextures, 0,0,16,16));
        buttons.put("F", new Sprite(noteTextures,0,0,16,16));


        buttons.get("A").setPosition(positionX - 10, positionY);
        buttons.get("A").setScale(scale);
        buttons.get("S").setPosition(positionX * 2,positionY);
        buttons.get("S").setScale(scale);
        buttons.get("D").setPosition(positionX * 3,positionY);
        buttons.get("D").setScale(scale);
        buttons.get("F").setPosition(positionX * 4,positionY);
        buttons.get("F").setScale(scale);
         



        
    }

 
  private void input()
  {
    
                
    if(Gdx.input.isKeyPressed(Input.Keys.A))
    {
        //buttons.get("A").circle(10,10, 50);
        System.out.println("Key: A");
        buttons.get("A").setColor(0,0,0,255);
        hasKeyBeenPressed = true;
    } else
    {
        buttons.get("A").setColor(255,255,255,255);

    }
    if(Gdx.input.isKeyPressed(Input.Keys.S))
    {
        System.out.println("Key: S");
        buttons.get("S").setColor(0,0,0,255);
        hasKeyBeenPressed = true;
    } else
    {
        buttons.get("S").setColor(255,255,255,255);

    }
     if(Gdx.input.isKeyPressed(Input.Keys.D))
    {
        System.out.println("Key: D");
        buttons.get("D").setColor(0,0,0,255);
        hasKeyBeenPressed = true;
    } else
    {
        buttons.get("D").setColor(255,255,255,255); 

        hasKeyBeenPressed = false;
    }
     if(Gdx.input.isKeyPressed(Input.Keys.F))
    {
        System.out.println("Key: F");
        buttons.get("F").setColor(0,0,0,255);
        hasKeyBeenPressed = true;
    } else
    {
        buttons.get("F").setColor(255,255,255,255);
        hasKeyBeenPressed = false;
    }
  }
  public void render(SpriteBatch target)
    {
        buttons.get("A").draw(target);
        buttons.get("S").draw(target);
        buttons.get("D").draw(target);
        buttons.get("F").draw(target);
    }
  public void update(float dt)
    {
        input();
        System.out.println(getKeyPress().toString());
    }
    public void dispose()
    {
        noteTextures.dispose();
    }
    public Boolean getKeyPress()
    {
        return hasKeyBeenPressed;
    }
    private HashMap<String, Sprite> buttons = new HashMap<>();
    private Texture noteTextures;
    private float positionX = 96; 
    private float positionY = 100;
    private float scale = 6;
    private Boolean hasKeyBeenPressed = false;




    
}
