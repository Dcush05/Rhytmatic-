package io.github.Rhythmatic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.Rhythmatic.Util.SoundManager;

public class LevelSelectorScreen implements Screen {
    private Main game;
    private SpriteBatch batch;
    private SoundManager soundManager;
    private Texture levelSelectorScreen;
    private Texture task1Button;
    private Texture task2Button;
    private Texture task3Button;

    private float task1ButtonX, task1ButtonY;
    private float task2ButtonX, task2ButtonY;
    private float task3ButtonX, task3ButtonY;

    public LevelSelectorScreen(Main game) {
        this.game = game;
        //this.playerName = playerName;
        this.batch = new SpriteBatch();
        this.levelSelectorScreen = new Texture("task_selector.png");
        this.task1Button = new Texture("task1.png");
        this.task2Button = new Texture("task2.png");
        this.task3Button = new Texture("task3.png");

        // Set button positions
        task1ButtonX = Gdx.graphics.getWidth() / 2f - task1Button.getWidth() / 2f;
        task1ButtonY = task1Button.getHeight() + 200;

        task2ButtonX = Gdx.graphics.getWidth() / 2f - task2Button.getWidth() / 2f;
        task2ButtonY = task1Button.getHeight() + 150;

        task3ButtonX = Gdx.graphics.getWidth() / 2f - task3Button.getWidth() / 2f;
        task3ButtonY = task1Button.getHeight() + 100;
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(levelSelectorScreen, 0, 0);
        batch.draw(task1Button, task1ButtonX, task1ButtonY);
        batch.draw(task2Button, task2ButtonX, task2ButtonY);
        batch.draw(task3Button, task3ButtonX, task3ButtonY);
        batch.end();

        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (mouseX >= task1ButtonX && mouseX <= task1ButtonX + task1Button.getWidth() &&
                mouseY >= task1ButtonY && mouseY <= task1ButtonY + task1Button.getHeight()) {
                game.setScreen(new GameScreen(game, 0)); 
            }

            // Check if task2 button is pressed
            if (mouseX >= task2ButtonX && mouseX <= task2ButtonX + task2Button.getWidth() &&
                mouseY >= task2ButtonY && mouseY <= task2ButtonY + task2Button.getHeight()) {
                game.setScreen(new GameScreen(game,1));
            }

            // Check if task3 button is pressed
            if (mouseX >= task3ButtonX && mouseX <= task3ButtonX + task3Button.getWidth() &&
                mouseY >= task3ButtonY && mouseY <= task3ButtonY + task3Button.getHeight()) {
                
                game.setScreen(new GameScreen(game, 2));
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
        levelSelectorScreen.dispose();
        task1Button.dispose();
        task2Button.dispose();
        task3Button.dispose();
    }
}
