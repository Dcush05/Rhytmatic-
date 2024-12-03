package io.github.Rhythmatic;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.Rhythmatic.Player;
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();
       // image = new Texture("libgdx.png");
        player = new Player();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        update();
        player.render(batch);
      //  batch.draw(image, 140, 210);
        batch.end();
    }
    public void update()
    {
        player.update(0);
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        //image.dispose();
    }
}
