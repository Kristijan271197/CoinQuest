package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class Villain {
  private TextureAtlas.AtlasRegion villain;
  
  private float villainSpeed = worldXToScreenX(3.0F);
  
  private Float x = Float.valueOf(Gdx.graphics.getWidth());
  
  private float worldXToScreenX(float paramFloat) {
    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
  }
  
  private float worldYToScreenY(float paramFloat) {
    return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
  }
  
  void drawVillain(SpriteBatch paramSpriteBatch, boolean paramBoolean) {
    paramSpriteBatch.draw((TextureRegion)this.villain, this.x.floatValue(), worldYToScreenY(75.0F), worldXToScreenX(100.0F), worldYToScreenY(156.0F));
    if (!paramBoolean)
      this.x = Float.valueOf(this.x.floatValue() - this.villainSpeed * Gdx.graphics.getDeltaTime() * 60.0F); 
    if (this.x.floatValue() <= worldXToScreenX(350.0F))
      this.x = Float.valueOf(worldXToScreenX(350.0F)); 
  }
  
  void initializeValues(TextureAtlas paramTextureAtlas) {
    this.villain = paramTextureAtlas.findRegion("villain");
  }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\com\zappycode\coinman\game\Villain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */