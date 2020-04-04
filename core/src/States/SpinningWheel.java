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
//import com.badlogic.gdx.math.Interpolation;
//import com.badlogic.gdx.scenes.scene2d.Action;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.EventListener;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.Touchable;
//import com.badlogic.gdx.scenes.scene2d.actions.Actions;
//import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.Random;
//
//public class SpinningWheel extends State {
//  private static final String SPIN_BUTTON_TIMER = "spinButtonTimer";
//
//  private static final String WHEEL_MULTIPLIER = "wheelMultiplier";
//
//  private static final String WHEEL_MULTIPLIER_DAY = "wheelMultiplierDay";
//
//  private static final String WHEEL_MULTIPLIER_WEEK = "wheelMultiplierWeek";
//
//  private static final String WHEEL_SPIN_MULTIPLIER = "wheelSpinMultiplier";
//
//  private static final String WHEEL_SPUN_TIMES = "wheelSpunTimes";
//
//  private BitmapFont buttonsTextFont;
//
//  private Calendar calendarG;
//
//  private Calendar calendarGPrevious;
//
//  private TextureAtlas.AtlasRegion coin;
//
//  private BitmapFont coinDiamondFont;
//
//  private int coinGlobal;
//
//  private boolean firstSpin;
//
//  private boolean isCoins;
//
//  private MusicSounds musicSoundsObject;
//
//  private boolean onceSound = false;
//
//  private Preferences prefs;
//
//  private Random random;
//
//  private long rewardWindowTime;
//
//  private TextureAtlas.AtlasRegion ruby;
//
//  private int rubyGlobal;
//
//  private SequenceAction sequence;
//
//  private boolean showRewardWindow;
//
//  private long spinButtonTimeRemaining;
//
//  private Image spinningWheelFront;
//
//  private int spinningWheelReward;
//
//  private BitmapFont spinsWindowTextFont;
//
//  private Stage stage = new Stage((Viewport)new ScreenViewport());
//
//  private int wheelMultiplier;
//
//  private int wheelMultiplierDay;
//
//  private int wheelMultiplierWeek;
//
//  private int wheelSpinMultiplier;
//
//  SpinningWheel(GameStateManager paramGameStateManager, final AdsController adsController, final AssetManager manager) {
//    super(paramGameStateManager);
//    Gdx.input.setInputProcessor((InputProcessor)this.stage);
//    Achievements achievements = new Achievements();
//    Date date = new Date();
//    GregorianCalendar gregorianCalendar = new GregorianCalendar();
//    this.calendarG = gregorianCalendar;
//    gregorianCalendar.setTime(date);
//    gregorianCalendar = new GregorianCalendar();
//    this.calendarGPrevious = gregorianCalendar;
//    gregorianCalendar.setTime(date);
//    this.calendarGPrevious.add(5, -1);
//    this.musicSoundsObject = new MusicSounds(manager);
//    TextureAtlas textureAtlas3 = (TextureAtlas)manager.get("spinning_wheel/spinning_wheel.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas2 = (TextureAtlas)manager.get("shared/shared.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas1 = (TextureAtlas)manager.get("main_game/main_game.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas4 = (TextureAtlas)manager.get("shop/shop.atlas", TextureAtlas.class);
//    this.sequence = new SequenceAction();
//    this.random = new Random(System.currentTimeMillis());
//    Preferences preferences = Gdx.app.getPreferences("prefs");
//    this.prefs = preferences;
//    this.coinGlobal = preferences.getInteger("coins");
//    this.rubyGlobal = this.prefs.getInteger("ruby");
//    this.spinButtonTimeRemaining = this.prefs.getLong("spinButtonTimer");
//    this.wheelMultiplier = this.prefs.getInteger("wheelMultiplier", 1);
//    this.wheelMultiplierDay = this.prefs.getInteger("wheelMultiplierDay", this.calendarG.get(5));
//    this.wheelSpinMultiplier = this.prefs.getInteger("wheelSpinMultiplier", 0);
//    this.wheelMultiplierWeek = this.prefs.getInteger("wheelMultiplierWeek", 1);
//    this.firstSpin = true;
//    if (this.prefs.getInteger("wheelsSpunAtmAchievement", 0) < 7)
//      achievements.checkWheelsSpun(this.prefs.getInteger("wheelSpunTimes", 0));
//    this.coin = textureAtlas2.findRegion("coin");
//    this.ruby = textureAtlas2.findRegion("diamond");
//    BitmapFont bitmapFont = (BitmapFont)manager.get("font/font_scale_07.fnt", BitmapFont.class);
//    this.coinDiamondFont = bitmapFont;
//    bitmapFont.setColor(Color.WHITE);
//    this.coinDiamondFont.getData().setScale(worldXToScreenX(0.7F), worldYToScreenY(0.7F));
//    bitmapFont = (BitmapFont)manager.get("font/font_scale_gray_1.fnt", BitmapFont.class);
//    this.buttonsTextFont = bitmapFont;
//    bitmapFont.setColor(Color.BLACK);
//    this.buttonsTextFont.getData().setScale(worldXToScreenX(1.0F), worldYToScreenY(1.0F));
//    bitmapFont = (BitmapFont)manager.get("font/font_scale_1.fnt", BitmapFont.class);
//    this.spinsWindowTextFont = bitmapFont;
//    bitmapFont.setColor(Color.WHITE);
//    this.spinsWindowTextFont.getData().setScale(worldXToScreenX(1.0F), worldYToScreenY(1.0F));
//    Image[] arrayOfImage2 = new Image[5];
//    Image[] arrayOfImage1 = new Image[5];
//    byte b;
//    for (b = 0; b < 5; b++) {
//      arrayOfImage2[b] = new Image((TextureRegion)textureAtlas3.findRegion("multiplier_red"));
//      arrayOfImage1[b] = new Image((TextureRegion)textureAtlas3.findRegion("multiplier_green"));
//    }
//    for (b = 0; b < 5; b++) {
//      arrayOfImage2[b].setSize(worldXToScreenX(75.0F), worldYToScreenY(100.0F));
//      Image image = arrayOfImage2[b];
//      float f = b * 75.0F + 62.5F;
//      image.setPosition(worldXToScreenX(f), worldYToScreenY(255.0F));
//      arrayOfImage1[b].setSize(worldXToScreenX(75.0F), worldYToScreenY(100.0F));
//      arrayOfImage1[b].setPosition(worldXToScreenX(f), worldYToScreenY(255.0F));
//    }
//    Image image3 = new Image((TextureRegion)textureAtlas2.findRegion("menu_background"));
//    image3.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//    image3.setPosition(0.0F, 0.0F);
//    Image image4 = new Image((TextureRegion)textureAtlas3.findRegion("spinning_wheel_front"));
//    this.spinningWheelFront = image4;
//    image4.setSize(worldXToScreenX(300.0F), worldXToScreenX(300.0F));
//    this.spinningWheelFront.setPosition(worldXToScreenX(100.0F), worldYToScreenY(390.0F));
//    this.spinningWheelFront.setOrigin(1);
//    image4 = new Image((TextureRegion)textureAtlas3.findRegion("spinning_wheel_back"));
//    image4.setSize(worldXToScreenX(350.0F), worldXToScreenX(350.0F));
//    image4.setPosition(worldXToScreenX(75.0F), worldYToScreenY(365.0F));
//    Image image5 = new Image((TextureRegion)textureAtlas3.findRegion("spinning_wheel_arrow"));
//    image5.setSize(worldXToScreenX(50.0F), worldYToScreenY(90.0F));
//    image5.setPosition(worldXToScreenX(225.0F), worldYToScreenY(710.0F));
//    ImageButton.ImageButtonStyle imageButtonStyle1 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle1.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("long_button_unpressed")));
//    imageButtonStyle1.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("long_button_pressed")));
//    ImageButton imageButton4 = new ImageButton(imageButtonStyle1);
//    imageButton4.setPosition(worldXToScreenX(135.0F), worldYToScreenY(820.0F));
//    imageButton4.setSize(worldXToScreenX(230.0F), worldYToScreenY(92.0F));
//    imageButton4.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            SpinningWheel.access$002(SpinningWheel.this, System.currentTimeMillis());
//            SpinningWheel.this.spinWheel();
//            SpinningWheel.this.prefs.putLong("spinButtonTimer", System.currentTimeMillis());
//            SpinningWheel.this.prefs.flush();
//            SpinningWheel.this.musicSoundsObject.playWheelSpin();
//          }
//        });
//    ImageButton imageButton3 = new ImageButton(imageButtonStyle1);
//    imageButton3.setPosition(worldXToScreenX(135.0F), worldYToScreenY(105.0F));
//    imageButton3.setSize(worldXToScreenX(230.0F), worldYToScreenY(92.0F));
//    imageButton3.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            if (SpinningWheel.this.rubyGlobal >= 5) {
//              SpinningWheel.this.prefs.putInteger("ruby", SpinningWheel.this.prefs.getInteger("ruby") - 5);
//              SpinningWheel.this.prefs.flush();
//              SpinningWheel spinningWheel = SpinningWheel.this;
//              SpinningWheel.access$402(spinningWheel, spinningWheel.prefs.getInteger("ruby"));
//              SpinningWheel.this.spinWheel();
//              SpinningWheel.this.musicSoundsObject.playWheelSpin();
//            } else {
//              ((Actor)SpinningWheel.this.stage.getActors().get(3)).setTouchable(Touchable.disabled);
//              ((Actor)SpinningWheel.this.stage.getActors().get(4)).setTouchable(Touchable.disabled);
//              ((Actor)SpinningWheel.this.stage.getActors().get(7)).setTouchable(Touchable.disabled);
//              ((Actor)SpinningWheel.this.stage.getActors().get(8)).setVisible(true);
//              ((Actor)SpinningWheel.this.stage.getActors().get(9)).setVisible(true);
//            }
//          }
//        });
//    Image image6 = new Image((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("congratulations_window"))));
//    image6.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
//    image6.setPosition(worldXToScreenX(10.0F), worldYToScreenY(375.0F));
//    ImageButton.ImageButtonStyle imageButtonStyle2 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle2.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("congratulations_window_x_button_unpressed")));
//    imageButtonStyle2.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("congratulations_window_x_button_pressed")));
//    ImageButton imageButton5 = new ImageButton(imageButtonStyle2);
//    imageButton5.setPosition(worldXToScreenX(420.0F), worldYToScreenY(490.0F));
//    imageButton5.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
//    imageButton5.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            SpinningWheel.this.musicSoundsObject.playButtonClick();
//            SpinningWheel.this.musicSoundsObject.getWheelReward().stop();
//            SpinningWheel.this.gsm.set(new SpinningWheel(SpinningWheel.this.gsm, adsController, manager));
//            SpinningWheel.this.dispose();
//          }
//        });
//    Image image1 = new Image((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("spinning_wheel_multiplier"))));
//    image1.setSize(worldXToScreenX(375.0F), worldYToScreenY(100.0F));
//    image1.setPosition(worldXToScreenX(62.5F), worldYToScreenY(255.0F));
//    ImageButton.ImageButtonStyle imageButtonStyle3 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle3.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("back_button_unpressed")));
//    imageButtonStyle3.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("back_button_pressed")));
//    Image image2 = new Image((Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas4.findRegion("not_enough_diamonds_window"))));
//    image2.setSize(worldXToScreenX(480.0F), worldYToScreenY(180.0F));
//    image2.setPosition(worldXToScreenX(10.0F), worldYToScreenY(410.0F));
//    ImageButton.ImageButtonStyle imageButtonStyle4 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle4.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("congratulations_window_x_button_unpressed")));
//    imageButtonStyle4.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("congratulations_window_x_button_pressed")));
//    ImageButton imageButton7 = new ImageButton(imageButtonStyle4);
//    imageButton7.setPosition(worldXToScreenX(420.0F), worldYToScreenY(525.0F));
//    imageButton7.setSize(worldXToScreenX(50.0F), worldYToScreenY(50.0F));
//    imageButton7.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            ((Actor)SpinningWheel.this.stage.getActors().get(3)).setTouchable(Touchable.enabled);
//            ((Actor)SpinningWheel.this.stage.getActors().get(4)).setTouchable(Touchable.enabled);
//            ((Actor)SpinningWheel.this.stage.getActors().get(7)).setTouchable(Touchable.enabled);
//            ((Actor)SpinningWheel.this.stage.getActors().get(8)).setVisible(false);
//            ((Actor)SpinningWheel.this.stage.getActors().get(9)).setVisible(false);
//            SpinningWheel.this.musicSoundsObject.playButtonClick();
//          }
//        });
//    Image image7 = new Image((TextureRegion)textureAtlas2.findRegion("sure_quit_window"));
//    image7.setSize(worldXToScreenX(480.0F), worldYToScreenY(210.0F));
//    image7.setPosition(worldXToScreenX(10.0F), worldYToScreenY(395.0F));
//    ImageButton.ImageButtonStyle imageButtonStyle5 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle5.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("congratulations_window_x_button_unpressed")));
//    imageButtonStyle5.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("congratulations_window_x_button_pressed")));
//    ImageButton imageButton2 = new ImageButton(imageButtonStyle5);
//    imageButton2.setPosition(worldXToScreenX(116.0F), worldYToScreenY(405.0F));
//    imageButton2.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
//    imageButton2.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            ((Actor)SpinningWheel.this.stage.getActors().get(10)).setVisible(false);
//            ((Actor)SpinningWheel.this.stage.getActors().get(11)).setVisible(false);
//            ((Actor)SpinningWheel.this.stage.getActors().get(12)).setVisible(false);
//            SpinningWheel.this.musicSoundsObject.playButtonClick();
//          }
//        });
//    imageButtonStyle5 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle5.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("story_continue_button_unpressed")));
//    imageButtonStyle5.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("story_continue_button_unpressed")));
//    ImageButton imageButton1 = new ImageButton(imageButtonStyle5);
//    imageButton1.setPosition(worldXToScreenX(307.0F), worldYToScreenY(405.0F));
//    imageButton1.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
//    imageButton1.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            SpinningWheel.this.musicSoundsObject.playButtonClick();
//            Gdx.app.exit();
//          }
//        });
//    ImageButton imageButton6 = new ImageButton(imageButtonStyle3);
//    imageButton6.setPosition(worldXToScreenX(20.0F), worldYToScreenY(20.0F));
//    imageButton6.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
//    imageButton6.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            SpinningWheel.this.gsm.set(new MainMenu(SpinningWheel.this.gsm, adsController, manager));
//            SpinningWheel.this.musicSoundsObject.playButtonClick();
//            SpinningWheel.this.dispose();
//          }
//        });
//    this.stage.addActor((Actor)image3);
//    this.stage.addActor((Actor)image4);
//    this.stage.addActor((Actor)this.spinningWheelFront);
//    this.stage.addActor((Actor)imageButton4);
//    this.stage.addActor((Actor)imageButton3);
//    this.stage.addActor((Actor)image6);
//    this.stage.addActor((Actor)imageButton5);
//    this.stage.addActor((Actor)imageButton6);
//    this.stage.addActor((Actor)image2);
//    this.stage.addActor((Actor)imageButton7);
//    this.stage.addActor((Actor)image7);
//    this.stage.addActor((Actor)imageButton2);
//    this.stage.addActor((Actor)imageButton1);
//    for (b = 0; b < 5; b++)
//      this.stage.addActor((Actor)arrayOfImage2[b]);
//    for (b = 0; b < 5; b++)
//      this.stage.addActor((Actor)arrayOfImage1[b]);
//    this.stage.addActor((Actor)image1);
//    this.stage.addActor((Actor)image5);
//    ((Actor)this.stage.getActors().get(5)).setVisible(false);
//    ((Actor)this.stage.getActors().get(6)).setVisible(false);
//    ((Actor)this.stage.getActors().get(8)).setVisible(false);
//    ((Actor)this.stage.getActors().get(9)).setVisible(false);
//    ((Actor)this.stage.getActors().get(10)).setVisible(false);
//    ((Actor)this.stage.getActors().get(11)).setVisible(false);
//    ((Actor)this.stage.getActors().get(12)).setVisible(false);
//    showGreenMultipliers();
//  }
//
//  private void showGreenMultipliers() {
//    byte b3;
//    byte b1 = 0;
//    byte b2 = 0;
//    while (true) {
//      b3 = b1;
//      if (b2 < 5) {
//        ((Actor)this.stage.getActors().get(b2 + 18)).setVisible(false);
//        b2++;
//        continue;
//      }
//      break;
//    }
//    while (b3 < this.wheelMultiplier) {
//      ((Actor)this.stage.getActors().get(b3 + 18)).setVisible(true);
//      b3++;
//    }
//  }
//
//  private void spinWheel() {
//    int i = this.random.nextInt(100) + 1;
//    int j = this.random.nextInt(3) + 1;
//    this.rewardWindowTime = System.currentTimeMillis();
//    this.showRewardWindow = true;
//    this.firstSpin = false;
//    this.onceSound = true;
//    Preferences preferences = this.prefs;
//    preferences.putInteger("wheelSpunTimes", preferences.getInteger("wheelSpunTimes", 0) + 1);
//    this.prefs.putInteger("wheelMultiplierDay", this.calendarG.get(5));
//    if (this.prefs.getInteger("wheelSpinMultiplier") != 26) {
//      preferences = this.prefs;
//      preferences.putInteger("wheelSpinMultiplier", preferences.getInteger("wheelSpinMultiplier") + 1);
//    }
//    this.prefs.flush();
//    int k = this.prefs.getInteger("wheelMultiplier");
//    this.wheelMultiplier = k;
//    if (i >= 1 && i <= 5) {
//      preferences = this.prefs;
//      preferences.putInteger("ruby", k * 10 + preferences.getInteger("ruby"));
//      this.prefs.flush();
//      this.spinningWheelReward = 10;
//      this.isCoins = false;
//      this.sequence.addAction((Action)Actions.rotateBy((this.random.nextInt(36) + 2190), 7.0F, (Interpolation)Interpolation.fastSlow));
//    } else if (i >= 6 && i <= 15) {
//      preferences = this.prefs;
//      preferences.putInteger("ruby", this.wheelMultiplier * 5 + preferences.getInteger("ruby"));
//      this.prefs.flush();
//      this.spinningWheelReward = 5;
//      this.isCoins = false;
//      this.sequence.addAction((Action)Actions.rotateBy((this.random.nextInt(36) + 2280), 7.0F, (Interpolation)Interpolation.fastSlow));
//    } else if (i >= 16 && i <= 35) {
//      preferences = this.prefs;
//      preferences.putInteger("coins", this.wheelMultiplier * 200 + preferences.getInteger("coins"));
//      this.prefs.flush();
//      this.spinningWheelReward = 200;
//      this.isCoins = true;
//      if (j == 1) {
//        this.sequence.addAction((Action)Actions.rotateBy((this.random.nextInt(36) + 2325), 7.0F, (Interpolation)Interpolation.fastSlow));
//      } else {
//        this.sequence.addAction((Action)Actions.rotateBy((this.random.nextInt(36) + 2415), 7.0F, (Interpolation)Interpolation.fastSlow));
//      }
//    } else if (i >= 36 && i <= 65) {
//      preferences = this.prefs;
//      preferences.putInteger("coins", this.wheelMultiplier * 100 + preferences.getInteger("coins"));
//      this.prefs.flush();
//      this.spinningWheelReward = 100;
//      this.isCoins = true;
//      if (j == 1) {
//        this.sequence.addAction((Action)Actions.rotateBy((this.random.nextInt(36) + 2370), 7.0F, (Interpolation)Interpolation.fastSlow));
//      } else {
//        this.sequence.addAction((Action)Actions.rotateBy((this.random.nextInt(36) + 2460), 7.0F, (Interpolation)Interpolation.fastSlow));
//      }
//    } else if (i >= 66 && i <= 100) {
//      preferences = this.prefs;
//      preferences.putInteger("coins", this.wheelMultiplier * 50 + preferences.getInteger("coins"));
//      this.prefs.flush();
//      this.spinningWheelReward = 50;
//      this.isCoins = true;
//      if (j == 1) {
//        this.sequence.addAction((Action)Actions.rotateBy((this.random.nextInt(36) + 2145), 7.0F, (Interpolation)Interpolation.fastSlow));
//      } else {
//        this.sequence.addAction((Action)Actions.rotateBy((this.random.nextInt(36) + 2235), 7.0F, (Interpolation)Interpolation.fastSlow));
//      }
//    }
//    this.spinningWheelFront.addAction((Action)this.sequence);
//    ((Actor)this.stage.getActors().get(3)).setTouchable(Touchable.disabled);
//    ((Actor)this.stage.getActors().get(4)).setTouchable(Touchable.disabled);
//    ((Actor)this.stage.getActors().get(7)).setTouchable(Touchable.disabled);
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
//    byte b;
//    this.stage.act(Gdx.graphics.getDeltaTime());
//    this.stage.draw();
//    Gdx.input.setCatchKey(4, true);
//    if (Gdx.input.isKeyPressed(4)) {
//      ((Actor)this.stage.getActors().get(10)).setVisible(true);
//      ((Actor)this.stage.getActors().get(11)).setVisible(true);
//      ((Actor)this.stage.getActors().get(12)).setVisible(true);
//    }
//    if (this.calendarG.get(3) != this.wheelMultiplierWeek) {
//      this.prefs.putInteger("wheelMultiplier", 1);
//      this.prefs.putInteger("wheelSpinMultiplier", 0);
//      this.prefs.putInteger("wheelMultiplierWeek", this.calendarG.get(3));
//      this.prefs.flush();
//      this.wheelMultiplier = this.prefs.getInteger("wheelMultiplier");
//      this.wheelSpinMultiplier = this.prefs.getInteger("wheelSpinMultiplier");
//      this.wheelMultiplierWeek = this.prefs.getInteger("wheelMultiplierWeek");
//      showGreenMultipliers();
//    } else if (this.calendarG.get(5) != this.wheelMultiplierDay && this.calendarGPrevious.get(5) != this.wheelMultiplierDay && this.firstSpin) {
//      this.prefs.putInteger("wheelMultiplier", 1);
//      this.prefs.putInteger("wheelSpinMultiplier", 0);
//      this.prefs.flush();
//      this.wheelMultiplier = this.prefs.getInteger("wheelMultiplier");
//      this.wheelSpinMultiplier = this.prefs.getInteger("wheelSpinMultiplier");
//      showGreenMultipliers();
//    }
//    int i = this.wheelSpinMultiplier;
//    if (i < 5) {
//      this.prefs.putInteger("wheelMultiplier", 1);
//      i = this.wheelSpinMultiplier;
//      b = 5;
//    } else if (i < 11) {
//      this.prefs.putInteger("wheelMultiplier", 2);
//      i = this.wheelSpinMultiplier - 5;
//      b = 6;
//    } else if (i < 18) {
//      this.prefs.putInteger("wheelMultiplier", 3);
//      i = this.wheelSpinMultiplier - 11;
//      b = 7;
//    } else {
//      if (i < 26) {
//        this.prefs.putInteger("wheelMultiplier", 4);
//        i = this.wheelSpinMultiplier - 18;
//      } else {
//        this.prefs.putInteger("wheelMultiplier", 5);
//        i = 8;
//      }
//      b = 8;
//    }
//    this.prefs.flush();
//    this.wheelMultiplier = this.prefs.getInteger("wheelMultiplier");
//    showGreenMultipliers();
//    long l1 = System.currentTimeMillis();
//    long l2 = this.spinButtonTimeRemaining;
//    paramSpriteBatch.begin();
//    paramSpriteBatch.draw((TextureRegion)this.coin, worldXToScreenX(10.0F), worldYToScreenY(960.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
//    this.coinDiamondFont.draw((Batch)paramSpriteBatch, String.valueOf(this.coinGlobal), worldXToScreenX(40.0F), worldYToScreenY(980.0F));
//    paramSpriteBatch.draw((TextureRegion)this.ruby, worldXToScreenX(10.0F), worldYToScreenY(930.0F), worldXToScreenX(25.0F), worldYToScreenY(25.0F));
//    this.coinDiamondFont.draw((Batch)paramSpriteBatch, String.valueOf(this.rubyGlobal), worldXToScreenX(40.0F), worldYToScreenY(950.0F));
//    if (System.currentTimeMillis() - this.spinButtonTimeRemaining >= 43200000L) {
//      ((Actor)this.stage.getActors().get(3)).setTouchable(Touchable.enabled);
//      this.buttonsTextFont.draw((Batch)paramSpriteBatch, "Free Spin", worldXToScreenX(185.0F), worldYToScreenY(880.0F));
//    } else {
//      ((Actor)this.stage.getActors().get(3)).setTouchable(Touchable.disabled);
//      this.buttonsTextFont.draw((Batch)paramSpriteBatch, "Free Spin in:", worldXToScreenX(162.5F), worldYToScreenY(895.0F));
//      BitmapFont bitmapFont = this.buttonsTextFont;
//      StringBuilder stringBuilder = new StringBuilder();
//      l1 = (43200000L - l1 - l2) / 1000L / 60L;
//      stringBuilder.append(l1 / 60L);
//      stringBuilder.append("h:");
//      stringBuilder.append(l1 % 60L);
//      stringBuilder.append("m");
//      bitmapFont.draw((Batch)paramSpriteBatch, stringBuilder.toString(), worldXToScreenX(235.0F), worldYToScreenY(865.0F), worldXToScreenX(30.0F), 1, false);
//    }
//    if (this.showRewardWindow && System.currentTimeMillis() - this.rewardWindowTime >= 7500L) {
//      if (this.onceSound) {
//        this.musicSoundsObject.playWheelReward();
//        this.onceSound = false;
//      }
//      if (this.isCoins) {
//        BitmapFont bitmapFont = this.spinsWindowTextFont;
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("You received ");
//        stringBuilder.append(this.spinningWheelReward * this.wheelMultiplier);
//        stringBuilder.append(" coins");
//        bitmapFont.draw((Batch)paramSpriteBatch, stringBuilder.toString(), worldXToScreenX(90.0F), worldYToScreenY(465.0F));
//      } else {
//        BitmapFont bitmapFont = this.spinsWindowTextFont;
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("You received ");
//        stringBuilder.append(this.spinningWheelReward * this.wheelMultiplier);
//        stringBuilder.append(" diamonds");
//        bitmapFont.draw((Batch)paramSpriteBatch, stringBuilder.toString(), worldXToScreenX(90.0F), worldYToScreenY(465.0F));
//      }
//      ((Actor)this.stage.getActors().get(5)).setVisible(true);
//      ((Actor)this.stage.getActors().get(6)).setVisible(true);
//    }
//    if (this.wheelSpinMultiplier < 26) {
//      BitmapFont bitmapFont = this.spinsWindowTextFont;
//      StringBuilder stringBuilder = new StringBuilder();
//      stringBuilder.append("Spins until next multiplier ");
//      stringBuilder.append(i);
//      stringBuilder.append("/");
//      stringBuilder.append(b);
//      bitmapFont.draw((Batch)paramSpriteBatch, stringBuilder.toString(), worldXToScreenX(50.0F), worldYToScreenY(240.0F));
//    }
//    paramSpriteBatch.draw((TextureRegion)this.ruby, worldXToScreenX(155.0F), worldYToScreenY(140.0F), worldXToScreenX(30.0F), worldYToScreenY(30.0F));
//    this.buttonsTextFont.draw((Batch)paramSpriteBatch, "5   Spin", worldXToScreenX(185.0F), worldYToScreenY(165.0F));
//    paramSpriteBatch.end();
//  }
//
//  public void update(float paramFloat) {}
//}
//
//
///* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\States\SpinningWheel.class
// * Java compiler version: 6 (50.0)
// * JD-Core Version:       1.1.3
// */