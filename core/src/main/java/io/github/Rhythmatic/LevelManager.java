package io.github.Rhythmatic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import io.github.Rhythmatic.Util.SoundManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LevelManager{
    private List<Level> levels;
    private int currentLevelIndex;
    private SoundManager soundManager;
    private ScheduledExecutorService scheduler;
    private Main game;

    public LevelManager(SoundManager soundManager, Main game) {
        this.soundManager = soundManager;
        this.game = game;
        levels = new ArrayList<>();
        currentLevelIndex = 0;
        scheduler = Executors.newScheduledThreadPool(1);
        loadLevels();
    }

    private void loadLevels() {
        FileHandle fileHandle = Gdx.files.internal("levels.json");
        if (fileHandle.exists()) {
            Json json = new Json();
            LevelsData levelsData = json.fromJson(LevelsData.class, fileHandle);
            levels.addAll(levelsData.getLevels());
        } else {
            System.err.println("Error: levels.json file not found.");
        }
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public Level getCurrentLevel() {
        if (currentLevelIndex < levels.size()) {
            return levels.get(currentLevelIndex);
        }
        return null;
    }

    public void nextLevel() {
        if (currentLevelIndex < levels.size() - 1) {
            currentLevelIndex++;
        }
    }

    public void previousLevel() {
        if (currentLevelIndex > 0) {
            currentLevelIndex--;
        }
    }

    public void startCurrentLevel() {
        Level currentLevel = getCurrentLevel();
        if (currentLevel != null) {
            soundManager.loadSound(currentLevel.getMusicName(), currentLevel.getFilePath());
            soundManager.playSound(currentLevel.getMusicName(), 1.0f, false);
            startTimer((long)currentLevel.getDuration());
        }
    }

    private void startTimer(long duration) {
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        endLevel();
                    }
                });
            }
        }, duration, TimeUnit.SECONDS);
        System.out.println(duration);
    }

    private void endLevel() {
        soundManager.dispose();
        game.setScreen(new LevelSelectorScreen(game));
    }

    public void stopTimer() {
        scheduler.shutdownNow();
    }
    public List<Level> getLevels()
    {
        return levels;
    }

    private static class LevelsData {
        private List<Level> levels;

        public List<Level> getLevels() {
            return levels;
        }
    }
}
