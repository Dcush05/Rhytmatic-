package io.github.Rhythmatic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MenuScreen implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture menu_bg;

    public MenuScreen(Main game) {
        this.game = game;
        this.batch = new SpriteBatch();
        menu_bg = new Texture("menu_screen.png");
        
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(menu_bg, 0,0);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.G)) {
            // Simple input handling, replace with button click detection
            game.setScreen(new LevelSelectorScreen(game));
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
