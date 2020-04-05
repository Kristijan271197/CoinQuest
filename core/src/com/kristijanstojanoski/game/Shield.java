package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

import States.EndGame;
import States.Shop;

class Shield {
    private boolean hasShield = false;
    private Random random = new Random(System.currentTimeMillis());
    private TextureAtlas.AtlasRegion[] shield = new TextureAtlas.AtlasRegion[2];
    private TextureAtlas.AtlasRegion shieldCollectible;
    private ArrayList<Rectangle> shieldCollectibleRectangles = new ArrayList<>();
    private ArrayList<Integer> shieldCollectibleXs = new ArrayList<>();
    private ArrayList<Integer> shieldCollectibleYs = new ArrayList<>();
    private float shieldOnTimer = 0.0F;
    private int shieldState = 0;
    private int shieldStatePause;


    void initializeValues(TextureAtlas mainGameAtlas) {
        shield[0] = mainGameAtlas.findRegion("shield");
        shield[1] = mainGameAtlas.findRegion("transparent");
        shieldCollectible = mainGameAtlas.findRegion("shield_collectible");
    }


    void drawShield(SpriteBatch batch, float playerX, float playerY, boolean pause, Preferences prefs) {
        if (!pause) {
            if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.PLAYER_NUMBER)
                batch.draw(shield[shieldState], playerX - worldXToScreenX(15.0F), playerY, worldXToScreenX(133.3F), worldYToScreenY(160.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.ROBOT_NUMBER)
                batch.draw(shield[shieldState], playerX + worldXToScreenX(5.0F), playerY - worldYToScreenY(5.0F), worldXToScreenX(150.0F), worldYToScreenY(175.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.KNIGHT_NUMBER)
                batch.draw(shield[shieldState], playerX - worldXToScreenX(10.0F), playerY, worldXToScreenX(150.0F), worldYToScreenY(175.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWBOY_NUMBER)
                batch.draw(shield[shieldState], playerX - worldXToScreenX(10.0F), playerY - worldYToScreenY(5.0F), worldXToScreenX(140.0F), worldYToScreenY(165.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWGIRL_NUMBER)
                batch.draw(shield[shieldState], playerX + worldXToScreenX(15.0F), playerY - worldYToScreenY(5.0F), worldXToScreenX(140.0F), worldYToScreenY(160.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_MALE_NUMBER)
                batch.draw(shield[shieldState], playerX - worldXToScreenX(10.0F), playerY - worldYToScreenY(5.0F), worldXToScreenX(140.0F), worldYToScreenY(160.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_FEMALE_NUMBER)
                batch.draw(shield[shieldState], playerX - worldXToScreenX(15.0F), playerY - worldYToScreenY(5.0F), worldXToScreenX(150.0F), worldYToScreenY(170.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.DINO_NUMBER)
                batch.draw(shield[shieldState], playerX - worldXToScreenX(10.0F), playerY - worldYToScreenY(5.0F), worldXToScreenX(150.0F), worldYToScreenY(160.0F));

        } else {
            if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.PLAYER_NUMBER)
                batch.draw(shield[0], playerX - worldXToScreenX(15.0F), playerY, worldXToScreenX(133.3F), worldYToScreenY(160.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.ROBOT_NUMBER)
                batch.draw(shield[0], playerX + worldXToScreenX(5.0F), playerY - worldYToScreenY(5.0F), worldXToScreenX(150.0F), worldYToScreenY(175.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.KNIGHT_NUMBER)
                batch.draw(shield[0], playerX - worldXToScreenX(10.0F), playerY, worldXToScreenX(150.0F), worldYToScreenY(175.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWBOY_NUMBER)
                batch.draw(shield[0], playerX - worldXToScreenX(10.0F), playerY - worldYToScreenY(5.0F), worldXToScreenX(140.0F), worldYToScreenY(165.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.COWGIRL_NUMBER)
                batch.draw(shield[0], playerX + worldXToScreenX(15.0F), playerY - worldYToScreenY(5.0F), worldXToScreenX(140.0F), worldYToScreenY(160.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_MALE_NUMBER)
                batch.draw(shield[0], playerX - worldXToScreenX(10.0F), playerY - worldYToScreenY(5.0F), worldXToScreenX(140.0F), worldYToScreenY(160.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.NINJA_FEMALE_NUMBER)
                batch.draw(shield[0], playerX - worldXToScreenX(15.0F), playerY - worldYToScreenY(5.0F), worldXToScreenX(150.0F), worldYToScreenY(170.0F));
            else if (prefs.getInteger(Shop.COSTUME_SELECTED_GAME) == Shop.DINO_NUMBER)
                batch.draw(shield[0], playerX - worldXToScreenX(10.0F), playerY - worldYToScreenY(5.0F), worldXToScreenX(150.0F), worldYToScreenY(160.0F));
        }
    }

    void drawShieldCollectible(SpriteBatch batch, boolean pause) {
        shieldCollectibleRectangles.clear();
        for (int i = 0; i < shieldCollectibleXs.size(); i++) {
            batch.draw(shieldCollectible, shieldCollectibleXs.get(i), shieldCollectibleYs.get(i), worldXToScreenX(62.0F), worldYToScreenY(70.0F));
            if (!pause)
                shieldCollectibleXs.set(i, (int) (shieldCollectibleXs.get(i) - worldXToScreenX(5.0F) * Gdx.graphics.getDeltaTime() * 60.0F));
            shieldCollectibleRectangles.add(new Rectangle(shieldCollectibleXs.get(i) + worldXToScreenX(13.3F), shieldCollectibleYs.get(i), worldXToScreenX(44.4F), worldYToScreenY(65.0F)));
        }
    }

    void makeShield() {
        float height = random.nextFloat() * Gdx.graphics.getHeight();
        if (worldYToScreenY(70.0F) + height >= Gdx.graphics.getHeight())
            shieldCollectibleYs.add((int) (height - worldYToScreenY(70.0F)));
        else if (height <= worldYToScreenY(70.0F))
            shieldCollectibleYs.add((int) worldYToScreenY(70.0F));
        else
            shieldCollectibleYs.add((int) height);
        shieldCollectibleXs.add(Gdx.graphics.getWidth());
    }


    void shieldCollision(Rectangle paramRectangle, float shieldTimer, Preferences prefs) {
        for (int i = 0; i < shieldCollectibleRectangles.size(); i++) {
            if (Intersector.overlaps(paramRectangle, shieldCollectibleRectangles.get(i))) {
                prefs.putInteger(EndGame.POWER_UPS_COLLECTED, prefs.getInteger(EndGame.POWER_UPS_COLLECTED, 0) + 1);
                prefs.flush();
                hasShield = true;
                shieldOnTimer = shieldTimer;
                shieldState = 0;
                shieldCollectibleRectangles.remove(i);
                shieldCollectibleXs.remove(i);
                shieldCollectibleYs.remove(i);
                break;
            }
        }
    }

    void resetShieldStats() {
        shieldCollectibleXs.clear();
        shieldCollectibleYs.clear();
        shieldCollectibleRectangles.clear();
        hasShield = false;
    }

    boolean isHasShield() {
        return this.hasShield;
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

    float getShieldOnTimer() {
        return this.shieldOnTimer;
    }

    int getShieldState() {
        return this.shieldState;
    }

    int getShieldStatePause() {
        return this.shieldStatePause;
    }


    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY(float paramFloat) {
        return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
    }

    public void dispose() {
    }
}
