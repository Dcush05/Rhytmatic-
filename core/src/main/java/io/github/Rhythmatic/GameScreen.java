package io.github.Rhythmatic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.Rhythmatic.Util.BaseUI;
import io.github.Rhythmatic.Util.SoundManager;
import io.github.Rhythmatic.Util.*;



public class GameScreen implements Screen {
    //TODO *starting 12/10/24: Create Junit tests(score checking, Game checking, etc), *Menu System(Play, levels(contain diffferent tasks to do), quit button), *setting up levels, Serialization,
    // UI to display timer
    //network programming(sending scores to server if the server is up)
    private Main game;
    private SpriteBatch batch;
    public Player player;
    private Texture image;
    private NoteManager notes;
    private Array<BaseUI> UIs;
    private SoundManager soundManager;
    private LevelManager levelManager;
    private FpsUI FPS;
    private PointsUI points;
    private PointIndicatorUI messageIndicator;
    private int levelIndex;
    private TimerUI timerUI;
    private Serialization save;


    public GameScreen(Main game2, int levelIndex) {
        this.game = game2;
        this.batch = new SpriteBatch();

        this.player = new Player(MenuScreen.playerName);
        this.image = new Texture("coffee_bg.png");
        this.notes = new NoteManager();
        this.soundManager = new SoundManager();
        this.levelManager = new LevelManager(soundManager, game);
        this.FPS = new FpsUI();
        this.points = new PointsUI();
        this.messageIndicator = new PointIndicatorUI();
        this.UIs = new Array<>();
        UIs.add(FPS);
        UIs.add(points);
        UIs.add(messageIndicator);
        this.levelIndex = levelIndex;
        levelManager.setLevel(levelIndex);
        save = new Serialization();
        
       // notes.setBPM(levelManager.getCurrentLevel().getBPM());
       System.out.println(player.getName());
    }

    

    @Override
    public void show() {
        levelManager.startCurrentLevel();
        Level currentLevel = levelManager.getCurrentLevel();
        if (currentLevel != null) {
            notes.setBPM(currentLevel.getBPM());
            this.timerUI = new TimerUI((long) currentLevel.getDuration());
            UIs.add(timerUI);


        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();
        update();
        batch.draw(image, 0, 0);
        player.render(batch);
        for (BaseUI UI : UIs) {
            UI.render(batch);
        }
        notes.render(batch);
        batch.end();
    }

    public void update() {
        player.update(Gdx.graphics.getDeltaTime());
        player.handleCollision(notes.getNoteAABB());
        points.setPoints(player.getPoints());
        messageIndicator.setText(player.getButtons());
        notes.update(Gdx.graphics.getDeltaTime(), player.getGotCoffee());
        levelManager.update(Gdx.graphics.getDeltaTime());
        if(levelManager.getLevelFinish())
        {
            save.saveData(player, levelManager, "data.dat");

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
        levelManager.dispose();
        player.dispose();
        notes.dispose();
        soundManager.dispose();
        FPS.dispose();
        image.dispose();
        for(BaseUI UIs : UIs)
        {
            UIs.dispose();
        }


    }
}
