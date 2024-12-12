package io.github.Rhythmatic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import io.github.Rhythmatic.Util.SoundManager;
import java.util.ArrayList;
import java.util.List;

public class LevelManager {
    private List<Level> levels;
    private int currentLevelIndex;
    private SoundManager soundManager;
    
    private float elapsedTime;  // Time passed since the level started (in seconds)
    private float levelDuration; // Duration of the level in seconds
    private boolean isLevelFinished;
    
    private Main game;

    public LevelManager(SoundManager soundManager, Main game) {
        this.soundManager = soundManager;
        this.game = game;
        this.isLevelFinished = false;
        this.elapsedTime = 0f; // Initialize elapsed time to 0
        this.levelDuration = 0f; // Duration is not set initially
        levels = new ArrayList<>();
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
            this.levelDuration = (float) currentLevel.getDuration();  // Set the level's duration
            this.elapsedTime = 0f;  // Reset the elapsed time to 0 at the start of the level
        }
    }

    public void update(float deltaTime) {
        if (!isLevelFinished) {
            elapsedTime += deltaTime;  // Update the elapsed time with the time passed in this frame
            
            if (elapsedTime >= levelDuration) {
                endLevel();
            }
        }
    }

    private void endLevel() {
        isLevelFinished = true;
        soundManager.dispose();
        game.setScreen(new LevelSelectorScreen(game));
    }

    public Boolean getLevelFinish() {
        return isLevelFinished;
    }

    public void dispose() {
        soundManager.dispose();
        game.dispose();
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevel(int levelIndex) {
        currentLevelIndex = levelIndex;
    }

    public String getLevelData() {
        return "Task: " + currentLevelIndex;
    }

    private static class LevelsData {
        private List<Level> levels;

        public List<Level> getLevels() {
            return levels;
        }
    }
}