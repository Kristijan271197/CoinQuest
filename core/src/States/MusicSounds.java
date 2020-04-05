package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MusicSounds {
    private Music backgroundMusic;
    private Sound bombSound;
    private Sound buttonBuy;
    private Sound buttonClick;
    private Sound buttonReceiveCoins;
    private Sound coinCollect;
    private Sound jump;
    private Sound playerGround;
    private Preferences prefs;
    private Sound rockHit;
    private Sound wheelReward;
    private Music wheelSpin;

    public MusicSounds(AssetManager paramAssetManager) {
        buttonClick = paramAssetManager.get("music/button_click.mp3", Sound.class);
        buttonReceiveCoins = paramAssetManager.get("music/receive_coins.mp3", Sound.class);
        buttonBuy = paramAssetManager.get("music/button_buy.mp3", Sound.class);
        coinCollect = paramAssetManager.get("music/coin_collect.mp3", Sound.class);
        rockHit = paramAssetManager.get("music/rock_hit.mp3", Sound.class);
        bombSound = paramAssetManager.get("music/bomb_explode.mp3", Sound.class);
        playerGround = paramAssetManager.get("music/player_ground.mp3", Sound.class);
        jump = paramAssetManager.get("music/jump.mp3", Sound.class);
        wheelReward = paramAssetManager.get("music/wheel_reward.mp3", Sound.class);
        wheelSpin = paramAssetManager.get("music/wheel_of_fortune.mp3", Music.class);
        backgroundMusic = paramAssetManager.get("music/fun_adventure.ogg", Music.class);
        prefs = Gdx.app.getPreferences("prefs");
    }


    public void playBackgroundMusic() {
        if (this.prefs.getBoolean("music")) {
            this.backgroundMusic.setVolume(0.2F);
            this.backgroundMusic.play();
            this.backgroundMusic.setLooping(true);
        } else {
            this.backgroundMusic.stop();
        }
    }

    public void playBombSound() {
        if (this.prefs.getBoolean("sound"))
            this.bombSound.play(1.0F);
    }

    void playButtonBuy() {
        if (this.prefs.getBoolean("sound"))
            this.buttonBuy.play(0.5F);
    }

    public void playButtonClick() {
        if (this.prefs.getBoolean("sound"))
            this.buttonClick.play(0.5F);
    }

    void playButtonReceiveCoins() {
        if (this.prefs.getBoolean("sound"))
            this.buttonReceiveCoins.play(0.5F);
    }

    public void playCoinCollect() {
        if (this.prefs.getBoolean("sound"))
            this.coinCollect.play(0.2F);
    }

    public void playJump() {
        if (this.prefs.getBoolean("sound"))
            this.jump.play(0.25F);
    }

    public void playPlayerGround() {
        if (this.prefs.getBoolean("sound"))
            this.playerGround.play(0.5F);
    }

    public void playRockHit() {
        if (this.prefs.getBoolean("sound"))
            this.rockHit.play(0.5F);
    }

    void playWheelReward() {
        if (this.prefs.getBoolean("sound"))
            this.wheelReward.play(0.15F);
    }

    void playWheelSpin() {
        if (this.prefs.getBoolean("sound")) {
            this.wheelSpin.setVolume(0.15F);
            this.wheelSpin.play();
        }
    }

    public Music getBackgroundMusic() {
        return this.backgroundMusic;
    }

    Sound getWheelReward() {
        return this.wheelReward;
    }
}
