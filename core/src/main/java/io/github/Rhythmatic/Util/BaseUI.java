package io.github.Rhythmatic.Util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BaseUI implements UI {
    protected BitmapFont font12;
    protected Texture texture;
    protected SpriteBatch Sprite; 

    public BaseUI() {
        this.font12 = FontManager.getFont12();
        
    }

    @Override
    public void dispose() {
        FontManager.dispose();
        // FontManager.dispose() should be called once when the application is shutting down, not here
    }
}
