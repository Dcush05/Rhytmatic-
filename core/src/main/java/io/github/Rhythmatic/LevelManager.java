package io.github.Rhythmatic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import io.github.Rhythmatic.Util.SoundManager;

import java.util.ArrayList;
import java.util.List;


public class LevelManager {
    private List<Level> levels;
    private int currentLevelIndex;
    private SoundManager soundManager;

    public LevelManager(SoundManager soundManager) {
        this.soundManager = soundManager;
        levels = new ArrayList<>();
        currentLevelIndex = 0;
        loadLevels();
    }

    private void loadLevels() {
        FileHandle fileHandle = Gdx.files.internal("assets/levels.json");
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
        System.out.println(currentLevel.getDuration());
        if (currentLevel != null) {
            soundManager.loadSound(currentLevel.getMusicName(), currentLevel.getFilePath());

            soundManager.playSound(currentLevel.getMusicName(), 1.0f, false);
        }
    }

    private static class LevelsData {
        private List<Level> levels;

        public List<Level> getLevels() {
            return levels;
        }
    }
}