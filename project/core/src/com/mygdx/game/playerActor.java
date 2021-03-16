package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class playerActor extends Actor {

    private final Texture playerTexture;

    private int PLAYER_HEALTH = 100;
    private String healthAmount;

    public playerActor() {
        playerTexture = new Texture(Gdx.files.internal("playercharacter.png"));
        healthAmount = "" + PLAYER_HEALTH;


        setWidth(playerTexture.getWidth()/2);
        setHeight(playerTexture.getHeight()/2);
        setBounds(0,40, getWidth(), getHeight());

        addListener(new PlayerListener());

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
        Main.font.draw(Main.getBatch(), healthAmount, 50, 30);
    }

    public void hitAction() {
        SequenceAction sequenceAction = new SequenceAction();

        MoveToAction moveAction = new MoveToAction();
        MoveToAction moveBack = new MoveToAction();


        moveAction.setPosition(300f, 40f);
        moveAction.setDuration(1f);
        moveBack.setPosition(0f, 40f);
        moveBack.setDuration(1f);

        sequenceAction.addAction(moveAction);
        sequenceAction.addAction(moveBack);

        playerActor.this.addAction(sequenceAction);
    }

     public class PlayerListener extends InputListener {


        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return true;
        }
    }
}
