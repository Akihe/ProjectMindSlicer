package fi.tuni.MindSlicer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fi.tuni.MindSlicer.screens.level1;
import fi.tuni.MindSlicer.screens.level3;
import fi.tuni.MindSlicer.screens.level2;

/**
 * Player actor class
 * <p>This class is for constructing the player with it's necessary variables, values and textures, as well as managing the methods of the player.
 * PLAYER_HEALTH is the players base hp value. MONEY is the players currency for buying statPoints. enemyAttackAfter indicates how long the opponent has to wait after a player action.</p>
 */

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

    /**
     * players construction
     * @param level Is necessary, so the code knows, which level the player is in when constructing. statsPointsBought keeps track of the players upgrades.
     *              <p> Here the player is constructed to a stage with it's proper parameters and textures</p>
     */
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

    /**
     * a method for clearing buffs
     * <p>This method clears any buffs on the player after stage</p>
     */
    public void resetStats(){
        PLAYER_ATK = defaultValues.currentAttack;
    }

    /**
     * the method for the players incoming damage calculation
     * @param damageTaken is a value that comes from the opponent of the stage in question
     * <p>This method is used to reduce the players health when taking damage, and takes into account if the player has a shield active</p>
     */
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

    /**
     * players draw method
     * @param batch
     * @param alpha
     * <p> in the draw method for the player, the players health points are also consistently drawn on the screen</p>
     */
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

        main.font.draw(batch, healthAmount, 145, 30);
    }

    /**
     * the players offensive skill
     * <p>a method that reduces the correct stage's opponents health according to the player attack and the enemy's defense stats
     * The players texture is updated to a new one for the duration of the action and after the action, playerActionDone=true lets the opponents attack</p>
     */
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

    /**
     * players defensive skill
     * <p>method that actives shield_On=true for player, that affects damage calculation in damageTaken() method
     * The players texture is updated to a new one for the duration of the action and after the action, playerActionDone=true lets the opponents attack</p>
     */
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

    /**
     * Players healing skill.
     *
     * <p>increases the players hp for a set amount
     * The players texture is updated to a new one for the duration of the action and after the action, playerActionDone=true lets the opponents attack</p>
     */
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

    /**
     * changes players texture to normal
     * <p>changes the player texture after an action </p>
     */
    public void resetPlayer() {
        playerTexture = defaultTexture;
        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());
    }

    /**
     * the players buff action
     * <p>the players action for temporarily increasing the attack value. resetStats() clears these buffs after the stage
     * The players texture is updated to a new one for the duration of the action and after the action, playerActionDone=true lets the opponents attack</p>
     */
    public void thinkAction() {
        buffSound.play();
        hitTexture = new Texture("drink_coffee_buff1.png");
        playerTexture = hitTexture;

        setWidth(playerTexture.getWidth());
        setHeight(playerTexture.getHeight());

        double ATK_RISE = PLAYER_ATK * 1.3;
        PLAYER_ATK = (int) ATK_RISE;
        playerActionDone = true;
        enemyActor.allowPlayerAttack = false;
        enemyAttacksAfter = 3;
    }
}
