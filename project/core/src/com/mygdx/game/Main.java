package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class Main extends Game {

	static SpriteBatch batch;
	Texture img;
	public static BitmapFont font;

	public static final int WORLD_WIDTH = 800;
	public static final int WORLD_HEIGHT = 480;

	public static SpriteBatch getBatch() {
		return batch;
	}


	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new mainMenuScreen(this));

		font = new BitmapFont();
		font.getData().setScale(2);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
