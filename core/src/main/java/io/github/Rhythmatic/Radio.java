package io.github.Rhythmatic;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import io.github.Rhythmatic.Util.SoundManager;

public class Radio{

    private SoundManager soundManager;

    public Radio(SoundManager soundManager) 
    {
        this.soundManager = soundManager;
    }

    public void pickAndPlaySound() {
         SwingUtilities.invokeLater(() -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                System.out.println("Selected file: " + filePath);

                Gdx.app.postRunnable(() -> {
                    String soundName = selectedFile.getName();
                    FileHandle fileHandle = Gdx.files.absolute(filePath);
                    soundManager.loadSound(soundName, fileHandle.path());
                    soundManager.playSound(soundName, 1.0f, false);
                });
            }
        });
    }
}