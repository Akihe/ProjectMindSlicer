package fi.tuni.MindSlicer.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import fi.tuni.MindSlicer.defaultValues;
import fi.tuni.MindSlicer.enemyActor;
import fi.tuni.MindSlicer.playerActor;
import fi.tuni.MindSlicer.screens.level1;
import fi.tuni.MindSlicer.screens.level2;
import fi.tuni.MindSlicer.screens.level3;

/**
 * Button for the players heal action. Has a texture and an inputlistener.
 */
public class healButton extends Actor{

    private final Texture texture;
    playerActor player;

    public healButton() {
        texture = new Texture("sweet_health.png");
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(235, 230, getWidth(), getHeight());

        if (defaultValues.levelInd == 1) {
            player = level1.player;
        } else if (defaultValues.levelInd == 2) {
            player = level2.player;
        } else if (defaultValues.levelInd == 3) {
            player = level3.player;
        }

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());
    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (!playerActor.playerActionDone && enemyActor.allowPlayerAttack) {
                player.heal();
            }
            return true;
        }
    }
}

