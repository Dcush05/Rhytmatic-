package io.github.Rhythmatic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.Rhythmatic.Util.BaseUI;
import io.github.Rhythmatic.Util.FontManager;

public class TimerUI extends BaseUI {
    private long startTime;
    private long duration;
   // private BitmapFont font;

    public TimerUI(long duration) {
        this.duration = duration;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void render(SpriteBatch batch) {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = (currentTime - startTime) / 1000;
        long remainingTime = duration - elapsedTime;

        String timeText = String.format("%02d:%02d", remainingTime / 60, remainingTime % 60);
        font12.draw(batch, "Time Left: " + timeText, (Gdx.graphics.getWidth() / 2) - 93, Gdx.graphics.getHeight() - 20);
    }

    public void resetTimer(long newDuration) {
        this.duration = newDuration;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void dispose() {
        font12.dispose();
    }

	@Override
	public void input() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'input'");
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}
}
