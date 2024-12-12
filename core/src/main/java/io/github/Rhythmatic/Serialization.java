package io.github.Rhythmatic;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialization {
    Serialization()
    {

    }
    public void saveData(Player player, LevelManager level, String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(player.getPoints());  
            objectOut.writeObject(player.getName());
            objectOut.writeObject(level.getLevelData());
            System.out.println("Game data saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Integer loadHighScore(String filename) {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (Integer) objectIn.readObject(); // Read high score as Integer
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;  
        }
    }
}