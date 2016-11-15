package ecu.silicon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kotcrab.vis.ui.VisUI;
import ecu.silicon.screens.game.STGameScreen;
import ecu.silicon.screens.STMenuScreen;

public class SiliconTycoon extends Game {
	public static SiliconTycoon INSTANCE;

	public STRepository repository;
	public SpriteBatch batch;

	public STMenuScreen menuScreen;
	public STGameScreen gameScreen;

	public SiliconTycoon(){
		repository = new STRepository();
	}

	@Override
	public void create() {
        batch = new SpriteBatch();
		repository.loadAll();
		VisUI.load(Gdx.files.local("gui/theme/tinted.json"));
		menuScreen = new STMenuScreen();
		gameScreen = new STGameScreen(new STSaveState("Mitchell"));

		setScreen(menuScreen);
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		VisUI.dispose();
		repository.dispose();
	}

	public static SiliconTycoon getInstance(){
		return INSTANCE;
	}
}
