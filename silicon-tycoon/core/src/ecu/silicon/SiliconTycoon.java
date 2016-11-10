package ecu.silicon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ecu.silicon.screens.STMenuScreen;

public class SiliconTycoon extends Game {
	public static SiliconTycoon INSTANCE;

	public STRepository repository;
	public SpriteBatch batch;

	public SiliconTycoon(){
		repository = new STRepository();
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		repository.loadAll();

		setScreen(new STMenuScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		repository.dispose();
	}

	public static SiliconTycoon getInstance(){
		return INSTANCE;
	}
}
