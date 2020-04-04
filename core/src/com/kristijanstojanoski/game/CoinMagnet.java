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

class CoinMagnet {
  private TextureAtlas.AtlasRegion coinMagnetCollectible;
  
  private ArrayList<Rectangle> coinMagnetCollectibleRectangles = new ArrayList<Rectangle>();
  
  private ArrayList<Integer> coinMagnetCollectibleXs = new ArrayList<Integer>();
  
  private ArrayList<Integer> coinMagnetCollectibleYs = new ArrayList<Integer>();
  
  private float coinMagnetHeight = worldYToScreenY();
  
  private float coinMagnetOnTimer = 0.0F;
  
  private float coinMagnetWidth = worldXToScreenX(70.0F);
  
  private boolean hasCoinMagnet = false;
  
  private Random random = new Random(System.currentTimeMillis());
  
  private float worldXToScreenX(float paramFloat) {
    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
  }
  
  private float worldYToScreenY() {
    return Gdx.graphics.getHeight() / 1000.0F * 70.0F;
  }
  
  void coinMagnetCollision(Rectangle paramRectangle, float paramFloat, Preferences paramPreferences) {
    for (byte b = 0; b < this.coinMagnetCollectibleRectangles.size(); b++) {
      if (Intersector.overlaps(paramRectangle, this.coinMagnetCollectibleRectangles.get(b))) {
        paramPreferences.putInteger("powerUpsCollected", paramPreferences.getInteger("powerUpsCollected", 0) + 1);
        paramPreferences.flush();
        this.hasCoinMagnet = true;
        this.coinMagnetOnTimer = paramFloat;
        this.coinMagnetCollectibleRectangles.remove(b);
        this.coinMagnetCollectibleXs.remove(b);
        this.coinMagnetCollectibleYs.remove(b);
        break;
      } 
    } 
  }
  
  public void dispose() {}
  
  void drawCoinMagnetCollectible(SpriteBatch paramSpriteBatch, boolean paramBoolean) {
    this.coinMagnetCollectibleRectangles.clear();
    for (byte b = 0; b < this.coinMagnetCollectibleXs.size(); b++) {
      paramSpriteBatch.draw((TextureRegion)this.coinMagnetCollectible, ((Integer)this.coinMagnetCollectibleXs.get(b)).intValue(), ((Integer)this.coinMagnetCollectibleYs.get(b)).intValue(), this.coinMagnetWidth, this.coinMagnetHeight);
      if (!paramBoolean) {
        ArrayList<Integer> arrayList = this.coinMagnetCollectibleXs;
        arrayList.set(b, Integer.valueOf((int)(((Integer)arrayList.get(b)).intValue() - worldXToScreenX(5.0F) * Gdx.graphics.getDeltaTime() * 60.0F)));
      } 
      this.coinMagnetCollectibleRectangles.add(new Rectangle(((Integer)this.coinMagnetCollectibleXs.get(b)).intValue(), ((Integer)this.coinMagnetCollectibleYs.get(b)).intValue(), this.coinMagnetWidth, this.coinMagnetHeight));
    } 
  }
  
  float getCoinMagnetOnTimer() {
    return this.coinMagnetOnTimer;
  }
  
  void initializeValues(TextureAtlas paramTextureAtlas) {
    this.coinMagnetCollectible = paramTextureAtlas.findRegion("coin_magnet");
  }
  
  boolean isHasCoinMagnet() {
    return this.hasCoinMagnet;
  }
  
  void makeCoinMagnet() {
    float f = this.random.nextFloat() * Gdx.graphics.getHeight();
    if (this.coinMagnetHeight + f >= Gdx.graphics.getHeight()) {
      this.coinMagnetCollectibleYs.add(Integer.valueOf((int)(f - this.coinMagnetHeight)));
    } else if (f <= worldYToScreenY()) {
      this.coinMagnetCollectibleYs.add(Integer.valueOf((int)worldYToScreenY()));
    } else {
      this.coinMagnetCollectibleYs.add(Integer.valueOf((int)f));
    } 
    this.coinMagnetCollectibleXs.add(Integer.valueOf(Gdx.graphics.getWidth()));
  }
  
  void resetCoinMagnetState() {
    this.coinMagnetCollectibleXs.clear();
    this.coinMagnetCollectibleYs.clear();
    this.coinMagnetCollectibleRectangles.clear();
    this.coinMagnetOnTimer = 0.0F;
    this.hasCoinMagnet = false;
  }
  
  void setHasCoinMagnet() {
    this.hasCoinMagnet = false;
  }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\CoinMagnet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */