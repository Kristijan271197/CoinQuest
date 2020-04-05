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

class Rocket {
    private int arrowStateGreen = 0;
    private int arrowStateRed = 0;
    private int arrowStateYellow = 0;
    private float greenHeight;
    private float lastArrowGreenTimer = 0.0F;
    private float lastArrowRedTimer = 0.0F;
    private float lastArrowYellowTimer = 0.0F;
    private Random random = new Random(System.currentTimeMillis());
    private float redHeight;
    private TextureAtlas.AtlasRegion rocket;
    private TextureAtlas.AtlasRegion[] rocketArrowGreen = new TextureAtlas.AtlasRegion[3];
    private TextureAtlas.AtlasRegion[] rocketArrowRed = new TextureAtlas.AtlasRegion[3];
    private TextureAtlas.AtlasRegion[] rocketArrowYellow = new TextureAtlas.AtlasRegion[3];
    private int rocketsArrowX;
    private int rocketsArrowYGreen;
    private int rocketsArrowYRed;
    private int rocketsArrowYYellow;
    private ArrayList<Rectangle> rocketsRectangles = new ArrayList<>();
    private ArrayList<Integer> rocketsXs = new ArrayList<>();
    private ArrayList<Integer> rocketsYs = new ArrayList<>();
    private float yellowHeight;


    void initializeValues(TextureAtlas mainGameAtlas) {
        rocket = mainGameAtlas.findRegion("rocket");
        for (int i = 0; i < 3; i++) {
            rocketArrowRed[i] = mainGameAtlas.findRegion("rocket_arrow", 3 - i);
            rocketArrowYellow[i] = mainGameAtlas.findRegion("rocket_arrow", 3 - i);
            rocketArrowGreen[i] = mainGameAtlas.findRegion("rocket_arrow", 3 - i);
        }
        redHeight = makeRocketArrowRed();
        yellowHeight = makeRocketArrowYellow();
        greenHeight = makeRocketArrowGreen();
    }


    void drawArrows(boolean firstArrowRed, boolean firstArrowYellow, boolean firstArrowGreen, SpriteBatch batch) {
        if (firstArrowRed)
            batch.draw(rocketArrowRed[arrowStateRed], rocketsArrowX, rocketsArrowYRed, worldXToScreenX(96.0F), worldYToScreenY(50.0F));
        if (firstArrowYellow)
            batch.draw(rocketArrowYellow[arrowStateYellow], rocketsArrowX, rocketsArrowYYellow, worldXToScreenX(96.0F), worldYToScreenY(50.0F));
        if (firstArrowGreen)
            batch.draw(rocketArrowGreen[arrowStateGreen], rocketsArrowX, rocketsArrowYGreen, worldXToScreenX(96.0F), worldYToScreenY(50.0F));
    }

    void drawRocket(SpriteBatch batch, boolean pause, ShapeRenderer paramShapeRenderer, float rocketSpeed) {
        rocketsRectangles.clear();
        for (int i = 0; i < rocketsXs.size(); i++) {
            batch.draw(rocket, rocketsXs.get(i), rocketsYs.get(i), worldXToScreenX(145.0F), worldYToScreenY(50.0F));
            if (!pause)
                rocketsXs.set(i, (int) (rocketsXs.get(i) - worldXToScreenX(rocketSpeed) * Gdx.graphics.getDeltaTime() * 60.0F));
            rocketsRectangles.add(new Rectangle(rocketsXs.get(i), rocketsYs.get(i), worldXToScreenX(145.0F), worldYToScreenY(50.0F)));
        }
    }


    void makeRocket(float height) {
        if (worldYToScreenY(50.0F) + height >= Gdx.graphics.getHeight())
            rocketsYs.add((int) (height - worldXToScreenX(50.0F)));
        else if (height <= worldYToScreenY(70.0F))
            rocketsYs.add((int) worldYToScreenY(70.0F));
        else
            rocketsYs.add((int) height);
        rocketsXs.add(Gdx.graphics.getWidth());
    }


    float makeRocketArrowRed() {
        float height = random.nextFloat() * Gdx.graphics.getHeight();
        if (worldYToScreenY(50.0F) + height >= Gdx.graphics.getHeight())
            rocketsArrowYRed = (int) (height - worldXToScreenX(50.0F));
        else if (height <= worldYToScreenY(70.0F))
            rocketsArrowYRed = (int) worldYToScreenY(70.0F);
        else
            rocketsArrowYRed = (int) height;

        rocketsArrowX = (int) (Gdx.graphics.getWidth() - worldXToScreenX(96.0F));
        return height;
    }

