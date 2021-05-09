package fi.tuni.MindSlicer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fi.tuni.MindSlicer.screens.level1;
import fi.tuni.MindSlicer.screens.level3;
import fi.tuni.MindSlicer.screens.level2;


public class playerActor extends Actor {

    private Texture playerTexture;
    private Texture hitTexture;
    private Texture defaultTexture;

    public int PLAYER_HEALTH = 100;
    private String healthAmount;

    private int PLAYER_ATK;
    public static int PLAYER_DEF;

    public static int MONEY = defaultValues.startingMoney;
    public int enemyAttacksAfter;
    public static boolean shield_ON = false;
    public static boolean playerActionDone;
    private int currentLevel;

    private Sound buffSound;
    private Sound hitSound;
    private Sound healSound;
    private Sound shieldSound;


    public static int statPointsBought = 0;

    public playerActor(int level) {

        playerActionDone = false;
        defaultTexture = new Texture(Gdx.files.internal("playercharacter.png"));

        hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/basicattack.mp3"));
        buffSound = Gdx.audio.newSound(Gdx.files.internal("sounds/drink.wav"));
        shieldSound = Gdx.audio.newSound(Gdx.files.internal("sounds/mixdown.wav"));
        healSound = Gdx.audio.newSound(Gdx.files.internal("sounds/eat.wav"));

        playerTexture = defaultTexture;
        healthAmount = "" + PLAYER_HEALTH;
        currentLevel = level;

        PLAYER_ATK = defaultValues.currentAttack;
        PLAYER_DEF = defaultValues.currentDefence;

        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());
        setBounds(100,40, getWidth(), getHeight());

    }

    public void resetStats(){
        PLAYER_ATK = defaultValues.currentAttack;
    }

    public void reduceHealth(int damageTaken) {
        if (shield_ON) {
            damageTaken = damageTaken / 3;
            shield_ON = false;
        }

        int totalDamage = PLAYER_DEF-damageTaken;

        if (totalDamage >= 0) {
            totalDamage = -1;
        }

        this.PLAYER_HEALTH = this.PLAYER_HEALTH + totalDamage;
        healthAmount = "" + PLAYER_HEALTH;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(playerTexture,
                this.getX(), this.getY(),
                this.getOriginX(),
                this.getOriginY(),
                this.getWidth(),
                this.getHeight(),
                this.getScaleX(),
                this.getScaleY(),
                this.getRotation(),0,0,
                playerTexture.getWidth(), playerTexture.getHeight(), false, false);

        Main.font.draw(batch, healthAmount, 145, 30);
    }

    public void hitAction() {
        hitSound.play();
        hitTexture = new Texture("thumbs_up.png");
        playerTexture = hitTexture;

        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());

        if (currentLevel == 1) {
            level1.enemy.reduceHealth(PLAYER_ATK);
        } else if (currentLevel == 2) {
            level2.enemy.reduceHealth(PLAYER_ATK);
        } else if (currentLevel == 3) {
            level3.enemy.reduceHealth(PLAYER_ATK);
        }

        playerActionDone = true;
        enemyActor.allowPlayerAttack = false;
        enemyAttacksAfter = 4;
    }

    public void superShield(){
        shieldSound.play();
        hitTexture = new Texture("main_def.png");
        playerTexture = hitTexture;

        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());

        shield_ON = true;

        playerActionDone = true;
        enemyActor.allowPlayerAttack = false;
        enemyAttacksAfter = 3;
    }

    public void heal(){
        healSound.play();
        hitTexture = new Texture("sweet_health_buff.png");
        playerTexture = hitTexture;

        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());

        PLAYER_HEALTH = PLAYER_HEALTH + 25;
        playerActionDone = true;
        enemyActor.allowPlayerAttack = false;
        enemyAttacksAfter = 3;
        healthAmount = "" + PLAYER_HEALTH;
    }

    public void resetPlayer() {
        playerTexture = defaultTexture;
        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());
    }

    public void thinkAction() {
        buffSound.play();
        hitTexture = new Texture("drink_coffee_buff1.png");
        playerTexture = hitTexture;

        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());

        double ATK_RISE = PLAYER_ATK * 1.2;
        PLAYER_ATK = (int) ATK_RISE;
        playerActionDone = true;
        enemyActor.allowPlayerAttack = false;
        enemyAttacksAfter = 3;
    }
}
