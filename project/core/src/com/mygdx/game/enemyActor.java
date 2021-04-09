package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.screens.*;


import java.util.Random;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;


public class enemyActor extends Actor {

    private Texture enemyTexture;

    public int ENEMY_HEALTH = 100;
    private String healthAmount;
    private int ATK_damage = 20;
    private int ENEMY_DEF=5;
    public static Random attackRoll=new Random();
    public static int AttackNRO;
    SequenceAction fadeIn;

    public enemyActor() {
        enemyTexture = new Texture(Gdx.files.internal("monster2.png"));

        setWidth(enemyTexture.getWidth()/3);
        setHeight(enemyTexture.getHeight()/3);
        setBounds(550,40, getWidth(), getHeight());

        healthAmount = "" + ENEMY_HEALTH;
    }

    public void reduceHealth(int damageTaken) {

        int totalDamage=ENEMY_DEF-damageTaken;
        if(totalDamage>=0){
            totalDamage=-1;

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
        batch.setColor(Color.WHITE); // reset the color
    }

    public void enemyHit() {
        SequenceAction sequenceAction = new SequenceAction();

        MoveToAction moveAction = new MoveToAction();
        MoveToAction moveBack = new MoveToAction();

        moveAction.setPosition(300f, 140f);
        moveAction.setDuration(0.7f);

        moveBack.setPosition(600f, 140f);
        moveBack.setDuration(1.9f);


        sequenceAction.addAction(moveAction);
        sequenceAction.addAction(moveBack);
/*
        enemyActor.this.addAction(sequence(sequenceAction, run(new Runnable() {
            public void run () {
                playerActor.playerActionDone = false;
            }
        })));

 */

        enemyActor.this.addAction(sequenceAction);
        level1.player.reduceHealth(ATK_damage);
        playerActor.playerActionDone = false;

    }

    public void enemyBuff() {
        SequenceAction Think_Action = new SequenceAction();

        MoveToAction moveUpAction = new MoveToAction();
        MoveToAction moveBackDown = new MoveToAction();
        RotateToAction rotateLeftAction = new RotateToAction();
        RotateToAction rotateRightAction = new RotateToAction();
        RotateToAction rotateBackAction = new RotateToAction();

        moveUpAction.setPosition(550f, 220f);
        moveUpAction.setDuration(0.2f);
        moveBackDown.setPosition(550f, 140f);
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



        int buffAmount = 5;
        ATK_damage += buffAmount;
        playerActor.playerActionDone = false;
    }


    public void majorAttack() {
        SequenceAction sequenceAction = new SequenceAction();

        MoveToAction moveAction = new MoveToAction();
        MoveToAction moveBack = new MoveToAction();


        RotateToAction rotateAction = new RotateToAction();

        moveAction.setPosition(300f, 140f);
        moveAction.setDuration(0.3f);

        moveBack.setPosition(600f, 140f);
        moveBack.setDuration(2.3f);


        sequenceAction.addAction(moveAction);
        sequenceAction.addAction(rotateAction);
        sequenceAction.addAction(moveBack);
        enemyActor.this.addAction(sequenceAction);

        level1.player.reduceHealth(50);
        playerActor.playerActionDone = false;
    }

    public void randomAttack() {
        if(AttackNRO>=0 && AttackNRO<30){
            enemyHit();}
        if(AttackNRO>=30 && AttackNRO<67){
            enemyBuff();}
        if(AttackNRO>=67 && AttackNRO<=100){
            majorAttack();}
    }


/*
    public void allowPlayerAttack(int delay) {
        int timer = 0;
        timer += Gdx.graphics.getDeltaTime();

        if (timer > delay) {
        }
    }


 */

    public void enemyDie() {

            SequenceAction fadeOut = new SequenceAction();
            fadeOut.addAction((Actions.fadeOut(2)));
            enemyActor.this.addAction(fadeOut);

      //  playerActor.MONEY =playerActor.MONEY+50; !!!!

 /*
        SequenceAction sequenceAction = new SequenceAction();

        MoveToAction moveAction = new MoveToAction();

        RotateToAction rotateAction = new RotateToAction();

        moveAction.setPosition(1000f, 30f);
        moveAction.setDuration(0.01f);

        rotateAction.setRotation(720);
        rotateAction.setDuration(1f);

        sequenceAction.addAction(rotateAction);
        sequenceAction.addAction(moveAction);

        enemyActor.this.addAction(sequenceAction);
  */
    }
}
