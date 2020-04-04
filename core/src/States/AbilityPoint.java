package States;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

class AbilityPoint {
  private TextureAtlas.AtlasRegion[] abilityPoint;
  
  private boolean isFilled;
  
  AbilityPoint(TextureAtlas paramTextureAtlas) {
    TextureAtlas.AtlasRegion[] arrayOfAtlasRegion = new TextureAtlas.AtlasRegion[2];
    this.abilityPoint = arrayOfAtlasRegion;
    this.isFilled = false;
    arrayOfAtlasRegion[0] = paramTextureAtlas.findRegion("ability_point_empty");
    this.abilityPoint[1] = paramTextureAtlas.findRegion("ability_point_filled");
  }
  
  public void dispose() {}
  
  TextureAtlas.AtlasRegion getAbilityPoint(int paramInt) {
    return this.abilityPoint[paramInt];
  }
  
  boolean isFilled() {
    return this.isFilled;
  }
  
  void setFilled() {
    this.isFilled = true;
  }
}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\States\AbilityPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */