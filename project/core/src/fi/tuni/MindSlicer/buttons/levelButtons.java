package fi.tuni.MindSlicer.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import fi.tuni.MindSlicer.defaultValues;
import fi.tuni.MindSlicer.screens.levelSelect;


/**
 * Creates a button for the called level. Has a texture based and
 */
public class levelButtons extends Actor {

    private Texture texture;
    private int currentLevel;

    public levelButtons(float xPos, float yPos, int level) {
        currentLevel = level;

        texture = new Texture("yksi.png");

        if (currentLevel == 2) {
            texture = new Texture("kaksi.png");
        } else if (currentLevel == 3) {
            texture = new Texture("kolme.png");
        }

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        setBounds(xPos, yPos, getWidth() * 2f, getHeight() * 2f);

        addListener(new PlayerListener());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), getWidth(), getHeight());

    }

    class PlayerListener extends InputListener {

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (currentLevel == 1) {
                levelSelect.setLevel1();
               defaultValues.levelInd = 1;
            } else if (currentLevel == 2) {
                levelSelect.setLevel2();
                defaultValues.levelInd = 2;
            } else if (currentLevel == 3) {
                levelSelect.setLevel3();
                defaultValues.levelInd = 3;
            }
            return true;
        }
    }
}
