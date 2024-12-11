package io.github.Rhythmatic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import io.github.Rhythmatic.Button;
import io.github.Rhythmatic.Util.Collision;
import io.github.Rhythmatic.Util.SoundManager;

public class Player {
   

  public SoundManager sound;
    Player()
    {
      buttons = new HashMap<>();
       buttons.put("D", new Button( new Rectangle(0, 0, 16, 16), com.badlogic.gdx.Input.Keys.D));
       buttons.get("D").id = "D";
       buttons.get("D").setPosition(positionX, positionY);
       buttons.get("D").setScale(scale);

      buttons.put("F", new Button(new Rectangle(0,0,16,16), com.badlogic.gdx.Input.Keys.F));
      buttons.get("F").id = "F";
      buttons.get("F").setPosition(positionX + 112, positionY);
      buttons.get("F").setScale(scale);

       buttons.put("J", new Button(new Rectangle(0,0,16,16), com.badlogic.gdx.Input.Keys.J));
      buttons.get("J").id = "J";
      buttons.get("J").setPosition(positionX + 224, positionY);
      buttons.get("J").setScale(scale);

      buttons.put("K", new Button(new Rectangle(0,0,16,16), com.badlogic.gdx.Input.Keys.K));
      buttons.get("K").id = "K";
      buttons.get("K").setPosition(positionX+ 336, positionY);
      buttons.get("K").setScale(scale);

      AABB = new Array<Rectangle>();
      collider = new Collision();
      sound = new SoundManager();
      sound.loadSound("droplet", "Sound effects/droplet.mp3");

      
    
      
       //buttons.get("A").setPosition(100, 100);
     // buttons.get("A").setScale(0);
      // buttons.get("A").setScale(scale);
  


        
    }

 
  private void input()
  {
  /*  if(Gdx.input.isKeyJustPressed(Input.Keys.A))
    {
        buttons.get("A").isPressed = true;
    }*/
    for(Button button : buttons.values())
    {
      if((points > 1) && !getGotCoffee() && button.getButtonPress()) //find a better way to punish players
      {
        points--;
      } 
    } 
                
  

  }
  public void render(SpriteBatch target)
    {
       /*  buttons.get("A").render(target);
        buttons.get("S").render(target);
        buttons.get("D").render(target);
        buttons.get("F").render(target);*/
        for(Button button : buttons.values())
        {
          button.render(target);
        }
    }
  public void update(float dt)
    {
        input();
        for(Button button : buttons.values())
        {
          button.update();
        }
        setPoints();
      //  System.out.println(getbuttonsAABB().toString());
       // System.out.println(getKeyPress().toString());
    }
    public void dispose()
    {
        /*buttons.get("A").dispose();
        buttons.get("S").dispose();
        buttons.get("D").dispose();
        buttons.get("F").dispose();*/
        for(Button button : buttons.values())
        {
          button.dispose();
        }
        sound.dispose(); 
    }
    public Array<Rectangle> getbuttonsAABB()
    {
      AABB.clear();
      for(Button button : buttons.values())
      {
        AABB.add(button.getBoundingBox());
      }
      return AABB;
    }

    public void handleCollision(ArrayList<Rectangle> noteAABBs)
    {
      for(Button button : buttons.values())
      {
        for(Rectangle AABB2 : noteAABBs)
        {
          if(collider.collisionDetection(button.getBoundingBox(), AABB2) && button.isPressed)
          {
              System.out.println("Collision has been detected");
              sound.playSound("droplet", 0.5f, false);
              button.setGotCoffee(true);
             // points++;

          } 
        }
      }
    }
    public Boolean getGotCoffee()
    {
      Boolean bool = false;
      for(Button button : buttons.values())
      {
        bool = button.getGotCoffee(); 
      }
      return bool;
    }
    public void setPoints()
    {
      for(Button button : buttons.values())
      {
        if(button.getGotCoffee())
        {
          points++;
          button.setGotCoffee(false);
        }
      }
    }
    public int getPoints()
    {
      return points;
    }
    public Array<Button> getButtons() {
      Collection<Button> buttonCollection = buttons.values();
      Array<Button> buttonArray = new Array<Button>(buttonCollection.size());
      for (Button button : buttonCollection) {
        buttonArray.add(button);
      }
    return buttonArray;
  }

   
   // private HashMap<String, Sprite> buttons = new HashMap<>();
    private HashMap<String, Button> buttons;
    private Array<Rectangle> AABB;
    private float positionX = 10; 
    private float positionY = 15;
    private float scale = 7;
    private int points = 0;
    private Collision collider;




    
}
