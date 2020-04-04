//package States;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.Preferences;
//import com.badlogic.gdx.assets.AssetManager;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g2d.Batch;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.EventListener;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.Touchable;
//import com.badlogic.gdx.scenes.scene2d.ui.Button;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//import com.zappycode.coinman.game.MainGame;
//
//public class EndGame extends State {
//  public static final String ADS_WATCHED = "adsWatched";
//
//  static final String HIGH_SCORE_CITY = "highScoreCity";
//
//  static final String HIGH_SCORE_DESERT = "highScoreDesert";
//
//  private static final String LIFETIME_COINS_COLLECTED = "lifetimeCoinsCollected";
//
//  private static final String LIFETIME_METRES = "lifetimeMetres";
//
//  public static final String POWER_UPS_COLLECTED = "powerUpsCollected";
//
//  private static final String STAGES_COMPLETED = "stagesCompleted";
//
//  private static final String STAGES_PLAYED = "stagesPlayed";
//
//  public static final String STORY_MODE_CITY = "storyModeCity";
//
//  public static final String STORY_MODE_DESERT = "storyModeDesert";
//
//  private int coinCount;
//
//  private BitmapFont coinsHighScoreFont;
//
//  private AdsController mAdsController;
//
//  private MusicSounds musicSoundsObject;
//
//  private Preferences prefs;
//
//  private int score;
//
//  private BitmapFont scoreFont;
//
//  private boolean showTripleCoinsText = false;
//
//  private Stage stage = new Stage((Viewport)new ScreenViewport());
//
//  private int stageNumber;
//
//  private float story;
//
//  private ImageButton tripleCoinsAdButton;
//
//  private ImageButton.ImageButtonStyle tripleCoinsAdButtonTexture;
//
//  private ImageButton.ImageButtonStyle tripleCoinsAdWaitButtonTexture;
//
//  private BitmapFont tripleCoinsFont;
//
//  public EndGame(GameStateManager paramGameStateManager, final AdsController adsController, final AssetManager manager, int paramInt1, int paramInt2, float paramFloat, final int stageNumber) {
//    super(paramGameStateManager);
//    Gdx.input.setInputProcessor((InputProcessor)this.stage);
//    this.mAdsController = adsController;
//    this.prefs = Gdx.app.getPreferences("prefs");
//    this.stageNumber = stageNumber;
//    TextureAtlas textureAtlas2 = (TextureAtlas)manager.get("main_menu/main_menu.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas3 = (TextureAtlas)manager.get("shared/shared.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas1 = (TextureAtlas)manager.get("main_game/main_game.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas4 = (TextureAtlas)manager.get("end_game/end_game.atlas", TextureAtlas.class);
//    this.story = paramFloat;
//    Achievements achievements = new Achievements();
//    this.musicSoundsObject = new MusicSounds(manager);
//    Preferences preferences = this.prefs;
//    preferences.putInteger("lifetimeCoinsCollected", preferences.getInteger("lifetimeCoinsCollected", 0) + paramInt1);
//    preferences = this.prefs;
//    preferences.putInteger("stagesPlayed", preferences.getInteger("stagesPlayed", 0) + 1);
//    preferences = this.prefs;
//    preferences.putFloat("lifetimeMetres", preferences.getFloat("lifetimeMetres", 0.0F) + paramFloat);
//    this.prefs.flush();
//    if (stageNumber == 1) {
//      if (this.prefs.getInteger("highScoreCity", 0) < paramInt2) {
//        this.prefs.putInteger("highScoreCity", paramInt2);
//        this.prefs.flush();
//      }
//      if (this.prefs.getFloat("storyModeCity", 0.0F) < paramFloat) {
//        this.prefs.putFloat("storyModeCity", paramFloat);
//        this.prefs.flush();
//      }
//    } else if (stageNumber == 2) {
//      if (this.prefs.getInteger("highScoreDesert", 0) < paramInt2) {
//        this.prefs.putInteger("highScoreDesert", paramInt2);
//        this.prefs.flush();
//      }
//      if (this.prefs.getFloat("storyModeDesert", 0.0F) < paramFloat) {
//        this.prefs.putFloat("storyModeDesert", paramFloat);
//        this.prefs.flush();
//      }
//    }
//    if (stageNumber == 1) {
//      if (this.prefs.getInteger("cityAtmAchievement", 0) < 7)
//        achievements.checkCityAchievement(paramFloat);
//    } else if (stageNumber == 2 && this.prefs.getInteger("desertAtmAchievement", 0) < 7) {
//      achievements.checkDesertAchievement(paramFloat);
//    }
//    if (this.prefs.getInteger("coinsOneGameAtmAchievement", 0) < 6)
//      achievements.checkCoinsOneGameAtmAchievement(paramInt1);
//    if (this.prefs.getInteger("lifetimeCoinsCollectedAtmAchievement", 0) < 8)
//      achievements.checkLifetimeCoinsCollected(this.prefs.getInteger("lifetimeCoinsCollected", 0));
//    if (this.prefs.getInteger("stageCompletedAtmAchievement", 0) < 1 && this.prefs.getFloat("storyModeCity", 0.0F) > 100.0F) {
//      preferences = this.prefs;
//      preferences.putInteger("stagesCompleted", preferences.getInteger("stagesCompleted", 0) + 1);
//    }
//    if (this.prefs.getInteger("stageCompletedAtmAchievement", 0) < 2 && this.prefs.getFloat("storyModeDesert", 0.0F) > 100.0F) {
//      preferences = this.prefs;
//      preferences.putInteger("stagesCompleted", preferences.getInteger("stagesCompleted", 0) + 1);
//    }
//    this.prefs.flush();
//    if (this.prefs.getInteger("stageCompletedAtmAchievement", 0) < 2)
//      achievements.checkStageCompleted(this.prefs.getInteger("stagesCompleted", 0));
//    if (this.prefs.getInteger("revivedAfterDeathAtmAchievement", 0) < 6)
//      achievements.checkReviveAfterDeath(this.prefs.getInteger("revivedAfterDeath", 0));
//    if (this.prefs.getInteger("stagesPlayedAtmAchievement", 0) < 8)
//      achievements.checkStagesPlayed(this.prefs.getInteger("stagesPlayed", 0));
//    if (this.prefs.getInteger("powerUpsCollectedAtmAchievement", 0) < 7)
//      achievements.checkPowerUpsCollected(this.prefs.getInteger("powerUpsCollected", 0));
//    if (this.prefs.getInteger("adsWatchedAtmAchievement", 0) < 5)
//      achievements.checkAdsWatched(this.prefs.getInteger("adsWatched", 0));
//    if (this.prefs.getInteger("lifetimeMetresAtmAchievement", 0) < 6)
//      achievements.checkLifetimeMetres(this.prefs.getFloat("lifetimeMetres", 0.0F));
//    this.coinCount = paramInt1;
//    this.score = paramInt2;
//    BitmapFont bitmapFont = (BitmapFont)manager.get("font/font_end_game.fnt", BitmapFont.class);
//    this.scoreFont = bitmapFont;
//    bitmapFont.setColor(Color.BLACK);
//    this.scoreFont.getData().setScale(worldXToScreenX(1.3F), worldYToScreenY(1.3F));
//    this.scoreFont.getRegion().getRegionWidth();
//    bitmapFont = (BitmapFont)manager.get("font/font_end_game_smaller.fnt", BitmapFont.class);
//    this.coinsHighScoreFont = bitmapFont;
//    bitmapFont.setColor(Color.BLACK);
//    this.coinsHighScoreFont.getData().setScale(worldXToScreenX(1.2F), worldYToScreenY(1.2F));
//    bitmapFont = (BitmapFont)manager.get("font/font_scale_1.fnt", BitmapFont.class);
//    this.tripleCoinsFont = bitmapFont;
//    bitmapFont.setColor(Color.WHITE);
//    this.tripleCoinsFont.getData().setScale(worldXToScreenX(1.0F), worldYToScreenY(1.0F));
//    Image image4 = new Image((TextureRegion)textureAtlas3.findRegion("menu_background"));
//    image4.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//    image4.setPosition(0.0F, 0.0F);
//    Image image6 = new Image((TextureRegion)textureAtlas4.findRegion("score_table"));
//    image6.setSize(worldXToScreenX(290.0F), worldYToScreenY(350.0F));
//    image6.setPosition(worldXToScreenX(200.0F), worldYToScreenY(550.0F));
//    Image image5 = new Image((TextureRegion)textureAtlas4.findRegion("player_image"));
//    image5.setSize(worldXToScreenX(300.0F), worldYToScreenY(200.0F));
//    image5.setPosition(worldXToScreenX(10.0F), worldYToScreenY(625.0F));
//    if (this.prefs.getInteger("costumeSelectedGame") == 0) {
//      image5.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas4.findRegion("player_image"))));
//      image5.setSize(worldXToScreenX(117.0F), worldYToScreenY(200.0F));
//    } else if (this.prefs.getInteger("costumeSelectedGame") == 1) {
//      image5.setSize(worldXToScreenX(119.0F), worldYToScreenY(200.0F));
//      image5.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas4.findRegion("robot_image"))));
//    } else if (this.prefs.getInteger("costumeSelectedGame") == 2) {
//      image5.setSize(worldXToScreenX(140.0F), worldYToScreenY(200.0F));
//      image5.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas4.findRegion("knight_image"))));
//    } else if (this.prefs.getInteger("costumeSelectedGame") == 3) {
//      image5.setSize(worldXToScreenX(131.0F), worldYToScreenY(200.0F));
//      image5.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas4.findRegion("cowboy_image"))));
//    } else if (this.prefs.getInteger("costumeSelectedGame") == 4) {
//      image5.setSize(worldXToScreenX(124.0F), worldYToScreenY(200.0F));
//      image5.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas4.findRegion("cowgirl_image"))));
//    } else if (this.prefs.getInteger("costumeSelectedGame") == 5) {
//      image5.setSize(worldXToScreenX(109.0F), worldYToScreenY(200.0F));
//      image5.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas4.findRegion("ninja_male_image"))));
//    } else if (this.prefs.getInteger("costumeSelectedGame") == 6) {
//      image5.setSize(worldXToScreenX(120.0F), worldYToScreenY(200.0F));
//      image5.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas4.findRegion("ninja_female_image"))));
//    } else if (this.prefs.getInteger("costumeSelectedGame") == 7) {
//      image5.setSize(worldXToScreenX(188.0F), worldYToScreenY(200.0F));
//      image5.setDrawable((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas4.findRegion("dino_image"))));
//    }
//    ImageButton.ImageButtonStyle imageButtonStyle4 = new ImageButton.ImageButtonStyle();
//    this.tripleCoinsAdButtonTexture = imageButtonStyle4;
//    imageButtonStyle4.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas4.findRegion("triple_coins_ad_button_unpressed")));
//    this.tripleCoinsAdButtonTexture.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas4.findRegion("triple_coins_ad_button_pressed")));
//    imageButtonStyle4 = new ImageButton.ImageButtonStyle();
//    this.tripleCoinsAdWaitButtonTexture = imageButtonStyle4;
//    imageButtonStyle4.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("ad_wait_button")));
//    this.tripleCoinsAdWaitButtonTexture.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("ad_wait_button")));
//    ImageButton imageButton4 = new ImageButton(this.tripleCoinsAdButtonTexture);
//    this.tripleCoinsAdButton = imageButton4;
//    imageButton4.setPosition(worldXToScreenX(100.0F), worldYToScreenY(280.0F));
//    this.tripleCoinsAdButton.setSize(worldXToScreenX(300.0F), worldYToScreenY(118.0F));
//    this.tripleCoinsAdButton.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            adsController.showRewardedVideo();
//            EndGame.this.musicSoundsObject.playButtonClick();
//          }
//        });
//    Image image3 = new Image((TextureRegion)textureAtlas4.findRegion("triple_coins_arrow"));
//    image3.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
//    image3.setPosition(worldXToScreenX(240.0F), worldYToScreenY(245.0F));
//    ImageButton.ImageButtonStyle imageButtonStyle3 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle3.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("main_game_restart_button_unpressed")));
//    imageButtonStyle3.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("main_game_restart_button_pressed")));
//    imageButton4 = new ImageButton(imageButtonStyle3);
//    imageButton4.setPosition(worldXToScreenX(175.0F), worldYToScreenY(40.0F));
//    imageButton4.setSize(worldXToScreenX(150.0F), worldYToScreenY(150.0F));
//    imageButton4.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            EndGame.this.gsm.set((State)new MainGame(EndGame.this.gsm, adsController, manager, stageNumber));
//            EndGame.this.musicSoundsObject.playButtonClick();
//            EndGame.this.dispose();
//          }
//        });
//    Image image2 = new Image((TextureRegion)textureAtlas3.findRegion("congratulations_window"));
//    image2.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
//    image2.setPosition(worldXToScreenX(10.0F), worldYToScreenY(420.0F));
//    ImageButton.ImageButtonStyle imageButtonStyle5 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle5.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("congratulations_window_x_button_unpressed")));
//    imageButtonStyle5.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("congratulations_window_x_button_pressed")));
//    ImageButton imageButton5 = new ImageButton(imageButtonStyle5);
//    imageButton5.setPosition(worldXToScreenX(420.0F), worldYToScreenY(535.0F));
//    imageButton5.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
//    imageButton5.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            ((Actor)EndGame.this.stage.getActors().get(4)).setVisible(false);
//            ((Actor)EndGame.this.stage.getActors().get(5)).setVisible(false);
//            EndGame.access$202(EndGame.this, false);
//            EndGame.this.musicSoundsObject.playButtonClick();
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle6 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle6.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("main_game_menu_button_unpressed")));
//    imageButtonStyle6.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("main_game_menu_button_pressed")));
//    ImageButton imageButton6 = new ImageButton(imageButtonStyle6);
//    imageButton6.setPosition(worldXToScreenX(25.0F), worldYToScreenY(20.0F));
//    imageButton6.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
//    imageButton6.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            EndGame.this.gsm.set(new ChooseStageMenu(EndGame.this.gsm, adsController, manager));
//            EndGame.this.musicSoundsObject.playButtonClick();
//            EndGame.this.musicSoundsObject.playBackgroundMusic();
//            EndGame.this.dispose();
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle7 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle7.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("shop_button_unpressed")));
//    imageButtonStyle7.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("shop_button_pressed")));
//    ImageButton imageButton3 = new ImageButton(imageButtonStyle7);
//    imageButton3.setPosition(worldXToScreenX(375.0F), worldYToScreenY(20.0F));
//    imageButton3.setSize(worldXToScreenX(100.0F), worldYToScreenY(100.0F));
//    imageButton3.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            EndGame.this.gsm.set(new Shop(EndGame.this.gsm, adsController, manager));
//            EndGame.this.musicSoundsObject.playButtonClick();
//            EndGame.this.musicSoundsObject.getBackgroundMusic().play();
//            EndGame.this.dispose();
//          }
//        });
//    Image image1 = new Image((TextureRegion)textureAtlas3.findRegion("sure_quit_window"));
//    image1.setSize(worldXToScreenX(480.0F), worldYToScreenY(210.0F));
//    image1.setPosition(worldXToScreenX(10.0F), worldYToScreenY(395.0F));
//    ImageButton.ImageButtonStyle imageButtonStyle1 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle1.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("congratulations_window_x_button_unpressed")));
//    imageButtonStyle1.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("congratulations_window_x_button_pressed")));
//    ImageButton imageButton2 = new ImageButton(imageButtonStyle1);
//    imageButton2.setPosition(worldXToScreenX(116.0F), worldYToScreenY(405.0F));
//    imageButton2.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
//    imageButton2.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            ((Actor)EndGame.this.stage.getActors().get(9)).setVisible(false);
//            ((Actor)EndGame.this.stage.getActors().get(10)).setVisible(false);
//            ((Actor)EndGame.this.stage.getActors().get(11)).setVisible(false);
//            EndGame.this.musicSoundsObject.playButtonClick();
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle2 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle2.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("story_continue_button_unpressed")));
//    imageButtonStyle2.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("story_continue_button_pressed")));
//    ImageButton imageButton1 = new ImageButton(imageButtonStyle2);
//    imageButton1.setPosition(worldXToScreenX(307.0F), worldYToScreenY(405.0F));
//    imageButton1.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
//    imageButton1.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            EndGame.this.musicSoundsObject.playButtonClick();
//            Gdx.app.exit();
//          }
//        });
//    this.stage.addActor((Actor)image4);
//    this.stage.addActor((Actor)image6);
//    this.stage.addActor((Actor)image5);
//    this.stage.addActor((Actor)this.tripleCoinsAdButton);
//    this.stage.addActor((Actor)image2);
//    this.stage.addActor((Actor)imageButton5);
//    this.stage.addActor((Actor)imageButton4);
//    this.stage.addActor((Actor)imageButton6);
//    this.stage.addActor((Actor)imageButton3);
//    this.stage.addActor((Actor)image1);
//    this.stage.addActor((Actor)imageButton2);
//    this.stage.addActor((Actor)imageButton1);
//    ((Actor)this.stage.getActors().get(4)).setVisible(false);
//    ((Actor)this.stage.getActors().get(5)).setVisible(false);
//    ((Actor)this.stage.getActors().get(9)).setVisible(false);
//    ((Actor)this.stage.getActors().get(10)).setVisible(false);
//    ((Actor)this.stage.getActors().get(11)).setVisible(false);
//  }
//
//  private float worldXToScreenX(float paramFloat) {
//    return Gdx.graphics.getWidth() / 500.0F * paramFloat;
//  }
//
//  private float worldYToScreenY(float paramFloat) {
//    return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
//  }
//
//  public void dispose() {
//    this.stage.dispose();
//  }
//
//  public void handleInput() {}
//
//  public void render(SpriteBatch paramSpriteBatch) {
//    Gdx.input.setCatchKey(4, true);
//    if (Gdx.input.isKeyPressed(4)) {
//      ((Actor)this.stage.getActors().get(9)).setVisible(true);
//      ((Actor)this.stage.getActors().get(10)).setVisible(true);
//      ((Actor)this.stage.getActors().get(11)).setVisible(true);
//    }
//    boolean bool1 = this.mAdsController.getAdLoaded();
//    boolean bool2 = this.mAdsController.getRewardReceived();
//    if (bool1) {
//      this.tripleCoinsAdButton.setStyle((Button.ButtonStyle)this.tripleCoinsAdButtonTexture);
//      ((Actor)this.stage.getActors().get(3)).setTouchable(Touchable.enabled);
//    } else {
//      this.tripleCoinsAdButton.setStyle((Button.ButtonStyle)this.tripleCoinsAdWaitButtonTexture);
//      ((Actor)this.stage.getActors().get(3)).setTouchable(Touchable.disabled);
//    }
//    if (bool2) {
//      ((Actor)this.stage.getActors().get(3)).setVisible(false);
//      ((Actor)this.stage.getActors().get(4)).setVisible(true);
//      ((Actor)this.stage.getActors().get(5)).setVisible(true);
//      this.showTripleCoinsText = true;
//      Preferences preferences = this.prefs;
//      preferences.putInteger("coins", this.coinCount + preferences.getInteger("coins"));
//      preferences = this.prefs;
//      preferences.putInteger("adsWatched", preferences.getInteger("adsWatched", 0) + 1);
//      this.prefs.flush();
//      this.mAdsController.setRewardReceived(false);
//    }
//    this.stage.act(Gdx.graphics.getDeltaTime());
//    this.stage.draw();
//    paramSpriteBatch.begin();
//    this.scoreFont.draw((Batch)paramSpriteBatch, String.valueOf(this.score), worldXToScreenX(330.0F), worldYToScreenY(815.0F), worldXToScreenX(30.0F), 1, true);
//    this.coinsHighScoreFont.draw((Batch)paramSpriteBatch, String.valueOf(this.coinCount), worldXToScreenX(330.0F), worldYToScreenY(735.0F), worldXToScreenX(30.0F), 1, true);
//    int i = this.stageNumber;
//    if (i == 1) {
//      if (this.prefs.getFloat("storyModeCity", 0.0F) < 100.0F) {
//        BitmapFont bitmapFont = this.coinsHighScoreFont;
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append((int)this.story);
//        stringBuilder.append("%");
//        bitmapFont.draw((Batch)paramSpriteBatch, stringBuilder.toString(), worldXToScreenX(330.0F), worldYToScreenY(665.0F), worldXToScreenX(30.0F), 1, true);
//      } else {
//        this.coinsHighScoreFont.draw((Batch)paramSpriteBatch, "100%", worldXToScreenX(330.0F), worldYToScreenY(665.0F), worldXToScreenX(30.0F), 1, true);
//      }
//    } else if (i == 2) {
//      if (this.prefs.getFloat("storyModeDesert", 0.0F) < 100.0F) {
//        BitmapFont bitmapFont = this.coinsHighScoreFont;
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append((int)this.story);
//        stringBuilder.append("%");
//        bitmapFont.draw((Batch)paramSpriteBatch, stringBuilder.toString(), worldXToScreenX(330.0F), worldYToScreenY(665.0F), worldXToScreenX(30.0F), 1, true);
//      } else {
//        this.coinsHighScoreFont.draw((Batch)paramSpriteBatch, "100%", worldXToScreenX(330.0F), worldYToScreenY(665.0F), worldXToScreenX(30.0F), 1, true);
//      }
//    }
//    if (this.showTripleCoinsText) {
//      BitmapFont bitmapFont = this.tripleCoinsFont;
//      StringBuilder stringBuilder = new StringBuilder();
//      stringBuilder.append("You received ");
//      stringBuilder.append(this.coinCount * 2);
//      stringBuilder.append(" coins");
//      bitmapFont.draw((Batch)paramSpriteBatch, stringBuilder.toString(), worldXToScreenX(100.0F), worldYToScreenY(510.0F), worldXToScreenX(300.0F), 1, false);
//    }
//    paramSpriteBatch.end();
//  }
//
//  public void update(float paramFloat) {}
//}
//
//
///* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\States\EndGame.class
// * Java compiler version: 6 (50.0)
// * JD-Core Version:       1.1.3
// */