    float makeRocketArrowYellow() {
        float height = random.nextFloat() * Gdx.graphics.getHeight();
        if (worldYToScreenY(50.0F) + height >= Gdx.graphics.getHeight())
            rocketsArrowYYellow = (int) (height - worldXToScreenX(50.0F));
        else if (height <= worldYToScreenY(70.0F))
            rocketsArrowYYellow = (int) worldYToScreenY(70.0F);
        else
            rocketsArrowYYellow = (int) height;

        rocketsArrowX = (int) (Gdx.graphics.getWidth() - worldXToScreenX(96.0F));
        return height;
    }


    float makeRocketArrowGreen() {
        float height = random.nextFloat() * Gdx.graphics.getHeight();
        if (worldYToScreenY(50.0F) + height >= Gdx.graphics.getHeight())
            rocketsArrowYGreen = (int) (height - worldXToScreenX(50.0F));
        else if (height <= worldYToScreenY(70.0F))
            rocketsArrowYGreen = (int) worldYToScreenY(70.0F);
        else
            rocketsArrowYGreen = (int) height;

        rocketsArrowX = (int) (Gdx.graphics.getWidth() - worldXToScreenX(96.0F));
        return height;
    }


    int rocketCollision(Rectangle paramRectangle, int gameState, Preferences prefs) {
        for (int i = 0; i < this.rocketsRectangles.size(); i++) {
            if (Intersector.overlaps(paramRectangle, this.rocketsRectangles.get(i))) {
                prefs.putBoolean(Player.PLAYER_GROUND, true);
                prefs.flush();
                gameState = 2;
                rocketsXs.remove(i);
                rocketsYs.remove(i);
                rocketsRectangles.remove(i);
                break;
            }
        }
        return gameState;
    }


    void resetRocketStats() {
        rocketsXs.clear();
        rocketsYs.clear();
        rocketsRectangles.clear();
        lastArrowRedTimer = 0.0F;
        lastArrowYellowTimer = 0.0F;
        lastArrowGreenTimer = 0.0F;
    }


    void redArrowState(float timerRed) {
        if (timerRed - lastArrowRedTimer >= 1.9F && timerRed - lastArrowRedTimer <= 2.0F)
            arrowStateRed = 2;
        else if (timerRed - lastArrowRedTimer >= 1.0F && timerRed - lastArrowRedTimer <= 1.1F)
            arrowStateRed = 1;
        else if (timerRed - lastArrowRedTimer >= 0.0F && timerRed - lastArrowRedTimer <= 0.1F)
            arrowStateRed = 0;
    }

    void yellowArrowState(float timerYellow) {
        if (timerYellow - lastArrowYellowTimer >= 1.9F && timerYellow - lastArrowYellowTimer <= 2.0F)
            arrowStateYellow = 2;
        else if (timerYellow - lastArrowYellowTimer >= 1.0F && timerYellow - lastArrowYellowTimer <= 1.1F)
            arrowStateYellow = 1;
        else if (timerYellow - lastArrowYellowTimer >= 0.0F && timerYellow - lastArrowYellowTimer <= 0.1F)
            arrowStateYellow = 0;
    }

    void greenArrowState(float timerGreen) {
        if (timerGreen - lastArrowGreenTimer >= 1.9F && timerGreen - lastArrowGreenTimer <= 2.0F)
            arrowStateGreen = 2;
        else if (timerGreen - lastArrowGreenTimer >= 1.0F && timerGreen - lastArrowGreenTimer <= 1.1F)
            arrowStateGreen = 1;
        else if (timerGreen - lastArrowGreenTimer >= 0.0F && timerGreen - lastArrowGreenTimer <= 0.1F)
            arrowStateGreen = 0;
    }

    void setGreenHeight(float paramFloat) {
        this.greenHeight = paramFloat;
    }

    void setLastArrowGreenTimer(float paramFloat) {
        this.lastArrowGreenTimer = paramFloat;
    }

    void setLastArrowRedTimer(float paramFloat) {
        this.lastArrowRedTimer = paramFloat;
    }

    void setLastArrowYellowTimer(float paramFloat) {
        this.lastArrowYellowTimer = paramFloat;
    }

    void setRedHeight(float paramFloat) {
        this.redHeight = paramFloat;
    }

    void setYellowHeight(float paramFloat) {
        this.yellowHeight = paramFloat;
    }

    float getGreenHeight() {
        return this.greenHeight;
    }

    float getLastArrowGreenTimer() {
        return this.lastArrowGreenTimer;
    }

    float getLastArrowRedTimer() {
        return this.lastArrowRedTimer;
    }

    float getLastArrowYellowTimer() {
        return this.lastArrowYellowTimer;
    }

    float getRedHeight() {
        return this.redHeight;
    }

    float getYellowHeight() {
        return this.yellowHeight;
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
