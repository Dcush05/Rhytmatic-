package io.github.Rhythmatic;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import io.github.Rhythmatic.Util.FontManager;
import io.github.Rhythmatic.Util.SoundManager;

public class MenuScreen implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture menu_bg;
    private Texture playButton;
    private Texture quitButton;
    private Texture radioButton;
    private Radio radio;
    private SoundManager sound;
    private FontManager fontManager;
    private BitmapFont text;
    private Serialization serialization;
    private float playButtonX, playButtonY, quitButtonX, quitButtonY, radioButtonX, radioButtonY;
    private int currentScore;
    private Integer highScore;
    private ShapeRenderer shapeRenderer;
    private String inputText = "";
    public static String playerName = "Anonymous";
    private boolean isNameInputted = false;
    private boolean showNamePrompt = true;
    
    private float inputBoxX, inputBoxY, inputBoxWidth, inputBoxHeight;

    public MenuScreen(Main game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.menu_bg = new Texture("menu_screen.png");
        this.playButton = new Texture("start.png");
        this.quitButton = new Texture("quit.png");
        radioButton = new Texture("radio.png");

        // Set button positions
        playButtonX = Gdx.graphics.getWidth() / 2f - playButton.getWidth() / 2f;
        playButtonY = Gdx.graphics.getHeight() / 2f + 50;
        quitButtonX = Gdx.graphics.getWidth() / 2f - quitButton.getWidth() / 2f;
        quitButtonY = Gdx.graphics.getHeight() / 2f;
        radioButtonX = Gdx.graphics.getWidth() / 2f - radioButton.getWidth() / 2f;
        radioButtonY = Gdx.graphics.getHeight() / 2f - 50;
        sound = new SoundManager();
        radio = new Radio(sound);
        serialization = new Serialization();
        highScore = serialization.loadHighScore("data.dat");
        //System.out.println(highScore);
        fontManager = new FontManager();
        text = fontManager.getFont12();
      //  promptForName();
     //   System.out.println("Input name plz(will do this through the game itself instead of terminal at some point :P ): ");
       // Scanner ln = new Scanner(System.in);
       // playerName = ln.nextLine();



    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(menu_bg, 0, 0);
        batch.draw(playButton, playButtonX, playButtonY);
        batch.draw(quitButton, quitButtonX, quitButtonY);
        batch.draw(radioButton, radioButtonX, radioButtonY);
        text.draw(batch, "HighScore: " + highScore, 0,Gdx.graphics.getHeight());
        text.draw(batch, "Player: " + playerName, 200, 800);


        batch.end();
       // handleTextInput();
        handleInput();
    }

    private void handleInput() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            float mouseX = Gdx.input.getX();
            float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

            // Check if play button is pressed
            if (mouseX >= playButtonX && mouseX <= playButtonX + playButton.getWidth() &&
                mouseY >= playButtonY && mouseY <= playButtonY + playButton.getHeight()) {
                sound.stopAllSounds();
                
                game.setScreen(new LevelSelectorScreen(game));
            }
            if (mouseX >= radioButtonX && mouseX <= radioButtonX + radioButton.getWidth() &&
                mouseY >= radioButtonY && mouseY <= radioButtonY + radioButton.getHeight()) {
                sound.stopAllSounds();
                radio.pickAndPlaySound();
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
        sound.dispose();
        radioButton.dispose();
        text.dispose();
    }
}
