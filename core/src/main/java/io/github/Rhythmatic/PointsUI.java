package io.github.Rhythmatic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.github.Rhythmatic.Util.BaseUI;

public class PointsUI extends BaseUI {
   PointsUI()
   {
      
   }
   void setPoints(int points)
   {
      this.points = points;
   } 
   @Override
   public void render(SpriteBatch target) {
      // TODO Auto-generated method stub
      font12.draw(target, "Points: " + points, 367, 800);
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
   private int points;

}
