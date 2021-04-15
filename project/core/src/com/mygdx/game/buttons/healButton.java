package com.mygdx.game.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.playerActor;
import com.mygdx.game.screens.level1;

public class healButton extends Actor{

    private final Texture texture;
    playerActor player;

    public healButton() {
        texture = new Texture("sweet_health.png");
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(235, 230, getWidth(), getHeight());

        player = level1.player;

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            player.MunkkiHeal();
            return true;
        }
    }
}

