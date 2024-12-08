package io.github.Rhythmatic;

import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Notes {
   Notes()
   {
    //for now only have one sprite that we are rendering howveer in the future we will have an array of sprites that we will be rendering based on songs data
    defaultTexture = new Texture("assets/spritesheet.png");
    noteSprite = new Sprite(defaultTexture, 64,0, 16, 16);
    noteSprite.setSize(noteSprite.getWidth() * 2, noteSprite.getHeight() * 2);
    
    
    position = new Vector2();
    respawn();

   }

    private void loadTexturesFromFolder(String folderPath) {
        //NOTE: Have a seperate spritesheet that contains different sprites that we would want to use that corresponds to food you would get at 
        //a coffee place.
        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        Texture texture = new Texture(file.getAbsolutePath());
                        allNoteTextures.add(texture);
                    }
                }
            }
        }
    }
   
   public void setPosition(float x, float y)
   {
    noteSprite.setPosition(x, y);
   }
   public void update(float dt)
   {
    movement(dt);
   }
   public void render(SpriteBatch target)
   {
    noteSprite.draw(target);
   }
   
   public void movement(float dt)
   {
       position.y -= 100 * dt;
       if (position.y < -noteSprite.getHeight()) {
            // Respawn the note at the top with a random x-coordinate
            //respawn();
        }
       // System.out.println(getAABB().toString());
        // Update the sprite's position
        noteSprite.setPosition(position.x, position.y);
    }

    private void respawn() {
        // Set the note to start from the top of the screen
        position.y = Gdx.graphics.getHeight();

        // Set a random x-coordinate within the screen width
        int lane = (int) (Math.random() * 4);
        float laneWidth = Gdx.graphics.getWidth() / 4f;
        position.x = lane * laneWidth + (laneWidth - noteSprite.getWidth()) / 2;
        //position.x = (float) Math.random() * (Gdx.graphics.getWidth() - noteSprite.getWidth());

        // Optional: Set a random speed if you want variety in the note's speed
        //speed = 100 + (float) Math.random() * 200;
    }
    public Vector2 getPosition()
    {
        return position;
    }
    public Rectangle getAABB()
    {
        Rectangle AABB = new Rectangle(getPosition().x, getPosition().y, noteSprite.getWidth(), noteSprite.getHeight());
        return AABB;
        
    }
    public void dispose() {
        defaultTexture.dispose();
        for (Texture texture : allNoteTextures) {
            texture.dispose();
        }
    }


   private float speed = 1.f;

   private int frequency;
   private Sprite noteSprite;
   private Texture defaultTexture;
   private Array<Texture> allNoteTextures; 
   private ArrayList<Sprite> noteSprites;
   private Vector2 position;


}
