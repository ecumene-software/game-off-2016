package ecu.silicon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import ecu.silicon.SiliconTycoon;

public class STMenuScreen implements Screen {

    private OrthographicCamera camera;

    public STMenuScreen(){

    }

    private float logoY = 800;
    private float x;
    
    @Override
    public void render(float delta) {
        SiliconTycoon.getInstance().batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(0.15f, 0.15f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        logoY += ((-SiliconTycoon.getInstance().repository.logo.getHeight()/2 + 150) - logoY) * 0.1f;

        SiliconTycoon.getInstance().batch.begin();
        SiliconTycoon.getInstance().batch.draw(SiliconTycoon.getInstance().repository.server_background, -SiliconTycoon.getInstance().repository.server_background.getWidth()/2+x, -SiliconTycoon.getInstance().repository.server_background.getHeight());
        SiliconTycoon.getInstance().batch.draw(SiliconTycoon.getInstance().repository.grass_background, -SiliconTycoon.getInstance().repository.grass_background.getWidth()/2-x, 0);
        SiliconTycoon.getInstance().batch.draw(SiliconTycoon.getInstance().repository.logo, -SiliconTycoon.getInstance().repository.logo.getWidth()/2, logoY);
        SiliconTycoon.getInstance().batch.end();

        x += 0.5f;
    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(width, height);
        camera.update();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
