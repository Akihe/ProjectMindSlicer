package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.mygdx.game.screens.*;


public class playerActor extends Actor {

    private final Texture playerTexture;

    private int PLAYER_HEALTH = 100;
    private String healthAmount;
    public int PLAYER_ATK = 30;
    public static int MONEY =50;
    public int enemyAttacksAfter;

    public static boolean playerActionDone = false;

    public playerActor() {
        playerTexture = new Texture(Gdx.files.internal("playercharacter.png"));
        healthAmount = "" + PLAYER_HEALTH;

        setWidth(playerTexture.getWidth()/2);
        setHeight(playerTexture.getHeight()/2);
        setBounds(20,40, getWidth(), getHeight());

        addListener(new PlayerListener());
    }
//Liitetty reducehealth metodi pelaajalle myös. Voiko käyttää samaa metodia jos sen tekee uuteen luokkaan, ja kutsuu arvoja mm this.health (täytyy tehdä olio-ohjelmoinnilla parent olio, jolla on attribbutti HEALTH)
    public void reduceHealth(int damageTaken) {
        this.PLAYER_HEALTH = this.PLAYER_HEALTH - damageTaken;
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

        Main.font.draw(batch, healthAmount, 50, 30);
    }

    public void hitAction() {
        SequenceAction sequenceAction = new SequenceAction();

        MoveToAction moveAction = new MoveToAction();
        MoveToAction moveBack = new MoveToAction();
        RotateToAction rotateAction = new RotateToAction();
        RotateToAction rotateBackAction = new RotateToAction();

        moveAction.setPosition(300f, 40f);
        moveAction.setDuration(1f);
        moveBack.setPosition(20f, 40f);
        moveBack.setDuration(1f);

        rotateAction.setRotation(-360f);
        rotateAction.setDuration(0.8f);
        rotateBackAction.setRotation(0f);
        rotateBackAction.setDuration(0.01f);

        sequenceAction.addAction(moveAction);
        sequenceAction.addAction(rotateAction);
        sequenceAction.addAction(rotateBackAction);
        sequenceAction.addAction(moveBack);

        playerActor.this.addAction(sequenceAction);

        level1.enemy.reduceHealth(PLAYER_ATK);
        playerActionDone = true;
        enemyAttacksAfter = 4;
    }

    public void thinkAction() {

        SequenceAction Think_Action = new SequenceAction();

        MoveToAction moveUpAction = new MoveToAction();
        MoveToAction moveBackDown = new MoveToAction();
        RotateToAction rotateLeftAction = new RotateToAction();
        RotateToAction rotateRightAction = new RotateToAction();
        RotateToAction rotateBackAction = new RotateToAction();

        moveUpAction.setPosition(20f, 120f);
        moveUpAction.setDuration(0.2f);
        moveBackDown.setPosition(20f, 40f);
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

        playerActor.this.addAction(Think_Action);


        double ATK_RISE = PLAYER_ATK*1.5;
        PLAYER_ATK= (int) ATK_RISE;
        playerActionDone = true;
        enemyAttacksAfter = 3;

    }

    public class PlayerListener extends InputListener {


        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return true;
        }
    }
}
