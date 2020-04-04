package com.kristijanstojanoski.game;

import States.MusicSounds;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Random;

class Bomb {
  private TextureAtlas.AtlasRegion bomb;
  
  private ArrayList<Circle> bombCircles = new ArrayList<Circle>();
  
  private float bombHeight = worldYToScreenY(75.0F);
  
  private float bombWidth = worldXToScreenX(75.0F);
  
  private ArrayList<Integer> bombXs = new ArrayList<Integer>();
  
  private ArrayList<Integer> bombYs = new ArrayList<Integer>();
  
  private float lastBombTimer = 0.0F;
  
  private MusicSounds musicSoundsObject;
  
  private Random random = new Random(System.currentTimeMillis());
  
  private float worldXToScreenX(float paramFloat) {
    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
  }
  
  private float worldYToScreenY(float paramFloat) {
    return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
  }
  
  int bombCollision(Rectangle paramRectangle, int paramInt, Preferences paramPreferences) {
    int i;
    byte b = 0;
    while (true) {
      i = paramInt;
      if (b < this.bombCircles.size()) {
        if (Intersector.overlaps(this.bombCircles.get(b), paramRectangle)) {
          this.musicSoundsObject.playBombSound();
          paramPreferences.putBoolean("playerGround", true);
          paramPreferences.flush();
          i = 2;
          this.bombXs.remove(b);
          this.bombYs.remove(b);
          this.bombCircles.remove(b);
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    return i;
  }
  
  public void dispose() {}
  
  void drawBomb(SpriteBatch paramSpriteBatch, boolean paramBoolean, ShapeRenderer paramShapeRenderer, float paramFloat) {
    this.bombCircles.clear();
    for (byte b = 0; b < this.bombXs.size(); b++) {
      paramSpriteBatch.draw(this.bomb, this.bombXs.get(b), ((Integer)this.bombYs.get(b)).intValue(), this.bombWidth, this.bombHeight);
      if (!paramBoolean) {
        ArrayList<Integer> arrayList1 = this.bombXs;
        arrayList1.set(b, Integer.valueOf((int)(((Integer)arrayList1.get(b)).intValue() - worldXToScreenX(paramFloat) * Gdx.graphics.getDeltaTime() * 60.0F)));
      } 
      ArrayList<Circle> arrayList = this.bombCircles;
      float f1 = ((Integer)this.bombXs.get(b)).intValue();
      float f2 = this.bombHeight / 2.2F;
      float f3 = ((Integer)this.bombYs.get(b)).intValue();
      float f4 = this.bombHeight;
      arrayList.add(new Circle(f1 + f2, f3 + f4 / 2.5F, f4 / 2.4F));
    } 
  }
  
  float getLastBombTimer() {
    return this.lastBombTimer;
  }
  
  void initializeValues(TextureAtlas paramTextureAtlas, AssetManager paramAssetManager) {
    this.bomb = paramTextureAtlas.findRegion("bomb");
    this.musicSoundsObject = new MusicSounds(paramAssetManager);
  }
  
  void makeBomb() {
    float f = this.random.nextFloat() * Gdx.graphics.getHeight();
    if (this.bombHeight + f >= Gdx.graphics.getHeight()) {
      this.bombYs.add(Integer.valueOf((int)(f - this.bombHeight)));
    } else if (f <= worldYToScreenY(70.0F)) {
      this.bombYs.add(Integer.valueOf((int)worldYToScreenY(70.0F)));
    } else {
      this.bombYs.add(Integer.valueOf((int)f));
    } 
    this.bombXs.add(Integer.valueOf(Gdx.graphics.getWidth()));
  }
  
  void resetBombStats() {
    this.bombXs.clear();
    this.bombYs.clear();
    this.bombCircles.clear();
    this.lastBombTimer = 0.0F;
  }
  
  void setLastBombTimer(float paramFloat) {
    this.lastBombTimer = paramFloat;
  }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\Bomb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */