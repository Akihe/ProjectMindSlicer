package fi.tuni.MindSlicer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Timer;
import fi.tuni.MindSlicer.screens.level1;


import java.util.Random;

import fi.tuni.MindSlicer.screens.level2;
import fi.tuni.MindSlicer.screens.level3;

/**
 * Enemy actor class
 * <p>This class is for constructing the enemy with it's necessary variables, values and textures, as well as managing the methods of the enemy
 * different textures are for spesific actions. healthAmount is a string that indicates the current hp ENEMY_HEALTH, thats used in damage calculations
 * ENEMY_ATK and ENEMY_DEF are the base values for the opponents. attackRoll is used to calculate which move the enemy uses. Boolean usingBuffAttack is used to determine, if buff texture is drawn</p>
 */
public class enemyActor extends Actor {

    private Texture enemyTexture;
    private final Texture buffTexture;

    private String healthAmount;

    public int ENEMY_HEALTH;

    private int ENEMY_ATK;
    private int ENEMY_DEF;

    private Random attackRoll = new Random();
    private int attackNumber;
    private final int currentLevel;

    private final Sound hitSound;
    private final Sound buffSound;
    private final Sound majorHitSound;

    boolean usingBuffAttack;

    public static boolean allowPlayerAttack;

    /**
     * Enemy construction
     * @param level Is necessary, so the code knows, which level the player is in when constructing. This helps keep track of which stats and textures to use.
     *              <p> Here the enemy is constructed to a stage with it's proper parameters and textures</p>
     */
    public enemyActor(int level) {
        allowPlayerAttack = true;
        currentLevel = level;

        usingBuffAttack = false;

        hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/monsterbasic.mp3"));
        buffSound = Gdx.audio.newSound(Gdx.files.internal("sounds/monsterbuff.mp3"));
        majorHitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/monsterspecial.mp3"));

        if (currentLevel == 1) {
            enemyTexture = new Texture(Gdx.files.internal("basicmonster.png"));
            ENEMY_HEALTH = 100;
            ENEMY_ATK = 30;
            ENEMY_DEF = 10;
        } else if (currentLevel == 2) {
            enemyTexture = new Texture(Gdx.files.internal("masismonster.png"));
            ENEMY_HEALTH = 150;
            ENEMY_ATK = 40;
            ENEMY_DEF = 20;
        } else if (currentLevel == 3) {
            enemyTexture = new Texture(Gdx.files.internal("moneymonster.png"));
            ENEMY_HEALTH = 300;
            ENEMY_ATK = 50;
            ENEMY_DEF = 40;
        }

        setWidth(enemyTexture.getWidth());
        setHeight(enemyTexture.getHeight());
        setBounds(550,40, getWidth(), getHeight());

        healthAmount = "" + ENEMY_HEALTH;

        buffTexture = new Texture("bad_words.png");
    }

    /**
     * the method for the enemy's incoming damage calculation
     * @param damageTaken is a value that comes from the players action
     * <p>This method is used to reduce the enemy's health when taking damage, and takes into account the defense amount</p>
     */
    public void reduceHealth(int damageTaken) {

        int totalDamage = ENEMY_DEF - damageTaken;
        if(totalDamage >= 0) {
            totalDamage = -1;
        }

        this.ENEMY_HEALTH = this.ENEMY_HEALTH + totalDamage;
        healthAmount = "" + ENEMY_HEALTH;
    }

    /**
     * the rng method for attacking
     * <p>a method that rolls a new random value for choosing an attack</p>
     */
    public void chooseAttack(){
       attackNumber = attackRoll.nextInt(100);
    }

    /**
     *the enemy's draw method
     * @param batch
     * @param alpha
     * <p> in the draw method for the enemy, the enemy's health points are also consistently drawn on the screen.
     * boolean usingBuffAttack is used to see if the buffTexture is drawn. batch.setColor resets the color</p>
     */
    @Override
    public void draw(Batch batch, float alpha) {
        batch.setColor(this.getColor());
        batch.getColor().a *= alpha;
        batch.draw(enemyTexture,
                this.getX(), this.getY(),
                this.getOriginX(),
                this.getOriginY(),
                this.getWidth(),
                this.getHeight(),
                this.getScaleX(),
                this.getScaleY(),
                this.getRotation(),0,0,
                enemyTexture.getWidth(), enemyTexture.getHeight(), false, false);
        Main.font.draw(batch, healthAmount, 600, 30);

        if (usingBuffAttack) {
            if (currentLevel == 1) {
                batch.draw(buffTexture, 500f, 150f, buffTexture.getWidth(), buffTexture.getHeight());
            } else if (currentLevel == 2) {
                batch.draw(buffTexture, 480f, 200f, buffTexture.getWidth(), buffTexture.getHeight());
            } else if (currentLevel == 3) {
                batch.draw(buffTexture, 530f, 300f, buffTexture.getWidth(), buffTexture.getHeight());
            }
        }
        batch.setColor(Color.WHITE); // reset the color
    }

    /**
     * the opponents main offensive skill
     * <p>a method that reduces the correct stage's opponents health according to the enemy's attack and the player's defense stats
     * The opponent moves according to a SequenceAction updated to a new one for the duration of the action and after the action, playerActionDone=false lets the player attack.
     * currentLevel is used to track which level the player is on.
     * attackLength is used to measure th time of the action, to pace the fight</p>
     */

