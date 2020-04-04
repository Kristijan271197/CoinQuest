//package States;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.InputProcessor;
//import com.badlogic.gdx.Preferences;
//import com.badlogic.gdx.assets.AssetManager;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.scenes.scene2d.Actor;
//import com.badlogic.gdx.scenes.scene2d.EventListener;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Button;
//import com.badlogic.gdx.scenes.scene2d.ui.Image;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.viewport.ScreenViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//
//public class Settings extends State {
//  public static final String MUSIC = "music";
//
//  public static final String SOUND = "sound";
//
//  private ImageButton.ImageButtonStyle checkedMarkButtonStyle;
//
//  private ImageButton musicButton;
//
//  private MusicSounds musicSoundsObject;
//
//  private Preferences prefs;
//
//  private ImageButton soundButton;
//
//  private Stage stage = new Stage((Viewport)new ScreenViewport());
//
//  private ImageButton.ImageButtonStyle xButtonStyle;
//
//  Settings(final GameStateManager gsm, final AdsController adsController, final AssetManager manager) {
//    super(gsm);
//    Gdx.input.setInputProcessor((InputProcessor)this.stage);
//    this.prefs = Gdx.app.getPreferences("prefs");
//    this.musicSoundsObject = new MusicSounds(manager);
//    TextureAtlas textureAtlas1 = (TextureAtlas)manager.get("shared/shared.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas2 = (TextureAtlas)manager.get("settings/settings.atlas", TextureAtlas.class);
//    TextureAtlas textureAtlas3 = (TextureAtlas)manager.get("achievements/achievements.atlas", TextureAtlas.class);
//    Image image1 = new Image((TextureRegion)textureAtlas1.findRegion("menu_background"));
//    image1.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//    image1.setPosition(0.0F, 0.0F);
//    Image image2 = new Image((TextureRegion)textureAtlas2.findRegion("sound_music_text"));
//    image2.setSize(worldXToScreenX(300.0F), worldYToScreenY(200.0F));
//    image2.setPosition(worldXToScreenX(100.0F), worldYToScreenY(650.0F));
//    ImageButton.ImageButtonStyle imageButtonStyle2 = new ImageButton.ImageButtonStyle();
//    this.checkedMarkButtonStyle = imageButtonStyle2;
//    imageButtonStyle2.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("achievement_completed")));
//    this.checkedMarkButtonStyle.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas3.findRegion("achievement_completed")));
//    imageButtonStyle2 = new ImageButton.ImageButtonStyle();
//    this.xButtonStyle = imageButtonStyle2;
//    imageButtonStyle2.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("x_sound_music")));
//    this.xButtonStyle.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas2.findRegion("x_sound_music")));
//    ImageButton imageButton2 = new ImageButton(this.checkedMarkButtonStyle);
//    this.soundButton = imageButton2;
//    imageButton2.setPosition(worldXToScreenX(330.0F), worldYToScreenY(782.0F));
//    this.soundButton.setSize(worldXToScreenX(80.0F), worldYToScreenY(63.0F));
//    this.soundButton.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            if (Settings.this.prefs.getBoolean("sound", true)) {
//              Settings.this.prefs.putBoolean("sound", false);
//              Settings.this.prefs.flush();
//              Settings.this.musicSoundsObject.playButtonClick();
//            } else {
//              Settings.this.prefs.putBoolean("sound", true);
//              Settings.this.prefs.flush();
//              Settings.this.musicSoundsObject.playButtonClick();
//            }
//          }
//        });
//    imageButton2 = new ImageButton(this.checkedMarkButtonStyle);
//    this.musicButton = imageButton2;
//    imageButton2.setPosition(worldXToScreenX(330.0F), worldYToScreenY(665.0F));
//    this.musicButton.setSize(worldXToScreenX(80.0F), worldYToScreenY(63.0F));
//    this.musicButton.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            if (Settings.this.prefs.getBoolean("music", true)) {
//              Settings.this.prefs.putBoolean("music", false);
//              Settings.this.prefs.flush();
//              Settings.this.musicSoundsObject.getBackgroundMusic().stop();
//              Settings.this.musicSoundsObject.playButtonClick();
//            } else {
//              Settings.this.prefs.putBoolean("music", true);
//              Settings.this.prefs.flush();
//              Settings.this.musicSoundsObject.playButtonClick();
//              Settings.this.musicSoundsObject.playBackgroundMusic();
//            }
//          }
//        });
//    ImageButton.ImageButtonStyle imageButtonStyle1 = new ImageButton.ImageButtonStyle();
//    imageButtonStyle1.up = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("back_button_unpressed")));
//    imageButtonStyle1.down = (Drawable)new TextureRegionDrawable(new TextureRegion((TextureRegion)textureAtlas1.findRegion("back_button_pressed")));
//    ImageButton imageButton1 = new ImageButton(imageButtonStyle1);
//    imageButton1.setPosition(worldXToScreenX(20.0F), worldYToScreenY(20.0F));
//    imageButton1.setSize(worldXToScreenX(75.0F), worldYToScreenY(75.0F));
//    imageButton1.addListener((EventListener)new ClickListener() {
//          public void touchUp(InputEvent param1InputEvent, float param1Float1, float param1Float2, int param1Int1, int param1Int2) {
//            super.touchUp(param1InputEvent, param1Float1, param1Float2, param1Int1, param1Int2);
//            Settings.this.musicSoundsObject.playButtonClick();
//            GameStateManager gameStateManager = gsm;
//            gameStateManager.set(new MainMenu(gameStateManager, adsController, manager));
//            Settings.this.dispose();
//          }
//        });
//    this.stage.addActor((Actor)image1);
//    this.stage.addActor((Actor)image2);
//    this.stage.addActor((Actor)imageButton1);
//    this.stage.addActor((Actor)this.musicButton);
//    this.stage.addActor((Actor)this.soundButton);
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
//    this.stage.act(Gdx.graphics.getDeltaTime());
//    this.stage.draw();
//    if (this.prefs.getBoolean("sound", true)) {
//      this.soundButton.setStyle((Button.ButtonStyle)this.checkedMarkButtonStyle);
//      this.soundButton.setPosition(worldXToScreenX(330.0F), worldYToScreenY(782.0F));
//      this.soundButton.setSize(worldXToScreenX(80.0F), worldYToScreenY(63.0F));
//    } else {
//      this.soundButton.setPosition(worldXToScreenX(332.0F), worldYToScreenY(782.0F));
//      this.soundButton.setSize(worldXToScreenX(55.0F), worldYToScreenY(58.0F));
//      this.soundButton.setStyle((Button.ButtonStyle)this.xButtonStyle);
//    }
//    if (this.prefs.getBoolean("music", true)) {
//      this.musicButton.setSize(worldXToScreenX(80.0F), worldYToScreenY(63.0F));
//      this.musicButton.setPosition(worldXToScreenX(330.0F), worldYToScreenY(665.0F));
//      this.musicButton.setStyle((Button.ButtonStyle)this.checkedMarkButtonStyle);
//    } else {
//      this.musicButton.setSize(worldXToScreenX(55.0F), worldYToScreenY(58.0F));
//      this.musicButton.setPosition(worldXToScreenX(332.0F), worldYToScreenY(665.0F));
//      this.musicButton.setStyle((Button.ButtonStyle)this.xButtonStyle);
//    }
//  }
//
//  public void update(float paramFloat) {}
//}
//
//
///* Location:              C:\Users\nikol\Desktop\dex-tools-2.1-SNAPSHOT\kiki-dex2jar.jar!\States\Settings.class
// * Java compiler version: 6 (50.0)
// * JD-Core Version:       1.1.3
// */