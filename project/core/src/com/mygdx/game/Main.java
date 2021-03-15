package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private final float screenWidth = 8.0f;
	private final float screenHeight = 4.8f;


	private Stage gameStage;
	private playerActor player;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Garmfiel.png");

		gameStage = new Stage(new FitViewport(screenWidth, screenHeight), batch);

		player = new playerActor();
		gameStage.addActor(player);
		Gdx.input.setInputProcessor(gameStage);
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
