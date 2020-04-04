package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Settings extends State {
    public static final String MUSIC = "music";
    public static final String SOUND = "sound";

    private ImageButton.ImageButtonStyle checkedMarkButtonStyle;
    private ImageButton musicButton;
    private MusicSounds musicSoundsObject;
    private Preferences prefs;
    private ImageButton soundButton;
    private Stage stage;
    private ImageButton.ImageButtonStyle xButtonStyle;

    Settings(final GameStateManager gsm, final AdsController adsController, final AssetManager manager) {
        super(gsm);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this.stage);

        prefs = Gdx.app.getPreferences("prefs");
        musicSoundsObject = new MusicSounds(manager);

        TextureAtlas sharedAtlas = manager.get("shared/shared.atlas", TextureAtlas.class);
        TextureAtlas settingsAtlas = manager.get("settings/settings.atlas", TextureAtlas.class);
        TextureAtlas achievementsAtlas = manager.get("achievements/achievements.atlas", TextureAtlas.class);
        TextureAtlas mainGameAtlas = manager.get("main_game/main_game.atlas", TextureAtlas.class);

        Image bg = new Image(sharedAtlas.findRegion("menu_background"));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.setPosition(0.0F, 0.0F);

        Image soundMusicText = new Image(settingsAtlas.findRegion("sound_music_text"));
        soundMusicText.setSize(worldXToScreenX(300.0F), worldYToScreenY(200.0F));
        soundMusicText.setPosition(worldXToScreenX(100.0F), worldYToScreenY(650.0F));

        checkedMarkButtonStyle = new ImageButton.ImageButtonStyle();
        checkedMarkButtonStyle.up = new TextureRegionDrawable(new TextureRegion(achievementsAtlas.findRegion("achievement_completed")));
        checkedMarkButtonStyle.down = new TextureRegionDrawable(new TextureRegion(achievementsAtlas.findRegion("achievement_completed")));

        xButtonStyle = new ImageButton.ImageButtonStyle();
        xButtonStyle.up = new TextureRegionDrawable(new TextureRegion(settingsAtlas.findRegion("x_sound_music")));
        xButtonStyle.down = new TextureRegionDrawable(new TextureRegion(settingsAtlas.findRegion("x_sound_music")));

        soundButton = new ImageButton(checkedMarkButtonStyle);
        soundButton.setPosition(worldXToScreenX(330.0F), worldYToScreenY(782.0F));
        soundButton.setSize(worldXToScreenX(80.0F), worldYToScreenY(63.0F));
        soundButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (prefs.getBoolean(SOUND, true)) {
                    prefs.putBoolean(SOUND, false);
                    prefs.flush();
                    musicSoundsObject.playButtonClick();
                } else {
                    prefs.putBoolean(SOUND, true);
                    prefs.flush();
                    musicSoundsObject.playButtonClick();
                }
            }
        });

        musicButton = new ImageButton(checkedMarkButtonStyle);
        musicButton.setPosition(worldXToScreenX(330.0F), worldYToScreenY(665.0F));
        musicButton.setSize(worldXToScreenX(80.0F), worldYToScreenY(63.0F));
        musicButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                if (prefs.getBoolean(MUSIC, true)) {
                    prefs.putBoolean(MUSIC, false);
                    prefs.flush();
                    musicSoundsObject.getBackgroundMusic().stop();
                    musicSoundsObject.playButtonClick();
                } else {
                    prefs.putBoolean(MUSIC, true);
                    prefs.flush();
                    musicSoundsObject.playButtonClick();
                    musicSoundsObject.playBackgroundMusic();
                }
            }
        });

        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
        backButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_unpressed")));
        backButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_pressed")));

        ImageButton backButton = new ImageButton(backButtonStyle);
        backButton.setPosition(worldXToScreenX(20.0F), worldYToScreenY(20.0F));
        backButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        backButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                gsm.set(new MainMenu(gsm, adsController, manager));
                dispose();
            }
        });

        Image sureQuitWindow = new Image(sharedAtlas.findRegion("sure_quit_window"));
        sureQuitWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(210.0F));
        sureQuitWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(395.0F));

        ImageButton.ImageButtonStyle notQuitButtonStyle = new ImageButton.ImageButtonStyle();
        notQuitButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_unpressed")));
        notQuitButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_pressed")));

        ImageButton notQuitButton = new ImageButton(notQuitButtonStyle);
        notQuitButton.setPosition(worldXToScreenX(116.0F), worldYToScreenY(405.0F));
        notQuitButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        notQuitButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                stage.getActors().get(5).setVisible(false);
                stage.getActors().get(6).setVisible(false);
                stage.getActors().get(7).setVisible(false);
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton.ImageButtonStyle quitButtonStyle = new ImageButton.ImageButtonStyle();
        quitButtonStyle.up = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_unpressed")));
        quitButtonStyle.down = new TextureRegionDrawable(new TextureRegion(mainGameAtlas.findRegion("story_continue_button_pressed")));

        ImageButton quitButton = new ImageButton(quitButtonStyle);
        quitButton.setPosition(worldXToScreenX(307.0F), worldYToScreenY(405.0F));
        quitButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        quitButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                musicSoundsObject.playButtonClick();
                Gdx.app.exit();
            }
        });

        stage.addActor(bg);
        stage.addActor(soundMusicText);
        stage.addActor(backButton);
        stage.addActor(musicButton);
        stage.addActor(soundButton);
        stage.addActor(sureQuitWindow);
        stage.addActor(notQuitButton);
        stage.addActor(quitButton);
    }

    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY(float paramFloat) {
        return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
    }

    public void dispose() {
        stage.dispose();
    }

    public void handleInput() {
    }

    public void render(SpriteBatch paramSpriteBatch) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        Gdx.input.setCatchKey(4, true);
        if (Gdx.input.isKeyPressed(4)) {
            stage.getActors().get(5).setVisible(true);
            stage.getActors().get(6).setVisible(true);
            stage.getActors().get(7).setVisible(true);
        }

        if (prefs.getBoolean(SOUND, true)) {
            soundButton.setStyle(checkedMarkButtonStyle);
            soundButton.setPosition(worldXToScreenX(330.0F), worldYToScreenY(782.0F));
            soundButton.setSize(worldXToScreenX(80.0F), worldYToScreenY(63.0F));
        } else {
            soundButton.setPosition(worldXToScreenX(332.0F), worldYToScreenY(782.0F));
            soundButton.setSize(worldXToScreenX(55.0F), worldYToScreenY(58.0F));
            soundButton.setStyle(xButtonStyle);
        }
        if (prefs.getBoolean(MUSIC, true)) {
            musicButton.setSize(worldXToScreenX(80.0F), worldYToScreenY(63.0F));
            musicButton.setPosition(worldXToScreenX(330.0F), worldYToScreenY(665.0F));
            musicButton.setStyle(checkedMarkButtonStyle);
        } else {
            musicButton.setSize(worldXToScreenX(55.0F), worldYToScreenY(58.0F));
            musicButton.setPosition(worldXToScreenX(332.0F), worldYToScreenY(665.0F));
            musicButton.setStyle(xButtonStyle);
        }
    }

    public void update(float paramFloat) {
    }
}
