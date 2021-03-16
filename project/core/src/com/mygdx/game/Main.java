package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private final float screenWidth = 8.0f;
	private final float screenHeight = 4.8f;


	private Stage gameStage;
	private playerActor player;


	private Label outputLabel;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Garmfiel.png");

		gameStage = new Stage(new FitViewport(screenWidth, screenHeight), batch);

		player = new playerActor();
		gameStage.addActor(player);
		Gdx.input.setInputProcessor(gameStage);

		Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		ImageButton button2 = new ImageButton(mySkin);
		button2.setSize(screenWidth/12,screenHeight/12);
		button2.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Garmfiel.png"))));
		button2.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("badlogic.jpg"))));
		button2.setTransform(true);
		button2.scaleBy(1f);
		button2.setPosition(4f,2f);
		gameStage.addActor(button2);

		button2.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				outputLabel.setText("Press a Button");
			}

			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				outputLabel.setText("Pressed Text Button");
				return true;
			}
		});

		gameStage.addActor(button2);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gameStage.act();

		gameStage.draw();

		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
