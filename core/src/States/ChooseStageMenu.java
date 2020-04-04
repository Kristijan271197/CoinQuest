package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.kristijanstojanoski.game.MainGame;


public class ChooseStageMenu extends State {
    private boolean arcticInfoShow = false;
    private boolean cityInfoShow = false;
    private BitmapFont coinFont;
    private boolean desertInfoShow = false;
    private int firstButton = 1;
    private boolean forestInfoShow = false;
    private AdsController mAdsController;
    private MusicSounds musicSoundsObject;
    private Preferences prefs;
    private Stage stage;
    private BitmapFont textFont;

    public ChooseStageMenu(GameStateManager paramGameStateManager, AdsController adsController, final AssetManager manager) {
        super(paramGameStateManager);
        this.mAdsController = adsController;
        stage = new Stage(new ScreenViewport());
        prefs = Gdx.app.getPreferences("prefs");
        Gdx.input.setInputProcessor(stage);

        TextureAtlas sharedAtlas = manager.get("shared/shared.atlas", TextureAtlas.class);
        TextureAtlas mainGameAtlas = manager.get("main_game/main_game.atlas", TextureAtlas.class);
        TextureAtlas chooseStageAtlas = manager.get("choose_stage/choose_stage.atlas", TextureAtlas.class);
        musicSoundsObject = new MusicSounds(manager);

        Image bg = new Image(sharedAtlas.findRegion("menu_background"));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        bg.setPosition(0.0F, 0.0F);


        coinFont = manager.get("font/font_scale_15.fnt", BitmapFont.class);
        coinFont.setColor(Color.WHITE);
        coinFont.getData().setScale(worldXToScreenX(1.5F), worldYToScreenY(1.5F));
        textFont = manager.get("font/font_scale_1.fnt", BitmapFont.class);
        textFont.setColor(Color.WHITE);
        textFont.getData().setScale(worldXToScreenX(1.0F), worldYToScreenY(1.0F));

        ImageButton.ImageButtonStyle cityStageStyle = new ImageButton.ImageButtonStyle();
        cityStageStyle.up = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_city")));
        cityStageStyle.down = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_city")));

        ImageButton cityButton = new ImageButton(cityStageStyle);
        cityButton.setPosition(worldXToScreenX(50.0F), worldYToScreenY(700.0F));
        cityButton.setSize(worldXToScreenX(400.0F), worldYToScreenY(160.0F));
        cityButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                //gsm.set(new MainGame(gsm, mAdsController, manager, 1));
                musicSoundsObject.playButtonClick();
                musicSoundsObject.getBackgroundMusic().stop();
                //dispose();
            }
        });

        ImageButton.ImageButtonStyle desertStageStyle = new ImageButton.ImageButtonStyle();
        desertStageStyle.up = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_desert")));
        desertStageStyle.down = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_desert")));

        ImageButton desertButton = new ImageButton(desertStageStyle);
        desertButton.setPosition(worldXToScreenX(50.0F), worldYToScreenY(420.0F));
        desertButton.setSize(worldXToScreenX(400.0F), worldYToScreenY(160.0F));
        desertButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                //gsm.set(new MainGame(gsm, mAdsController, manager, 2));
                musicSoundsObject.playButtonClick();
                musicSoundsObject.getBackgroundMusic().stop();
                //dispose();
            }
        });

        ImageButton.ImageButtonStyle forestStageStyle = new ImageButton.ImageButtonStyle();
        forestStageStyle.up = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_forest")));
        forestStageStyle.down = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_forest")));

        ImageButton forestButton = new ImageButton(forestStageStyle);
        forestButton.setPosition(worldXToScreenX(50.0F), worldYToScreenY(140.0F));
        forestButton.setSize(worldXToScreenX(400.0F), worldYToScreenY(160.0F));
        forestButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
            }
        });

        ImageButton.ImageButtonStyle arcticButtonStyle = new ImageButton.ImageButtonStyle();
        arcticButtonStyle.up = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_arctic")));
        arcticButtonStyle.down = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_arctic")));

        ImageButton arcticButton = new ImageButton(arcticButtonStyle);
        arcticButton.setPosition(worldXToScreenX(50.0F), worldYToScreenY(-200.0F));
        arcticButton.setSize(worldXToScreenX(400.0F), worldYToScreenY(160.0F));
        arcticButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
            }
        });

        ImageButton.ImageButtonStyle stageUpButtonStyle = new ImageButton.ImageButtonStyle();
        stageUpButtonStyle.up = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("choose_stage_up_button_unpressed")));
        stageUpButtonStyle.down = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("choose_stage_up_button_pressed")));

        ImageButton stageUpButton = new ImageButton(stageUpButtonStyle);
        stageUpButton.setPosition(worldXToScreenX(220.0F), worldYToScreenY(900.0F));
        stageUpButton.setSize(worldXToScreenX(60.0F), worldYToScreenY(60.0F));
        stageUpButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                firstButton--;
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton.ImageButtonStyle stageDownButtonStyle = new ImageButton.ImageButtonStyle();
        stageDownButtonStyle.up = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("choose_stage_down_button_unpressed")));
        stageDownButtonStyle.down = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("choose_stage_down_button_pressed")));

        ImageButton stageDownButton = new ImageButton(stageDownButtonStyle);
        stageDownButton.setPosition(worldXToScreenX(220.0F), worldYToScreenY(40.0F));
        stageDownButton.setSize(worldXToScreenX(60.0F), worldYToScreenY(60.0F));
        stageDownButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                firstButton++;
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton.ImageButtonStyle stageInfoButtonStyle = new ImageButton.ImageButtonStyle();
        stageInfoButtonStyle.up = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_info_button_unpressed")));
        stageInfoButtonStyle.down = new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_info_button_pressed")));

        ImageButton stageInfoCity = new ImageButton(stageInfoButtonStyle);
        stageInfoCity.setPosition(worldXToScreenX(395.0F), worldYToScreenY(705.0F));
        stageInfoCity.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        stageInfoCity.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                stage.getActors().get(17).setVisible(true);
                stage.getActors().get(18).setVisible(true);
                infoWindowOpened();
                musicSoundsObject.playButtonClick();
                cityInfoShow = true;
            }
        });

        ImageButton stageInfoDesert = new ImageButton(stageInfoButtonStyle);
        stageInfoDesert.setPosition(worldXToScreenX(395.0F), worldYToScreenY(425.0F));
        stageInfoDesert.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        stageInfoDesert.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                desertInfoShow = true;
                stage.getActors().get(17).setVisible(true);
                stage.getActors().get(18).setVisible(true);
                infoWindowOpened();
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton stageInfoForest = new ImageButton(stageInfoButtonStyle);
        stageInfoForest.setPosition(worldXToScreenX(395.0F), worldYToScreenY(145.0F));
        stageInfoForest.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        stageInfoForest.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                forestInfoShow = true;
                stage.getActors().get(17).setVisible(true);
                stage.getActors().get(18).setVisible(true);
                infoWindowOpened();
                musicSoundsObject.playButtonClick();
            }
        });

        ImageButton stageInfoArctic = new ImageButton(stageInfoButtonStyle);
        stageInfoArctic.setPosition(worldXToScreenX(395.0F), worldYToScreenY(-205.0F));
        stageInfoArctic.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        stageInfoArctic.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                arcticInfoShow = true;
                stage.getActors().get(17).setVisible(true);
                stage.getActors().get(18).setVisible(true);
                infoWindowOpened();
                musicSoundsObject.playButtonClick();
            }
        });

        Image stageInfoWindow = new Image(new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_info_window"))));
        stageInfoWindow.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
        stageInfoWindow.setPosition(worldXToScreenX(10.0F), worldYToScreenY(375.0F));

        ImageButton.ImageButtonStyle xButtonStyle = new ImageButton.ImageButtonStyle();
        xButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_unpressed")));
        xButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("congratulations_window_x_button_pressed")));

        ImageButton xButton = new ImageButton(xButtonStyle);
        xButton.setPosition(worldXToScreenX(420.0F), worldYToScreenY(490.0F));
        xButton.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
        xButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                cityInfoShow = false;
                desertInfoShow = false;
                forestInfoShow = false;
                arcticInfoShow = false;
                stage.getActors().get(17).setVisible(false);
                stage.getActors().get(18).setVisible(false);
                ChooseStageMenu.this.infoWindowClosed();
                ChooseStageMenu.this.musicSoundsObject.playButtonClick();
            }
        });

        Image desertLock = new Image(new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_lock"))));
        desertLock.setSize(worldXToScreenX(80.0F), worldYToScreenY(80.0F));
        desertLock.setPosition(worldXToScreenX(210.0F), worldYToScreenY(460.0F));

        Image forestLock = new Image(new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_lock"))));
        forestLock.setSize(worldXToScreenX(80.0F), worldYToScreenY(80.0F));
        forestLock.setPosition(worldXToScreenX(210.0F), worldYToScreenY(180.0F));

        Image arcticLock = new Image(new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("stage_lock"))));
        arcticLock.setSize(worldXToScreenX(80.0F), worldYToScreenY(80.0F));
        arcticLock.setPosition(worldXToScreenX(210.0F), worldYToScreenY(-160.0F));

        Image desertBlurry = new Image(new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("choose_stage_blurry"))));
        desertBlurry.setColor(255.0F, 255.0F, 255.0F, 0.5F);
        desertBlurry.setSize(worldXToScreenX(400.0F), worldYToScreenY(160.0F));
        desertBlurry.setPosition(worldXToScreenX(50.0F), worldYToScreenY(420.0F));

        Image forestBlurry = new Image(new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("choose_stage_blurry_coming_soon"))));
        forestBlurry.setColor(255.0F, 255.0F, 255.0F, 0.85F);
        forestBlurry.setSize(worldXToScreenX(400.0F), worldYToScreenY(160.0F));
        forestBlurry.setPosition(worldXToScreenX(50.0F), worldYToScreenY(140.0F));

        Image arcticBlurry = new Image(new TextureRegionDrawable(new TextureRegion(chooseStageAtlas.findRegion("choose_stage_blurry_coming_soon"))));
        arcticBlurry.setColor(255.0F, 255.0F, 255.0F, 0.85F);
        arcticBlurry.setSize(worldXToScreenX(400.0F), worldYToScreenY(160.0F));
        arcticBlurry.setPosition(worldXToScreenX(50.0F), worldYToScreenY(-200.0F));

        ImageButton.ImageButtonStyle backButtonStyle = new ImageButton.ImageButtonStyle();
        backButtonStyle.up = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_unpressed")));
        backButtonStyle.down = new TextureRegionDrawable(new TextureRegion(sharedAtlas.findRegion("back_button_pressed")));

        ImageButton backButton = new ImageButton(backButtonStyle);
        backButton.setPosition(worldXToScreenX(20.0F), worldYToScreenY(20.0F));
        backButton.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
        backButton.addListener(new ClickListener() {
            public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
                super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
                gsm.set(new MainMenu(gsm, mAdsController, manager));
                musicSoundsObject.playButtonClick();
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
                stage.getActors().get(20).setVisible(false);
                stage.getActors().get(21).setVisible(false);
                stage.getActors().get(22).setVisible(false);
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
        stage.addActor(cityButton);
        stage.addActor(desertButton);
        stage.addActor(forestButton);
        stage.addActor(arcticButton);
        stage.addActor(stageUpButton);
        stage.addActor(stageDownButton);
        stage.addActor(desertBlurry);
        stage.addActor(forestBlurry);
        stage.addActor(arcticBlurry);
        stage.addActor(stageInfoCity);
        stage.addActor(stageInfoDesert);
        stage.addActor(stageInfoForest);
        stage.addActor(stageInfoArctic);
        stage.addActor(desertLock);
        stage.addActor(forestLock);
        stage.addActor(arcticLock);
        stage.addActor(stageInfoWindow);
        stage.addActor(xButton);
        stage.addActor(backButton);
        stage.addActor(sureQuitWindow);
        stage.addActor(notQuitButton);
        stage.addActor(quitButton);
        stage.getActors().get(2).setTouchable(Touchable.disabled);
        stage.getActors().get(3).setTouchable(Touchable.disabled);
        stage.getActors().get(4).setTouchable(Touchable.disabled);
        stage.getActors().get(5).setVisible(false);
        stage.getActors().get(15).setVisible(false);
        stage.getActors().get(16).setVisible(false);
        stage.getActors().get(17).setVisible(false);
        stage.getActors().get(18).setVisible(false);
        stage.getActors().get(20).setVisible(false);
        stage.getActors().get(21).setVisible(false);
        stage.getActors().get(22).setVisible(false);

        if ((int) this.prefs.getFloat("storyModeCity") >= 100) {
            stage.getActors().get(2).setTouchable(Touchable.enabled);
            stage.getActors().get(7).setVisible(false);
            stage.getActors().get(14).setVisible(false);
        }
    }


    public void dispose() {
        this.stage.dispose();
    }

    public void handleInput() {
    }

    public void render(SpriteBatch batch) {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        Gdx.input.setCatchKey(4, true);
        if (Gdx.input.isKeyPressed(4)) {
            stage.getActors().get(20).setVisible(true);
            stage.getActors().get(21).setVisible(true);
            stage.getActors().get(22).setVisible(true);
        }

        if (firstButton == 1) {
            stage.getActors().get(1).setPosition(worldXToScreenX(50.0F), worldYToScreenY(700.0F));
            stage.getActors().get(2).setPosition(worldXToScreenX(50.0F), worldYToScreenY(420.0F));
            stage.getActors().get(3).setPosition(worldXToScreenX(50.0F), worldYToScreenY(140.0F));
            stage.getActors().get(4).setPosition(worldXToScreenX(50.0F), worldYToScreenY(-200.0F));
            stage.getActors().get(5).setVisible(false);
            stage.getActors().get(6).setVisible(true);
            stage.getActors().get(7).setPosition(worldXToScreenX(50.0F), worldYToScreenY(420.0F));
            stage.getActors().get(8).setPosition(worldXToScreenX(50.0F), worldYToScreenY(140.0F));
            stage.getActors().get(9).setPosition(worldXToScreenX(50.0F), worldYToScreenY(-200.0F));
            stage.getActors().get(10).setPosition(worldXToScreenX(395.0F), worldYToScreenY(705.0F));
            stage.getActors().get(11).setPosition(worldXToScreenX(395.0F), worldYToScreenY(425.0F));
            stage.getActors().get(12).setPosition(worldXToScreenX(395.0F), worldYToScreenY(145.0F));
            stage.getActors().get(13).setPosition(worldXToScreenX(395.0F), worldYToScreenY(-205.0F));
            stage.getActors().get(14).setPosition(worldXToScreenX(210.0F), worldYToScreenY(460.0F));
            stage.getActors().get(15).setPosition(worldXToScreenX(210.0F), worldYToScreenY(180.0F));
            stage.getActors().get(16).setPosition(worldXToScreenX(210.0F), worldYToScreenY(-160.0F));
        } else if (firstButton == 2) {
            stage.getActors().get(1).setPosition(worldXToScreenX(50.0F), worldYToScreenY(1000.0F));
            stage.getActors().get(2).setPosition(worldXToScreenX(50.0F), worldYToScreenY(700.0F));
            stage.getActors().get(3).setPosition(worldXToScreenX(50.0F), worldYToScreenY(420.0F));
            stage.getActors().get(4).setPosition(worldXToScreenX(50.0F), worldYToScreenY(140.0F));
            stage.getActors().get(5).setVisible(true);
            stage.getActors().get(6).setVisible(false);
            stage.getActors().get(7).setPosition(worldXToScreenX(50.0F), worldYToScreenY(700.0F));
            stage.getActors().get(8).setPosition(worldXToScreenX(50.0F), worldYToScreenY(420.0F));
            stage.getActors().get(9).setPosition(worldXToScreenX(50.0F), worldYToScreenY(140.0F));
            stage.getActors().get(10).setPosition(worldXToScreenX(395.0F), worldYToScreenY(1005.0F));
            stage.getActors().get(11).setPosition(worldXToScreenX(395.0F), worldYToScreenY(705.0F));
            stage.getActors().get(12).setPosition(worldXToScreenX(395.0F), worldYToScreenY(425.0F));
            stage.getActors().get(13).setPosition(worldXToScreenX(395.0F), worldYToScreenY(145.0F));
            stage.getActors().get(14).setPosition(worldXToScreenX(210.0F), worldYToScreenY(740.0F));
            stage.getActors().get(15).setPosition(worldXToScreenX(210.0F), worldYToScreenY(460.0F));
            stage.getActors().get(16).setPosition(worldXToScreenX(210.0F), worldYToScreenY(180.0F));
        }
        batch.begin();
        if (cityInfoShow) {
            coinFont.draw(batch, "City", worldXToScreenX(200.0F), worldYToScreenY(540.0F));
            textFont.draw(batch, "High score: " + prefs.getInteger("highScoreCity", 0), worldXToScreenX(130.0F), worldYToScreenY(490.0F));
            if ((int) prefs.getFloat("storyModeCity") < 100)
                textFont.draw(batch, "Story mode: " + (int) prefs.getFloat("storyModeCity", 0.0F) + "%", worldXToScreenX(130.0F), worldYToScreenY(440.0F));
            else
                textFont.draw(batch, "Story mode completed", worldXToScreenX(90.0F), worldYToScreenY(440.0F));

        }
        if (desertInfoShow) {
            coinFont.draw(batch, "Desert", worldXToScreenX(180.0F), worldYToScreenY(540.0F));
            if ((int) prefs.getFloat("storyModeCity") >= 100) {

                textFont.draw(batch, "High score: " + prefs.getInteger("highScoreDesert", 0), worldXToScreenX(130.0F), worldYToScreenY(490.0F));
                if (this.prefs.getFloat("storyModeDesert") < 100.0F)
                    textFont.draw(batch, "Story mode: " + (int) prefs.getFloat("storyModeDesert", 0.0F) + "%", worldXToScreenX(130.0F), worldYToScreenY(440.0F));
                else
                    textFont.draw(batch, "Story mode completed", worldXToScreenX(90.0F), worldYToScreenY(440.0F));

            } else
                textFont.draw(batch, "To unlock complete City", worldXToScreenX(80.0F), worldYToScreenY(490.0F));

        }
        if (forestInfoShow)
            coinFont.draw(batch, "Coming Soon", worldXToScreenX(115.0F), worldYToScreenY(480.0F));
        if (arcticInfoShow)
            coinFont.draw(batch, "Coming Soon", worldXToScreenX(115.0F), worldYToScreenY(480.0F));
        batch.end();
    }

    public void update(float paramFloat) {
    }

    private void infoWindowClosed() {
        stage.getActors().get(1).setTouchable(Touchable.enabled);
        stage.getActors().get(2).setTouchable(Touchable.enabled);
        stage.getActors().get(3).setTouchable(Touchable.enabled);
        stage.getActors().get(4).setTouchable(Touchable.enabled);
        stage.getActors().get(5).setTouchable(Touchable.enabled);
        stage.getActors().get(6).setTouchable(Touchable.enabled);
        stage.getActors().get(10).setTouchable(Touchable.enabled);
        stage.getActors().get(11).setTouchable(Touchable.enabled);
        stage.getActors().get(12).setTouchable(Touchable.enabled);
        stage.getActors().get(13).setTouchable(Touchable.enabled);
        stage.getActors().get(19).setTouchable(Touchable.enabled);
    }

    private void infoWindowOpened() {
        stage.getActors().get(1).setTouchable(Touchable.disabled);
        stage.getActors().get(2).setTouchable(Touchable.disabled);
        stage.getActors().get(3).setTouchable(Touchable.disabled);
        stage.getActors().get(4).setTouchable(Touchable.disabled);
        stage.getActors().get(5).setTouchable(Touchable.disabled);
        stage.getActors().get(6).setTouchable(Touchable.disabled);
        stage.getActors().get(10).setTouchable(Touchable.disabled);
        stage.getActors().get(11).setTouchable(Touchable.disabled);
        stage.getActors().get(12).setTouchable(Touchable.disabled);
        stage.getActors().get(13).setTouchable(Touchable.disabled);
        stage.getActors().get(19).setTouchable(Touchable.disabled);
    }

    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY(float paramFloat) {
        return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
    }

}


/* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\States\ChooseStageMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */