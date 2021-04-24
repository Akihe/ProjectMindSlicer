package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Timer;

public class kidActor extends Actor {

    Texture texture;
    SequenceAction fadeIn;
    SequenceAction fadeOut;


    public kidActor() {

        texture = new Texture("child1.png");

        setWidth(texture.getWidth()/3f);
        setHeight(texture.getHeight()/3f);
        setBounds(580,40, getWidth(), getHeight());

        initialFade();
    }

    @Override
    public void draw(Batch batch, float alpha) {

        batch.setColor(this.getColor());
        batch.getColor().a *= alpha;

        batch.draw(texture,
                this.getX(), this.getY(),
                this.getOriginX(),
                this.getOriginY(),
                this.getWidth(),
                this.getHeight(),
                this.getScaleX(),
                this.getScaleY(),
                this.getRotation(),0,0,
                texture.getWidth(), texture.getHeight(), false, false);
        batch.setColor(Color.WHITE); // reset the color
    }

    public void initialFade() {
        fadeOut = new SequenceAction();
        fadeOut.addAction((Actions.fadeOut(0)));
        kidActor.this.addAction(fadeOut);
    }

    public void appear() {
        fadeIn = new SequenceAction();
        fadeIn.addAction((Actions.fadeIn(2)));

        float delay = 2;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                kidActor.this.addAction(fadeIn);
            }
        }, delay);
    }
}
