package com.badlogic.gdx.tests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.tests.utils.GdxTest;

public class MusicTest extends GdxTest {

	@Override public boolean needsGL20 () {
		return false;
	}

	Music music;
	TextureRegion buttons;
	SpriteBatch batch;
	BitmapFont font;	
	
	@Override public void create() {
		music = Gdx.audio.newMusic(Gdx.files.internal("data/threeofaperfectpair.mp3"));
		buttons = new TextureRegion(new Texture(Gdx.files.internal("data/playback.png")));
		batch = new SpriteBatch();
		font = new BitmapFont();
	}
	
	@Override public void resize(int width, int height) {
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
	}
	
	@Override public void render() {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(buttons, 0, 0);
		font.draw(batch, "\"Three of a perfect pair: " +  music.getPosition(), 10, Gdx.graphics.getHeight() - 20);
		batch.end();			
		
		if(Gdx.input.justTouched()) {
			if(Gdx.input.getY() > Gdx.graphics.getHeight() - 64) {
				if(Gdx.input.getX() < 64) music.play();
				if(Gdx.input.getX() > 64 && Gdx.input.getX() < 128) music.stop();
				if(Gdx.input.getX() > 128 && Gdx.input.getX() < 192) music.pause();
			}
		}
	}
}