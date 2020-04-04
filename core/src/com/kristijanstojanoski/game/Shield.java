package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Random;

class Shield {
  private boolean hasShield = false;
  
  private Random random = new Random(System.currentTimeMillis());
  
  private TextureAtlas.AtlasRegion[] shield = new TextureAtlas.AtlasRegion[2];
  
  private TextureAtlas.AtlasRegion shieldCollectible;
  
  private ArrayList<Rectangle> shieldCollectibleRectangles = new ArrayList<Rectangle>();
  
  private ArrayList<Integer> shieldCollectibleXs = new ArrayList<Integer>();
  
  private ArrayList<Integer> shieldCollectibleYs = new ArrayList<Integer>();
  
  private float shieldOnTimer = 0.0F;
  
  private int shieldState = 0;
  
  private int shieldStatePause;
  
  private float worldXToScreenX(float paramFloat) {
    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
  }
  
  private float worldYToScreenY(float paramFloat) {
    return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
  }
  
  public void dispose() {}
  
  void drawShield(SpriteBatch paramSpriteBatch, float paramFloat1, float paramFloat2, boolean paramBoolean, Preferences paramPreferences) {
    if (!paramBoolean) {
      if (paramPreferences.getInteger("costumeSelectedGame") == 0) {
        paramSpriteBatch.draw((TextureRegion)this.shield[this.shieldState], paramFloat1 - worldXToScreenX(15.0F), paramFloat2, worldXToScreenX(133.3F), worldYToScreenY(160.0F));
      } else if (paramPreferences.getInteger("costumeSelectedGame") == 1) {
        paramSpriteBatch.draw((TextureRegion)this.shield[this.shieldState], paramFloat1 + worldXToScreenX(5.0F), paramFloat2 - worldYToScreenY(5.0F), worldXToScreenX(150.0F), worldYToScreenY(175.0F));
      } else if (paramPreferences.getInteger("costumeSelectedGame") == 2) {
        paramSpriteBatch.draw((TextureRegion)this.shield[this.shieldState], paramFloat1 - worldXToScreenX(10.0F), paramFloat2, worldXToScreenX(150.0F), worldYToScreenY(175.0F));
      } else if (paramPreferences.getInteger("costumeSelectedGame") == 3) {
        paramSpriteBatch.draw((TextureRegion)this.shield[this.shieldState], paramFloat1 - worldXToScreenX(10.0F), paramFloat2 - worldYToScreenY(5.0F), worldXToScreenX(140.0F), worldYToScreenY(165.0F));
      } else if (paramPreferences.getInteger("costumeSelectedGame") == 4) {
        paramSpriteBatch.draw((TextureRegion)this.shield[this.shieldState], paramFloat1 + worldXToScreenX(15.0F), paramFloat2 - worldYToScreenY(5.0F), worldXToScreenX(140.0F), worldYToScreenY(160.0F));
      } else if (paramPreferences.getInteger("costumeSelectedGame") == 5) {
        paramSpriteBatch.draw((TextureRegion)this.shield[this.shieldState], paramFloat1 - worldXToScreenX(10.0F), paramFloat2 - worldYToScreenY(5.0F), worldXToScreenX(140.0F), worldYToScreenY(160.0F));
      } else if (paramPreferences.getInteger("costumeSelectedGame") == 6) {
        paramSpriteBatch.draw((TextureRegion)this.shield[this.shieldState], paramFloat1 - worldXToScreenX(15.0F), paramFloat2 - worldYToScreenY(5.0F), worldXToScreenX(150.0F), worldYToScreenY(170.0F));
      } else if (paramPreferences.getInteger("costumeSelectedGame") == 7) {
        paramSpriteBatch.draw((TextureRegion)this.shield[this.shieldState], paramFloat1 - worldXToScreenX(10.0F), paramFloat2 - worldYToScreenY(5.0F), worldXToScreenX(150.0F), worldYToScreenY(160.0F));
      } 
    } else if (paramPreferences.getInteger("costumeSelectedGame") == 0) {
      paramSpriteBatch.draw((TextureRegion)this.shield[0], paramFloat1 - worldXToScreenX(15.0F), paramFloat2, worldXToScreenX(133.3F), worldYToScreenY(160.0F));
    } else if (paramPreferences.getInteger("costumeSelectedGame") == 1) {
      paramSpriteBatch.draw((TextureRegion)this.shield[0], paramFloat1 + worldXToScreenX(5.0F), paramFloat2 - worldYToScreenY(5.0F), worldXToScreenX(150.0F), worldYToScreenY(175.0F));
    } else if (paramPreferences.getInteger("costumeSelectedGame") == 2) {
      paramSpriteBatch.draw((TextureRegion)this.shield[0], paramFloat1 - worldXToScreenX(10.0F), paramFloat2, worldXToScreenX(150.0F), worldYToScreenY(175.0F));
    } else if (paramPreferences.getInteger("costumeSelectedGame") == 3) {
      paramSpriteBatch.draw((TextureRegion)this.shield[0], paramFloat1 - worldXToScreenX(10.0F), paramFloat2 - worldYToScreenY(5.0F), worldXToScreenX(140.0F), worldYToScreenY(165.0F));
    } else if (paramPreferences.getInteger("costumeSelectedGame") == 4) {
      paramSpriteBatch.draw((TextureRegion)this.shield[0], paramFloat1 + worldXToScreenX(15.0F), paramFloat2 - worldYToScreenY(5.0F), worldXToScreenX(140.0F), worldYToScreenY(160.0F));
    } else if (paramPreferences.getInteger("costumeSelectedGame") == 5) {
      paramSpriteBatch.draw((TextureRegion)this.shield[0], paramFloat1 - worldXToScreenX(10.0F), paramFloat2 - worldYToScreenY(5.0F), worldXToScreenX(140.0F), worldYToScreenY(160.0F));
    } else if (paramPreferences.getInteger("costumeSelectedGame") == 6) {
      paramSpriteBatch.draw((TextureRegion)this.shield[0], paramFloat1 - worldXToScreenX(15.0F), paramFloat2 - worldYToScreenY(5.0F), worldXToScreenX(150.0F), worldYToScreenY(170.0F));
    } else if (paramPreferences.getInteger("costumeSelectedGame") == 7) {
      paramSpriteBatch.draw((TextureRegion)this.shield[0], paramFloat1 - worldXToScreenX(10.0F), paramFloat2 - worldYToScreenY(5.0F), worldXToScreenX(150.0F), worldYToScreenY(160.0F));
    } 
  }
  
