package States;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

class AbilityPoint {

  private TextureAtlas.AtlasRegion[] abilityPoint;
  private boolean isFilled;
  
  AbilityPoint(TextureAtlas paramTextureAtlas) {
    abilityPoint = new TextureAtlas.AtlasRegion[2];
    isFilled = false;
    abilityPoint[0] = paramTextureAtlas.findRegion("ability_point_empty");
    abilityPoint[1] = paramTextureAtlas.findRegion("ability_point_filled");
  }
  
  public void dispose() {}
  
  TextureAtlas.AtlasRegion getAbilityPoint(int paramInt) {
    return abilityPoint[paramInt];
  }
  
  boolean isFilled() {
    return isFilled;
  }
  
  void setFilled() {
    isFilled = true;
  }
}