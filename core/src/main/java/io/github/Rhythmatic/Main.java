package io.github.Rhythmatic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Main extends Game {

    @Override
    public void create() {
        this.setScreen(new MenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        // Dispose of resources here if needed
    }
}

 