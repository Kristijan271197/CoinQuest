package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Random;

class Spikes {
  private float lastSpikeDownTimer = 0.0F;
  
  private Random random = new Random(System.currentTimeMillis());
  
  private TextureAtlas.AtlasRegion spikeDown;
  
  private int spikeDownTime;
  
  private ArrayList<Rectangle> spikesDownRectanglesFirst = new ArrayList<Rectangle>();
  
  private ArrayList<Rectangle> spikesDownRectanglesSecond = new ArrayList<Rectangle>();
  
  private ArrayList<Rectangle> spikesDownRectanglesThird = new ArrayList<Rectangle>();
  
  private ArrayList<Integer> spikesDownXs = new ArrayList<Integer>();
  
  private ArrayList<Integer> spikesDownYs = new ArrayList<Integer>();
  
  private float worldXToScreenX(float paramFloat) {
    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
  }
  
  private float worldYToScreenY(float paramFloat) {
    return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
  }
  
  public void dispose() {}
  
  void drawSpikeDown(SpriteBatch paramSpriteBatch, boolean paramBoolean, ShapeRenderer paramShapeRenderer) {
    this.spikesDownRectanglesFirst.clear();
    this.spikesDownRectanglesSecond.clear();
    this.spikesDownRectanglesThird.clear();
    for (byte b = 0; b < this.spikesDownXs.size(); b++) {
      paramSpriteBatch.draw((TextureRegion)this.spikeDown, ((Integer)this.spikesDownXs.get(b)).intValue(), ((Integer)this.spikesDownYs.get(b)).intValue(), worldXToScreenX(112.0F), worldYToScreenY(250.0F));
      if (!paramBoolean) {
        ArrayList<Integer> arrayList = this.spikesDownXs;
        arrayList.set(b, Integer.valueOf((int)(((Integer)arrayList.get(b)).intValue() - worldXToScreenX(3.0F) * Gdx.graphics.getDeltaTime() * 60.0F)));
      } 
      this.spikesDownRectanglesFirst.add(new Rectangle(((Integer)this.spikesDownXs.get(b)).intValue() + worldXToScreenX(8.0F), ((Integer)this.spikesDownYs.get(b)).intValue() + worldYToScreenY(75.0F), worldXToScreenX(13.0F), worldYToScreenY(75.0F)));
      this.spikesDownRectanglesSecond.add(new Rectangle(((Integer)this.spikesDownXs.get(b)).intValue() + worldXToScreenX(85.0F), ((Integer)this.spikesDownYs.get(b)).intValue() + worldYToScreenY(110.0F), worldXToScreenX(20.0F), worldYToScreenY(80.0F)));
      this.spikesDownRectanglesThird.add(new Rectangle(((Integer)this.spikesDownXs.get(b)).intValue() + worldXToScreenX(45.0F), ((Integer)this.spikesDownYs.get(b)).intValue(), worldXToScreenX(20.0F), worldYToScreenY(250.0F)));
    } 
  }
  
  float getLastSpikeDownTimer() {
    return this.lastSpikeDownTimer;
  }
  
  int getSpikeDownTime() {
    return this.spikeDownTime;
  }
  
  void initializeValues(TextureAtlas paramTextureAtlas) {
    this.spikeDown = paramTextureAtlas.findRegion("cactus");
    this.spikeDownTime = this.random.nextInt(6001) + 4000;
  }
  
  void makeSpikeDown() {
    this.spikesDownXs.add(Integer.valueOf(Gdx.graphics.getWidth()));
    this.spikesDownYs.add(Integer.valueOf((int)worldYToScreenY(85.0F)));
  }
  
  void resetSpikeDownStats() {
    this.spikesDownXs.clear();
    this.spikesDownYs.clear();
    this.spikesDownRectanglesFirst.clear();
    this.spikesDownRectanglesSecond.clear();
    this.spikesDownRectanglesThird.clear();
    this.lastSpikeDownTimer = 0.0F;
  }
  
  void setLastSpikeDownTimer(float paramFloat) {
    this.lastSpikeDownTimer = paramFloat;
  }
  
  void setSpikeDownTime(int paramInt) {
    this.spikeDownTime = paramInt;
  }
  
  int spikeDownCollision(Rectangle paramRectangle, int paramInt) {
    int i;
    byte b = 0;
    while (true) {
      i = paramInt;
      if (b < this.spikesDownRectanglesFirst.size()) {
        if (Intersector.overlaps(paramRectangle, this.spikesDownRectanglesFirst.get(b))) {
          this.spikesDownXs.remove(b);
          this.spikesDownYs.remove(b);
          this.spikesDownRectanglesFirst.remove(b);
          this.spikesDownRectanglesSecond.remove(b);
          this.spikesDownRectanglesThird.remove(b);
        } else if (Intersector.overlaps(paramRectangle, this.spikesDownRectanglesSecond.get(b))) {
          this.spikesDownXs.remove(b);
          this.spikesDownYs.remove(b);
          this.spikesDownRectanglesFirst.remove(b);
          this.spikesDownRectanglesSecond.remove(b);
          this.spikesDownRectanglesThird.remove(b);
        } else if (Intersector.overlaps(paramRectangle, this.spikesDownRectanglesThird.get(b))) {
          this.spikesDownXs.remove(b);
          this.spikesDownYs.remove(b);
          this.spikesDownRectanglesFirst.remove(b);
          this.spikesDownRectanglesSecond.remove(b);
          this.spikesDownRectanglesThird.remove(b);
        } else {
          b++;
          continue;
        } 
        i = 2;
      } 
      break;
    } 
    return i;
  }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\Spikes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */