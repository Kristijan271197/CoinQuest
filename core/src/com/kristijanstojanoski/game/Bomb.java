package com.kristijanstojanoski.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Random;

import States.MusicSounds;

class Bomb {
    private TextureAtlas.AtlasRegion bomb;
    private ArrayList<Circle> bombCircles = new ArrayList<Circle>();
    private float bombHeight = worldYToScreenY(75.0F);
    private float bombWidth = worldXToScreenX(75.0F);
    private ArrayList<Integer> bombXs = new ArrayList<>();
    private ArrayList<Integer> bombYs = new ArrayList<>();
    private float lastBombTimer = 0.0F;
    private MusicSounds musicSoundsObject;
    private Random random = new Random(System.currentTimeMillis());

    void initializeValues(TextureAtlas paramTextureAtlas, AssetManager paramAssetManager) {
        bomb = paramTextureAtlas.findRegion("bomb");
        musicSoundsObject = new MusicSounds(paramAssetManager);
    }

    void makeBomb() {
        float height = random.nextFloat() * Gdx.graphics.getHeight();
        if (bombHeight + height >= Gdx.graphics.getHeight())
            bombYs.add((int) (height - bombHeight));
        else if (height <= worldYToScreenY(70.0F))
            bombYs.add((int) worldYToScreenY(70.0F));
        else
            bombYs.add((int) height);

        bombXs.add(Gdx.graphics.getWidth());
    }

    void drawBomb(SpriteBatch batch, boolean pause, ShapeRenderer paramShapeRenderer, float bombSpeed) {
        bombCircles.clear();
        for (int i = 0; i < bombXs.size(); i++) {
            batch.draw(bomb, bombXs.get(i), bombYs.get(i), bombWidth, bombHeight);
            if (!pause)
                bombXs.set(i, (int) (bombXs.get(i) - worldXToScreenX(bombSpeed) * Gdx.graphics.getDeltaTime() * 60.0F));

            bombCircles.add(new Circle(bombXs.get(i) + bombHeight / 2.2F, bombYs.get(i) + bombHeight / 2.5F, bombHeight / 2.4F));
        }
    }


    int bombCollision(Rectangle paramRectangle, int gameState, Preferences prefs) {
        for (int i = 0; i < bombCircles.size(); i++) {
            if (Intersector.overlaps(this.bombCircles.get(i), paramRectangle)) {
                this.musicSoundsObject.playBombSound();
                prefs.putBoolean(Player.PLAYER_GROUND, true);
                prefs.flush();
                gameState = 2;
                bombXs.remove(i);
                bombYs.remove(i);
                bombCircles.remove(i);
                break;
            }
        }
        return gameState;
    }

    public void dispose() {
    }


    float getLastBombTimer() {
        return lastBombTimer;
    }


    void resetBombStats() {
        bombXs.clear();
        bombYs.clear();
        bombCircles.clear();
        lastBombTimer = 0.0F;
    }

    void setLastBombTimer(float paramFloat) {
        lastBombTimer = paramFloat;
    }

    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY(float paramFloat) {
        return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
    }

}

