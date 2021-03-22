package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class enemyActor extends Actor {

    private Texture enemyTexture;

    public int ENEMY_HEALTH = 100;
    private String healthAmount;

    public enemyActor() {
        enemyTexture = new Texture(Gdx.files.internal("kaljanhimo.png"));

        setWidth(enemyTexture.getWidth()/2);
        setHeight(enemyTexture.getHeight()/2);
        setBounds(600,40, getWidth(), getHeight());

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
        Main.font.draw(Main.getBatch(), healthAmount, 660, 30);
    }
    public void enemyHit() {
        SequenceAction sequenceAction = new SequenceAction();

        MoveToAction moveAction = new MoveToAction();
        MoveToAction moveBack = new MoveToAction();


        RotateToAction rotateAction = new RotateToAction();

        moveAction.setPosition(300f, 30f);
        moveAction.setDuration(0.7f);

        moveBack.setPosition(660f, 30f);
        moveBack.setDuration(1.9f);


        sequenceAction.addAction(moveAction);
        sequenceAction.addAction(rotateAction);
        sequenceAction.addAction(moveBack);

        enemyActor.this.addAction(sequenceAction);

        fightingStageScreen.player.reduceHealth(5);
        playerActor.playerActionDone = false;
    }
    public void enemyDie() {
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
    }
}
