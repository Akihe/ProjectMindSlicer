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

    private Texture playerTexture;

    public playerActor() {
        playerTexture = new Texture(Gdx.files.internal("playercharacter.png"));

        setWidth(playerTexture.getWidth() / 100f);
        setHeight(playerTexture.getHeight() / 100f);
        setBounds(0,0, getWidth(), getHeight());

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
    }

    class PlayerListener extends InputListener {


        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            SequenceAction sequenceAction = new SequenceAction();

            MoveToAction moveAction = new MoveToAction();
            RotateToAction rotateAction = new RotateToAction();
            ScaleToAction scaleAction = new ScaleToAction();

            moveAction.setPosition(1.00f, 1.00f);
            moveAction.setDuration(1f);

            rotateAction.setRotation(720f);
            rotateAction.setDuration(1f);

            scaleAction.setScale(0.5f);
            scaleAction.setDuration(1f);

            sequenceAction.addAction(moveAction);
            sequenceAction.addAction(rotateAction);
            sequenceAction.addAction(scaleAction);


            playerActor.this.addAction(sequenceAction);
            return true;
        }
    }
}