  void drawShieldCollectible(SpriteBatch paramSpriteBatch, boolean paramBoolean, ShapeRenderer paramShapeRenderer) {
    this.shieldCollectibleRectangles.clear();
    for (byte b = 0; b < this.shieldCollectibleXs.size(); b++) {
      paramSpriteBatch.draw((TextureRegion)this.shieldCollectible, ((Integer)this.shieldCollectibleXs.get(b)).intValue(), ((Integer)this.shieldCollectibleYs.get(b)).intValue(), worldXToScreenX(62.0F), worldYToScreenY(70.0F));
      if (!paramBoolean) {
        ArrayList<Integer> arrayList = this.shieldCollectibleXs;
        arrayList.set(b, Integer.valueOf((int)(((Integer)arrayList.get(b)).intValue() - worldXToScreenX(5.0F) * Gdx.graphics.getDeltaTime() * 60.0F)));
      } 
      this.shieldCollectibleRectangles.add(new Rectangle(((Integer)this.shieldCollectibleXs.get(b)).intValue() + worldXToScreenX(13.3F), ((Integer)this.shieldCollectibleYs.get(b)).intValue(), worldXToScreenX(44.4F), worldYToScreenY(65.0F)));
    } 
  }
  
  float getShieldOnTimer() {
    return this.shieldOnTimer;
  }
  
  int getShieldState() {
    return this.shieldState;
  }
  
  int getShieldStatePause() {
    return this.shieldStatePause;
  }
  
  void initializeValues(TextureAtlas paramTextureAtlas) {
    this.shield[0] = paramTextureAtlas.findRegion("shield");
    this.shield[1] = paramTextureAtlas.findRegion("transparent");
    this.shieldCollectible = paramTextureAtlas.findRegion("shield_collectible");
  }
  
  boolean isHasShield() {
    return this.hasShield;
  }
  
  void makeShield() {
    float f = this.random.nextFloat() * Gdx.graphics.getHeight();
    if (worldYToScreenY(70.0F) + f >= Gdx.graphics.getHeight()) {
      this.shieldCollectibleYs.add(Integer.valueOf((int)(f - worldYToScreenY(70.0F))));
    } else if (f <= worldYToScreenY(70.0F)) {
      this.shieldCollectibleYs.add(Integer.valueOf((int)worldYToScreenY(70.0F)));
    } else {
      this.shieldCollectibleYs.add(Integer.valueOf((int)f));
    } 
    this.shieldCollectibleXs.add(Integer.valueOf(Gdx.graphics.getWidth()));
  }
  
  void resetShieldStats() {
    this.shieldCollectibleXs.clear();
    this.shieldCollectibleYs.clear();
    this.shieldCollectibleRectangles.clear();
    this.hasShield = false;
  }
  
  void setHasShieldFalse() {
    this.hasShield = false;
  }
  
  void setShieldState(int paramInt) {
    this.shieldState = paramInt;
  }
  
  void setShieldStatePause(int paramInt) {
    this.shieldStatePause = paramInt;
  }
  
  void shieldCollision(Rectangle paramRectangle, float paramFloat, Preferences paramPreferences) {
    for (byte b = 0; b < this.shieldCollectibleRectangles.size(); b++) {
      if (Intersector.overlaps(paramRectangle, this.shieldCollectibleRectangles.get(b))) {
        paramPreferences.putInteger("powerUpsCollected", paramPreferences.getInteger("powerUpsCollected", 0) + 1);
        paramPreferences.flush();
        this.hasShield = true;
        this.shieldOnTimer = paramFloat;
        this.shieldState = 0;
        this.shieldCollectibleRectangles.remove(b);
        this.shieldCollectibleXs.remove(b);
        this.shieldCollectibleYs.remove(b);
        break;
      } 
    } 
  }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\Shield.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */