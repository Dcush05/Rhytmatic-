package io.github.Rhythmatic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuScreen implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture menu_bg;
    private Texture playButton;
    private Texture quitButton;
    private float playButtonX, playButtonY, quitButtonX, quitButtonY;

    public MenuScreen(Main game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.menu_bg = new Texture("menu_screen.png");
        this.playButton = new Texture("start.png");
        this.quitButton = new Texture("quit.png");

        // Set button positions
        playButtonX = Gdx.graphics.getWidth() / 2f - playButton.getWidth() / 2f;
        playButtonY = Gdx.graphics.getHeight() / 2f + 50;
        quitButtonX = Gdx.graphics.getWidth() / 2f - quitButton.getWidth() / 2f;
        quitButtonY = Gdx.graphics.getHeight() / 2f - 50;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(menu_bg, 0, 0);
        batch.draw(playButton, playButtonX, playButtonY);
        batch.draw(quitButton, quitButtonX, quitButtonY);
        batch.end();

        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            // Check if play button is pressed
            if (mouseX >= playButtonX && mouseX <= playButtonX + playButton.getWidth() &&
                mouseY >= playButtonY && mouseY <= playButtonY + playButton.getHeight()) {
                game.setScreen(new LevelSelectorScreen(game));
            }

            // Check if quit button is pressed
            if (mouseX >= quitButtonX && mouseX <= quitButtonX + quitButton.getWidth() &&
                mouseY >= quitButtonY && mouseY <= quitButtonY + quitButton.getHeight()) {
                Gdx.app.exit();
            }
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
        menu_bg.dispose();
        playButton.dispose();
        quitButton.dispose();
    }
}
