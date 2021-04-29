package com.mygdx.MindSlicer.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.MindSlicer.defaultValues;
import com.mygdx.MindSlicer.screens.level1;


public class thinkButton extends Actor {

    private final Texture texture;
    com.mygdx.MindSlicer.playerActor player;

    public thinkButton() {
        texture = new Texture("coffee_cup.png");

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(80, 320, getWidth(), getHeight());

        if (com.mygdx.MindSlicer.defaultValues.levelInd == 1) {
            player = level1.player;
        } else if (defaultValues.levelInd == 2) {
            player = com.mygdx.MindSlicer.screens.level2.player;
        } else if (com.mygdx.MindSlicer.defaultValues.levelInd == 3) {
            player = com.mygdx.MindSlicer.screens.level3.player;
        }

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (!com.mygdx.MindSlicer.playerActor.playerActionDone && com.mygdx.MindSlicer.enemyActor.allowPlayerAttack) {
                player.thinkAction();
            }
            return true;
        }
    }
}
