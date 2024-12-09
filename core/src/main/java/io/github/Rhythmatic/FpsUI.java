package io.github.Rhythmatic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import io.github.Rhythmatic.Util.BaseUI;
import io.github.Rhythmatic.Util.Fps;
public class FpsUI extends BaseUI {

    private Fps fpsCounter;

    FpsUI()
    {
        super();
        fpsCounter = new Fps();

    }
    @Override
    public void render(SpriteBatch target)
    {
            font12.draw(target, "FPS: " + fpsCounter.getFps(), 0,800);
    }
    @Override
    public void update(float dt)
    {
    }
    @Override
    public void input()
    {

    }

}
