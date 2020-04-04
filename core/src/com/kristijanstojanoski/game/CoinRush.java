package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Random;

class CoinRush {
  private boolean coinRush = false;
  
  private boolean coinRushEnd = true;
  
  private float coinRushHeight = worldYToScreenY();
  
  private float coinRushOnTimer = 0.0F;
  
  private float coinRushWidth = worldXToScreenX(70.0F);
  
  private Random random = new Random(System.currentTimeMillis());
  
  private TextureAtlas.AtlasRegion speedCoinCollectible;
  
  private ArrayList<Rectangle> speedCoinCollectibleRectangles = new ArrayList<Rectangle>();
  
  private ArrayList<Integer> speedCoinCollectibleXs = new ArrayList<Integer>();
  
  private ArrayList<Integer> speedCoinCollectibleYs = new ArrayList<Integer>();
  
  private float worldXToScreenX(float paramFloat) {
    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
  }
  
  private float worldYToScreenY() {
    return Gdx.graphics.getHeight() / 1000.0F * 70.0F;
  }
  
  public void dispose() {}
  
  void drawSpeedCoinCollectible(SpriteBatch paramSpriteBatch, boolean paramBoolean) {
    this.speedCoinCollectibleRectangles.clear();
    for (byte b = 0; b < this.speedCoinCollectibleXs.size(); b++) {
      paramSpriteBatch.draw((TextureRegion)this.speedCoinCollectible, ((Integer)this.speedCoinCollectibleXs.get(b)).intValue(), ((Integer)this.speedCoinCollectibleYs.get(b)).intValue(), this.coinRushWidth, this.coinRushHeight);
      if (!paramBoolean) {
        ArrayList<Integer> arrayList = this.speedCoinCollectibleXs;
        arrayList.set(b, Integer.valueOf((int)(((Integer)arrayList.get(b)).intValue() - worldXToScreenX(5.0F) * Gdx.graphics.getDeltaTime() * 60.0F)));
      } 
      this.speedCoinCollectibleRectangles.add(new Rectangle(((Integer)this.speedCoinCollectibleXs.get(b)).intValue(), ((Integer)this.speedCoinCollectibleYs.get(b)).intValue(), this.coinRushWidth, this.coinRushHeight));
    } 
  }
  
  float getCoinRushOnTimer() {
    return this.coinRushOnTimer;
  }
  
  void initializeValues(TextureAtlas paramTextureAtlas) {
    this.speedCoinCollectible = paramTextureAtlas.findRegion("coin_rush");
  }
  
  boolean isCoinRush() {
    return this.coinRush;
  }
  
  boolean isCoinRushEnd() {
    return this.coinRushEnd;
  }
  
  void makeSpeedCoin() {
    float f = this.random.nextFloat() * Gdx.graphics.getHeight();
    if (this.coinRushHeight + f >= Gdx.graphics.getHeight()) {
      this.speedCoinCollectibleYs.add(Integer.valueOf((int)(f - this.coinRushHeight)));
    } else if (f <= worldYToScreenY()) {
      this.speedCoinCollectibleYs.add(Integer.valueOf((int)worldYToScreenY()));
    } else {
      this.speedCoinCollectibleYs.add(Integer.valueOf((int)f));
    } 
    this.speedCoinCollectibleXs.add(Integer.valueOf(Gdx.graphics.getWidth()));
  }
  
  void resetCoinRushStats() {
    this.speedCoinCollectibleXs.clear();
    this.speedCoinCollectibleYs.clear();
    this.speedCoinCollectibleRectangles.clear();
    this.coinRush = false;
    this.coinRushEnd = true;
  }
  
  void setCoinRushEnd(boolean paramBoolean) {
    this.coinRushEnd = paramBoolean;
  }
  
  void setCoinRushFalse() {
    this.coinRush = false;
  }
  
  void speedCoinCollision(Rectangle paramRectangle, float paramFloat, Preferences paramPreferences) {
    for (byte b = 0; b < this.speedCoinCollectibleRectangles.size(); b++) {
      if (Intersector.overlaps(paramRectangle, this.speedCoinCollectibleRectangles.get(b))) {
        paramPreferences.putInteger("powerUpsCollected", paramPreferences.getInteger("powerUpsCollected", 0) + 1);
        paramPreferences.flush();
        this.coinRush = true;
        this.coinRushEnd = true;
        this.coinRushOnTimer = paramFloat;
        this.speedCoinCollectibleRectangles.remove(b);
        this.speedCoinCollectibleXs.remove(b);
        this.speedCoinCollectibleYs.remove(b);
        break;
      } 
    } 
  }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\CoinRush.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */