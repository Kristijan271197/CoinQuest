package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Align;

public class LoadingScreen extends State {
    private AdsController adsController;
    private TextureAtlas.AtlasRegion background;
    private BitmapFont coinAndDiamondFont;
    private TextureAtlas.AtlasRegion greenBar;
    private TextureAtlas.AtlasRegion loadingBar;
    private TextureAtlas.AtlasRegion loadingBarOutline;
    private Sound loadingBarSound;
    private TextureAtlas.AtlasRegion loadingText;
    private TextureAtlas.AtlasRegion logo;
    private AssetManager manager;
    private int oldProgress = 0;
    private Preferences prefs;
    private TextureAtlas.AtlasRegion whiteBar;

    LoadingScreen(GameStateManager paramGameStateManager, AdsController adsController) {
        super(paramGameStateManager);
        this.adsController = adsController;
        manager = new AssetManager();

        manager.load("loading_screen/loading_screen.atlas", TextureAtlas.class);
        manager.load("music/loading_bar_sound.mp3", Sound.class);
        manager.load("font/font_loading_screen.fnt", BitmapFont.class);
        prefs = Gdx.app.getPreferences("prefs");
        manager.finishLoading();
        TextureAtlas textureAtlas = manager.get("loading_screen/loading_screen.atlas", TextureAtlas.class);
        coinAndDiamondFont = manager.get("font/font_loading_screen.fnt", BitmapFont.class);
        coinAndDiamondFont.setColor(Color.BLACK);
        coinAndDiamondFont.getData().setScale(worldXToScreenX(0.5f), worldYToScreenY(0.5f));
        background = textureAtlas.findRegion("loading_screen_background");
        loadingBar = textureAtlas.findRegion("loading_bar");
        loadingBarOutline = textureAtlas.findRegion("loading_bar_outline");
        whiteBar = textureAtlas.findRegion("white_bar");
        greenBar = textureAtlas.findRegion("green_bar");
        logo = textureAtlas.findRegion("navitas_logo");
        loadingText = textureAtlas.findRegion("loading_text");
        loadingBarSound = manager.get("music/loading_bar_sound.mp3", Sound.class);
        loadAssets();
    }


    private float worldXToScreenX(float paramFloat) {
        return Gdx.graphics.getWidth() / 500.0F * paramFloat;
    }

    private float worldYToScreenY(float paramFloat) {
        return Gdx.graphics.getHeight() / 1000.0F * paramFloat;
    }

    public void dispose() {
    }

    public void handleInput() {
    }

    public void render(SpriteBatch batch) {
        if (this.manager.update()) {
            this.gsm.push(new MainMenu(this.gsm, this.adsController, this.manager));
            dispose();
        } else {
            if ((int) (this.manager.getProgress() * 100.0F) > this.oldProgress) {
                if (this.prefs.getBoolean("sound", true))
                    this.loadingBarSound.play();
                this.oldProgress = (int) (this.manager.getProgress() * 100.0F);
            }

            batch.begin();
            batch.draw(background, 0.0F, 0.0F, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.draw(logo, worldXToScreenX(50.0F), worldYToScreenY(500.0F), worldXToScreenX(400.0F), worldYToScreenY(400.0F));
            batch.draw(loadingText, worldXToScreenX(150.0F), worldYToScreenY(290.0F), worldXToScreenX(200.0F), worldYToScreenY(53.0F));
            batch.draw(whiteBar, worldXToScreenX(105.0F), worldYToScreenY(206.0F), worldXToScreenX(290.0F), worldYToScreenY(66.0F));
            batch.draw(greenBar, worldXToScreenX(105.0F), worldYToScreenY(206.0F), worldXToScreenX((int) (manager.getProgress() * 290.0F)), worldYToScreenY(66.0F));
            batch.draw(loadingBar, worldXToScreenX(100.0F), worldYToScreenY(200.0F), worldXToScreenX(300.0F), worldYToScreenY(80.0F));
            batch.draw(loadingBarOutline, worldXToScreenX(93.0F), worldYToScreenY(192.0F), worldXToScreenX(315.0F), worldYToScreenY(96.0F));
            coinAndDiamondFont.draw(batch, String.valueOf((int) (this.manager.getProgress() * 100.0F)), worldXToScreenX(235.0F), worldYToScreenY(255.0F), worldXToScreenX(30.0F), Align.center, true);
            batch.end();
        }
    }

    public void update(float paramFloat) {
    }


    private void loadAssets() {
        this.manager.load("main_menu/main_menu.atlas", TextureAtlas.class);
        this.manager.load("shop/shop.atlas", TextureAtlas.class);
        this.manager.load("choose_stage/choose_stage.atlas", TextureAtlas.class);
        this.manager.load("spinning_wheel/spinning_wheel.atlas", TextureAtlas.class);
        this.manager.load("main_game/main_game.atlas", TextureAtlas.class);
        this.manager.load("story_city_start_first.png", Texture.class);
        this.manager.load("story_city_start_second.png", Texture.class);
        this.manager.load("story_city_start_third.png", Texture.class);
        this.manager.load("story_city_start_fourth.png", Texture.class);
        this.manager.load("story_city_end_first.png", Texture.class);
        this.manager.load("story_city_end_second.png", Texture.class);
        this.manager.load("story_city_end_third.png", Texture.class);
        this.manager.load("story_city_end_fourth.png", Texture.class);
        this.manager.load("arrow_story_left.png", Texture.class);
        this.manager.load("arrow_story_right.png", Texture.class);
        this.manager.load("skip_button.png", Texture.class);
        this.manager.load("achievements/achievements.atlas", TextureAtlas.class);
        this.manager.load("end_game/end_game.atlas", TextureAtlas.class);
        this.manager.load("shared/shared.atlas", TextureAtlas.class);
        this.manager.load("font/font_scale_15.fnt", BitmapFont.class);
        this.manager.load("font/font_scale_1.fnt", BitmapFont.class);
        this.manager.load("font/font_scale_gray_1.fnt", BitmapFont.class);
        this.manager.load("font/font_scale_09.fnt", BitmapFont.class);
        this.manager.load("font/font_scale_07.fnt", BitmapFont.class);
        this.manager.load("font/font_scale_065.fnt", BitmapFont.class);
        this.manager.load("font/font_end_game.fnt", BitmapFont.class);
        this.manager.load("font/font_end_game_smaller.fnt", BitmapFont.class);
        this.manager.load("font/score_font.fnt", BitmapFont.class);
        this.manager.load("font/text_font.fnt", BitmapFont.class);
        this.manager.load("font/achievements_font.fnt", BitmapFont.class);
        this.manager.load("font/achievements_button_font.fnt", BitmapFont.class);
        this.manager.load("settings/settings.atlas", TextureAtlas.class);
        this.manager.load("music/bomb_explode.mp3", Sound.class);
        this.manager.load("music/button_click.mp3", Sound.class);
        this.manager.load("music/coin_collect.mp3", Sound.class);
        this.manager.load("music/jump.mp3", Sound.class);
        this.manager.load("music/player_fall.mp3", Sound.class);
        this.manager.load("music/player_ground.mp3", Sound.class);
        this.manager.load("music/rock_hit.mp3", Sound.class);
        this.manager.load("music/button_buy.mp3", Sound.class);
        this.manager.load("music/wheel_of_fortune.mp3", Music.class);
        this.manager.load("music/wheel_reward.mp3", Sound.class);
        this.manager.load("music/receive_coins.mp3", Sound.class);
        this.manager.load("music/fun_adventure.ogg", Music.class);
    }


}