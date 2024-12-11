package io.github.Rhythmatic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.Rhythmatic.Util.SoundManager;

public class LevelSelectorScreen implements Screen {
    private Main game;
    private SpriteBatch batch;
    private SoundManager soundManager;


    public LevelSelectorScreen(Main game) {
        this.game = game;
        this.batch = new SpriteBatch();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // Render level selector items here
        // Example: batch.draw(levelItemTexture, x, y);
        batch.end();

        if (Gdx.input.isTouched()) {
            // Simple input handling, replace with button click detection
            game.setScreen(new GameScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
    }

   
}
