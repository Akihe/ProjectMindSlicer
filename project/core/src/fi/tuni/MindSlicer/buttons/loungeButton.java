package fi.tuni.MindSlicer.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import fi.tuni.MindSlicer.screens.levelSelect;

/**
 * A button for entering the Lounge.
 */
public class loungeButton extends Actor {

    private final Texture playTexture;

    public loungeButton() {
        playTexture = new Texture(Gdx.files.internal("loungebtn.png"));

        setWidth(playTexture.getWidth());
        setHeight(playTexture.getHeight());
        setBounds(210, 260, getWidth() * 1.5f , getHeight() * 1.5f);

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(playTexture, this.getX(), this.getY(), getWidth(), getHeight());

    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            levelSelect.setLounge();
            return true;
        }
    }
}
