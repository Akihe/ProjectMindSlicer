package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.screens.*;


import java.util.Random;

public class enemyActor extends Actor {

    private Texture enemyTexture;

    private String healthAmount;

    public int ENEMY_HEALTH;
    private int ATK_damage;
    private int ENEMY_DEF;
    public static Random attackRoll = new Random();
    public static int AttackNRO;
    public static boolean allowPlayerAttack;
    private int currentLevel;

    private Texture buffTexture;

    boolean usingBuffAttack;

    public enemyActor(int level) {
        allowPlayerAttack = true;
        currentLevel = level;

        usingBuffAttack = false;

        if (currentLevel == 1) {
            enemyTexture = new Texture(Gdx.files.internal("rasistimonster.png"));
            ENEMY_HEALTH = 100;
            ATK_damage = 30;
            ENEMY_DEF = 10;
        } else if (currentLevel == 2) {
            enemyTexture = new Texture(Gdx.files.internal("masismonster.png"));
            ENEMY_HEALTH = 150;
            ATK_damage = 40;
            ENEMY_DEF = 20;
        } else if (currentLevel == 3) {
            enemyTexture = new Texture(Gdx.files.internal("moneymonster.png"));
            ENEMY_HEALTH = 300;
            ATK_damage = 50;
            ENEMY_DEF = 40;
        }
        setWidth(enemyTexture.getWidth());
        setHeight(enemyTexture.getHeight());
        setBounds(550,40, getWidth(), getHeight());

        healthAmount = "" + ENEMY_HEALTH;

        buffTexture = new Texture("bad_words.png");
    }

    public void reduceHealth(int damageTaken) {

        int totalDamage = ENEMY_DEF - damageTaken;
        if(totalDamage >= 0) {
            totalDamage = -1;
        }

        this.ENEMY_HEALTH = this.ENEMY_HEALTH + totalDamage;
        healthAmount = "" + ENEMY_HEALTH;
    }

    public void chooseAttack(){
       AttackNRO = attackRoll.nextInt(100);
    }

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
        Main.font.draw(batch, healthAmount, 660, 30);
        if (usingBuffAttack) {
            batch.draw(buffTexture, 500f, 150f, buffTexture.getWidth(), buffTexture.getHeight());
        }
        batch.setColor(Color.WHITE); // reset the color
    }

    public void enemyHit() {
        int attackLength = 3;
        SequenceAction sequenceAction = new SequenceAction();

        MoveToAction moveAction = new MoveToAction();
        MoveToAction moveBack = new MoveToAction();

        moveAction.setPosition(300f, 40f);
        moveAction.setDuration(0.7f);

        moveBack.setPosition(600f, 40f);
        moveBack.setDuration(1.9f);


        sequenceAction.addAction(moveAction);
        sequenceAction.addAction(moveBack);

        enemyActor.this.addAction(sequenceAction);

        if(currentLevel == 1) {
            level1.player.reduceHealth(ATK_damage);
        } else if (currentLevel == 2) {
            level2.player.reduceHealth(ATK_damage);
        } else if (currentLevel == 3) {
            level3.player.reduceHealth(ATK_damage);
        }

        playerActor.playerActionDone = false;
        allowPlayerToAttack(attackLength);

    }

    public void enemyBuff() {
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
        ATK_damage += buffAmount;
        playerActor.playerActionDone = false;
        allowPlayerToAttack(attackLength);
    }

    public void majorAttack() {
        int attackLength = 3;
        SequenceAction sequenceAction = new SequenceAction();

        MoveToAction moveAction = new MoveToAction();
        MoveToAction moveBack = new MoveToAction();

        RotateToAction rotateAction = new RotateToAction();

        moveAction.setPosition(300f, 140f);
        moveAction.setDuration(0.3f);

        moveBack.setPosition(600f, 40f);
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

    public void randomAttack() {
        Gdx.app.log("random", "nro " + AttackNRO);
        if(AttackNRO >= 0 && AttackNRO < 50){
            enemyHit();
        }
        if(AttackNRO >= 50 && AttackNRO < 80){
            enemyBuff();
        }
        if(AttackNRO >= 80 && AttackNRO <= 100){
            majorAttack();
        }
    }


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

    public void enemyDie() {

            SequenceAction fadeOut = new SequenceAction();
            fadeOut.addAction((Actions.fadeOut(2)));
            enemyActor.this.addAction(fadeOut);

      //  playerActor.MONEY = playerActor.MONEY+50; !!!!
    }
}
