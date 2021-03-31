package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.game.screens.*;


public class enemyActor extends Actor {

    private Texture enemyTexture;

    public int ENEMY_HEALTH = 100;
    private String healthAmount;
    private int ATK_damage = 10;

    public enemyActor() {
        enemyTexture = new Texture(Gdx.files.internal("monster2.png"));

        setWidth(enemyTexture.getWidth()/3);
        setHeight(enemyTexture.getHeight()/3);
        setBounds(550,140, getWidth(), getHeight());

        healthAmount = "" + ENEMY_HEALTH;
    }

    public void reduceHealth(int damageTaken) {
        this.ENEMY_HEALTH = this.ENEMY_HEALTH - damageTaken;
        healthAmount = "" + ENEMY_HEALTH;
    }

    @Override
    public void draw(Batch batch, float alpha) {
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
    }

    public void enemyHit() {
        SequenceAction sequenceAction = new SequenceAction();

        MoveToAction moveAction = new MoveToAction();
        MoveToAction moveBack = new MoveToAction();


        RotateToAction rotateAction = new RotateToAction();

        moveAction.setPosition(300f, 140f);
        moveAction.setDuration(0.7f);

        moveBack.setPosition(600f, 140f);
        moveBack.setDuration(1.9f);


        sequenceAction.addAction(moveAction);
        sequenceAction.addAction(rotateAction);
        sequenceAction.addAction(moveBack);

        enemyActor.this.addAction(sequenceAction);

        level1.player.reduceHealth(5);
        playerActor.playerActionDone = false;
    }

    public void enemyBuff() {
        int buffAmount = 5;
        ATK_damage += buffAmount;
    }


    public void majorAttack() {
        level1.player.reduceHealth(50);
        playerActor.playerActionDone = false;
    }
/*
    public void randomAttack() {

        switch(random) {
            case 1: enemyHit();
            case 2: enemyBuff();
            case 3: majorAttack();
        }
    }

 */
/*
    public void allowPlayerAttack(int delay) {
        int timer = 0;
        timer += Gdx.graphics.getDeltaTime();

        if (timer > delay) {
        }
    }


 */
    public void chooseEnemyAttack() {

    }


    public void enemyDie() {

        enemyTexture = new Texture("child1.png");
        setHeight(enemyTexture.getHeight() /3);
        setWidth(enemyTexture.getWidth() /3);
        setBounds(550f, 20f, getWidth(), getHeight());

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
