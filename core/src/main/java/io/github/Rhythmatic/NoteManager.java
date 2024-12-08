package io.github.Rhythmatic;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class NoteManager {
    
   // private int spawnTimers[];

    public NoteManager() {
        notesArray = new Array<Notes>();
        spawnInterval = 3.0f; // Spawn a new note every second NOTE: make sure to change value based on the song that is playing. 
        spawnTimer = 0;
        random = new Random();
        noteAABB = new Rectangle();
        noteAABBs = new ArrayList<>();

    }

    public void update(float dt, Boolean isContained) {
        spawnTimer += dt;
        if (spawnTimer >= spawnInterval) {
            spawnNotes();
            spawnTimer = 0;
        }
       /*   for (int lane = 0; lane < 4; lane++) {
            spawnTimers[lane] += dt;
            if (spawnTimers[lane] >= getRandomSpawnInterval()) {
                spawnNoteInLane(lane);
                spawnTimers[lane] = 0;
            }
        }*/

        for (Notes note : notesArray) {
            note.update(dt);
        }
        destroyNote(isContained);
    }
    private float getRandomSpawnInterval() {
        return spawnInterval + random.nextFloat(); // Adds a random float between 0 and 1 to the base interval
    }


    public void render(SpriteBatch batch) {
        for (Notes note : notesArray) {
            note.render(batch);
        }
    }
    public ArrayList<Rectangle> getNoteAABB()
    {
        noteAABBs.clear();
        for (Notes note : notesArray) {
            noteAABBs.add(note.getAABB());
        }
        return noteAABBs;
    }
    public void destroyNote(Boolean isContained)
    {
        
        for(Notes note : notesArray)
        {
            if(note.getPosition().y < 106)
            {
                notesArray.removeValue(note, true);

            }
        }
    }

    private void spawnNotes() {
        float heights[] = generateDifferentHeights();
        for (int lane = 0; lane < 4; lane++) {
            float laneWidth = Gdx.graphics.getWidth() / 4f;
            float x = lane * laneWidth + (laneWidth - 32) / 2; // Assuming note width is 32
          //  float y = Gdx.graphics.getHeight();
         //  float y = Gdx.graphics.getHeight() + random.nextInt(200) - 100; // Random height offset between -100 and +100
            float y = heights[lane];
            Notes note = new Notes();
            note.setPosition(x, y);
            notesArray.add(note);
        }
    }
     private float[] generateDifferentHeights() {
        float[] heights = new float[4];
        for (int i = 0; i < 4; i++) {
            heights[i] = Gdx.graphics.getHeight() + random.nextInt(300) - 100; // Random height offset between -100 and +100
        }
        return heights;
    }

   /* private void destroyNotes()
    {
        
        for(Notes note : notesArray)
        {
            //if(note.)
            if(note.getPosition().y < 0 )
        }
    }*/


    public void dispose() {
        for (Notes note : notesArray) {
            note.dispose();
        }
    }
    private Array<Notes> notesArray;
    private float spawnTimer;
    private float spawnInterval;
    private Random random;
    private Rectangle noteAABB;
    ArrayList<Rectangle> noteAABBs;

}

