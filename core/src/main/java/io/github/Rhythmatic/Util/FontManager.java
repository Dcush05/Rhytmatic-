package io.github.Rhythmatic.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontManager {
    private static FreeTypeFontGenerator generator;
    private static BitmapFont font12;

    static {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/PixelifySans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 25;  
        font12 = generator.generateFont(parameter);
    }

    public static BitmapFont getFont12() {
        return font12;
    }
    
    public static void dispose() {
        font12.dispose();
        generator.dispose();
    }
}
