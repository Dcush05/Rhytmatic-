
package io.github.Rhythmatic;

public class Level {
    private int levelNumber;
    private String musicName;
    private String filePath;
    private float duration;
    private String author;
    private int BPM;
    

    public Level()
    {

    }
    public Level(int levelNumber, String musicName, String filePath, float duration, int BPM) {
        this.levelNumber = levelNumber;
        this.musicName = musicName;
        this.filePath = filePath;
        this.duration = duration;
        this.BPM = BPM;
    }
    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }
    public int getBPM()
    {
        return BPM;
    }

}
