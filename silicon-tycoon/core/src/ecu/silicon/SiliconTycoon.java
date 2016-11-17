package ecu.silicon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kotcrab.vis.ui.VisUI;
import ecu.silicon.models.STSaveState;
import ecu.silicon.screens.STGameScreen;
import ecu.silicon.screens.STMenuScreen;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

public class SiliconTycoon extends Game {
	public static SiliconTycoon INSTANCE;

	public STRepository repository;
	public SpriteBatch batch;

	public STMenuScreen menuScreen;
	public STGameScreen gameScreen;

	public String gameDirectory = "./";

	public SiliconTycoon(){
		repository = new STRepository();
	}

	@Override
	public void create() {
        batch = new SpriteBatch();
		repository.loadAll();
		VisUI.load(Gdx.files.local("gui/theme/tinted.json"));
		setScreen(menuScreen = new STMenuScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
        getScreen().dispose();
		batch.dispose();
		VisUI.dispose();
		repository.dispose();
	}

	public static SiliconTycoon getInstance(){
		return INSTANCE;
	}
}
