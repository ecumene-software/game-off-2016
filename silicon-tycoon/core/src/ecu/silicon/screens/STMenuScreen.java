package ecu.silicon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ecu.silicon.SiliconTycoon;

public class STMenuScreen implements Screen {

    private OrthographicCamera camera;

    public STMenuScreen(){

    }

    private float logoY = 800;
    private float x;

    private TextureRegion serverRegion   = new TextureRegion(SiliconTycoon.getInstance().repository.server_background);
    private TextureRegion grassRegion    = new TextureRegion(SiliconTycoon.getInstance().repository.grass_background);

    @Override
    public void render(float delta) {
        SiliconTycoon.getInstance().batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(135f/255f, 206f/255f, 235f/255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        logoY += ((-SiliconTycoon.getInstance().repository.logo.getHeight()/2 + 150) - logoY) * 3f * delta;
        float logoYAnim = logoY - (-SiliconTycoon.getInstance().repository.logo.getHeight()/2 + 150);

        serverRegion.setRegionWidth(Gdx.graphics.getWidth());
        grassRegion.setRegionWidth(Gdx.graphics.getWidth());
        serverRegion.setRegionX((int)( x - (logoY/4)));
        grassRegion.setRegionX((int) (-x + (logoY/4)));

        SiliconTycoon.getInstance().batch.begin();
        SiliconTycoon.getInstance().batch.draw(serverRegion, -Gdx.graphics.getWidth()/2,0);
        SiliconTycoon.getInstance().batch.draw(grassRegion,  -Gdx.graphics.getWidth()/2,-Gdx.graphics.getHeight()/2);
        SiliconTycoon.getInstance().batch.draw(SiliconTycoon.getInstance().repository.logo,            -SiliconTycoon.getInstance().repository.logo.getWidth()/2, logoY);
        SiliconTycoon.getInstance().batch.draw(SiliconTycoon.getInstance().repository.play_button,     -SiliconTycoon.getInstance().repository.play_button.getWidth()/4,     -((logoYAnim*1.2f)), SiliconTycoon.getInstance().repository.play_button.getWidth()/2, SiliconTycoon.getInstance().repository.play_button.getHeight()/2);
        SiliconTycoon.getInstance().batch.draw(SiliconTycoon.getInstance().repository.continue_button, -SiliconTycoon.getInstance().repository.continue_button.getWidth()/4, -((logoYAnim*1.8f) + 50), SiliconTycoon.getInstance().repository.continue_button.getWidth()/2, SiliconTycoon.getInstance().repository.continue_button.getHeight()/2);
        SiliconTycoon.getInstance().batch.draw(SiliconTycoon.getInstance().repository.quit_button,     -SiliconTycoon.getInstance().repository.quit_button.getWidth()/4,     -((logoYAnim*2.4f) + 100), SiliconTycoon.getInstance().repository.quit_button.getWidth()/2, SiliconTycoon.getInstance().repository.quit_button.getHeight()/2);
        SiliconTycoon.getInstance().batch.end();

        x += 40f * delta;
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
