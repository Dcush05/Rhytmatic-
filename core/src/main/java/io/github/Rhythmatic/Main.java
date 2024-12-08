package io.github.Rhythmatic;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.Rhythmatic.Player;
import io.github.Rhythmatic.Notes;
import io.github.Rhythmatic.NoteManager;
import io.github.Rhythmatic.Util.Collision;
import io.github.Rhythmatic.Util.Fps;
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Player player;
    private OrthographicCamera camera;
    private Notes note;
    private NoteManager notes;
    private Collision detection;
    private Fps FPS;



    @Override
    public void create() {
        batch = new SpriteBatch();
      //  image = new Texture("libgdx.png");
        player = new Player();
        note = new Notes();
        notes = new NoteManager();
        detection = new Collision();
        FPS = new Fps();
        //camera = new OrthographicCamera();
        //camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
       // camera.update();
       // batch.setProjectionMatrix(camera.combined);
        batch.begin();
        update();
        player.render(batch);
        //note.render(batch);
        notes.render(batch);
     //   batch.draw(image, 0, 0);
        batch.end();
    }
    public void update()
    {
        System.out.println(FPS.getFps());
        player.update(Gdx.graphics.getDeltaTime());
        player.handleCollision(notes.getNoteAABB());
        System.out.println("Points " + player.getPoints());


       // note.update(Gdx.graphics.getDeltaTime());
       notes.update(Gdx.graphics.getDeltaTime(), player.getGotCoffee());
    
       
        
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
       // note.dispose();
       notes.dispose();
        //image.dispose();
    }
}
