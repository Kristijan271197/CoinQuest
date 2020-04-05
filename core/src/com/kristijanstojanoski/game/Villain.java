package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class Villain {
    private TextureAtlas.AtlasRegion villain;
    private float villainSpeed = worldXToScreenX(3.0F);
    private Float x = (float) Gdx.graphics.getWidth();

    void initializeValues(TextureAtlas paramTextureAtlas) {
        villain = paramTextureAtlas.findRegion("villain");
    }

    void drawVillain(SpriteBatch batch, boolean pause) {
        batch.draw(villain, x, worldYToScreenY(75.0F), worldXToScreenX(100.0F), worldYToScreenY(156.0F));
        if (!pause)
            x = x - villainSpeed * Gdx.graphics.getDeltaTime() * 60.0F;
        if (x <= worldXToScreenX(350.0F))
            x = worldXToScreenX(350.0F);
    }

    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY(float paramFloat) {
        return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
    }

}

