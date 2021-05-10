package fi.tuni.MindSlicer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Timer;

/**
 *
 * Kid actor class
 * <p>This class is for constructing the child with it's necessary variables, values and textures, as well as managing the methods of it</p>
 *
 */

public class kidActor extends Actor {

    Texture texture;
    SequenceAction fadeIn;
    SequenceAction fadeOut;

    Sound appearSound;
    boolean soundPlayed;

    private int currentLevel;

    /**
     * kid's construction
     * @param level Is necessary, so the code knows, which level the player is in when constructing, and which texture to choose.
     *              <p> Here the kid is constructed to a stage with it's proper parameters and textures.</p>
     */

    public kidActor(int level) {
        currentLevel = level;

        soundPlayed = false;
        appearSound = Gdx.audio.newSound(Gdx.files.internal("sounds/kidappear.wav"));
        if (currentLevel == 1) {
            texture = new Texture(Gdx.files.internal("child_one.png"));
        } else if (currentLevel == 2) {
            texture = new Texture(Gdx.files.internal("child3.png"));
        } else if (currentLevel == 3) {
            texture = new Texture(Gdx.files.internal("child2.png"));
        }

        setWidth(texture.getWidth());
        setHeight(texture.getHeight());

        setBounds(580,40, getWidth(), getHeight());

        initialFade();
    }

    /**
     * kid's draw method
     * @param batch
     * @param alpha
     * <p>here the alpha value is tweaked</p>
     */
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

    /**
     * kid's method for fading
     * <p>a method that makes the kid invisible immediately at the start of a level with a fadeOut sequenceAction</p>
     */
    public void initialFade() {
        fadeOut = new SequenceAction();
        fadeOut.addAction((Actions.fadeOut(0)));
        kidActor.this.addAction(fadeOut);
    }

    /**
     * kid's method for appearing
     * <p>a method that makes the kid appear at the end of a level with a fadeIn sequenceAction, with a slight delay and a sound effect</p>
     */

    public void appear() {
        fadeIn = new SequenceAction();
        fadeIn.addAction((Actions.fadeIn(2)));

        float delay = 2;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                kidActor.this.addAction(fadeIn);
                if (!soundPlayed) {
                    appearSound.play();
                    soundPlayed = true;
                }
            }
        }, delay);
    }
}
