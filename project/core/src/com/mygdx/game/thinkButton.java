package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class thinkButton extends Actor {

    private final Texture texture;
    playerActor player;

    public thinkButton() {
        texture = new Texture("Garmfiel.png");

        setWidth(texture.getWidth()/2);
        setHeight(texture.getHeight()/2);
        setBounds(150, 300, getWidth(), getHeight());

        player = fightingStageScreen.player;

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            player.thinkAction();
            return true;
        }
    }
}