    public void enemyHit() {
        hitSound.play();
        int attackLength = 3;
        SequenceAction sequenceAction = new SequenceAction();

        MoveToAction moveAction = new MoveToAction();
        MoveToAction moveBack = new MoveToAction();

        moveAction.setPosition(300f, 40f);
        moveAction.setDuration(0.7f);

        moveBack.setPosition(550f, 40f);
        moveBack.setDuration(1.9f);

        sequenceAction.addAction(moveAction);
        sequenceAction.addAction(moveBack);

        enemyActor.this.addAction(sequenceAction);

        if(currentLevel == 1) {
            level1.player.reduceHealth(ENEMY_ATK);
        } else if (currentLevel == 2) {
            level2.player.reduceHealth(ENEMY_ATK);
        } else if (currentLevel == 3) {
            level3.player.reduceHealth(ENEMY_ATK);
        }

        playerActor.playerActionDone = false;
        allowPlayerToAttack(attackLength);
    }
    /**
     * the opponent's buff action
     * <p>the opponent's action for temporarily increasing the attack value by a base value.
     * The opponent moves according to a SequenceAction updated to a new one for the duration of the action and after the action, playerActionDone=false lets the player attack.
     * currentLevel is used to track which level the player is on. attackLength is used to measure th time of the action, to pace the fight</p>
      */

    public void enemyBuff() {
        buffSound.play();
        int attackLength = 3;
        SequenceAction Think_Action = new SequenceAction();

        MoveToAction moveUpAction = new MoveToAction();
        MoveToAction moveBackDown = new MoveToAction();
        RotateToAction rotateLeftAction = new RotateToAction();
        RotateToAction rotateRightAction = new RotateToAction();
        RotateToAction rotateBackAction = new RotateToAction();

        moveUpAction.setPosition(550f, 220f);
        moveUpAction.setDuration(0.2f);
        moveBackDown.setPosition(550f, 40f);
        moveBackDown.setDuration(0.1f);

        rotateLeftAction.setRotation(15f);
        rotateLeftAction.setDuration(0.4f);
        rotateRightAction.setRotation(-15f);
        rotateRightAction.setDuration(0.4f);
        rotateBackAction.setRotation(0f);
        rotateBackAction.setDuration(0.1f);

        Think_Action.addAction(rotateLeftAction);
        Think_Action.addAction(rotateRightAction);
        Think_Action.addAction(rotateBackAction);
        Think_Action.addAction(moveUpAction);
        Think_Action.addAction(moveBackDown);

        enemyActor.this.addAction(Think_Action);

        usingBuffAttack = true;
        int buffAmount = 5;
        ENEMY_ATK += buffAmount;
        playerActor.playerActionDone = false;
        allowPlayerToAttack(attackLength);
    }

    /**
     * the opponent's strong action
     * <p>the opponent's action for dealing big damage according to a base value.
     * The opponent moves according to a SequenceAction updated to a new one for the duration of the action and after the action, playerActionDone=false lets the player attack.
     * currentLevel is used to track which level the player is on. attackLength is used to measure th time of the action, to pace the fight</p>
     */
    public void majorAttack() {
        majorHitSound.play();
        int attackLength = 3;
        SequenceAction sequenceAction = new SequenceAction();

        MoveToAction moveAction = new MoveToAction();
        MoveToAction moveBack = new MoveToAction();

        RotateToAction rotateAction = new RotateToAction();

        moveAction.setPosition(300f, 140f);
        moveAction.setDuration(0.3f);

        moveBack.setPosition(550f, 40f);
        moveBack.setDuration(2.3f);

        sequenceAction.addAction(moveAction);
        sequenceAction.addAction(rotateAction);
        sequenceAction.addAction(moveBack);
        enemyActor.this.addAction(sequenceAction);

        if (currentLevel == 1) {
            level1.player.reduceHealth(50);
        } else if (currentLevel == 2) {
            level2.player.reduceHealth(50);
        } else if (currentLevel == 3) {
            level3.player.reduceHealth(50);
        }

        playerActor.playerActionDone = false;
        allowPlayerToAttack(attackLength);
    }

    /**
     * the method that tells which attack to use
     * <p>the method holds certain probabilities for using specific actions, based the randomised attack roll</p>
     */
    public void randomAttack() {
        if(attackNumber >= 0 && attackNumber < 50){
            enemyHit();
        }
        if(attackNumber >= 50 && attackNumber < 80){
            enemyBuff();
        }
        if(attackNumber >= 80 && attackNumber <= 100){
            majorAttack();
        }
    }

    /**
     * timing the turn structure of levels
     * @param delay int value for
     *              <p>uses the lengths of the enemy's actions to tell when the player can take a new action</p>
     */

    public void allowPlayerToAttack(int delay) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                allowPlayerAttack = true;
                usingBuffAttack = false;

                if(currentLevel == 1) {
                    level1.playerTurnTeller();;
                } else if (currentLevel == 2) {
                    level2.playerTurnTeller();;
                } else if (currentLevel == 3) {
                    level3.playerTurnTeller();;
                }

            }
        }, delay);
    }

    /**
     * the method for when the opponent is defeated
     * <p>creates and playes a sequenceAction, where the opponent dissapears. Used along with the kid actor's appear method.</p>
     */
    public void enemyDie() {
        SequenceAction fadeOut = new SequenceAction();
        fadeOut.addAction((Actions.fadeOut(2)));
        enemyActor.this.addAction(fadeOut);
    }
}
