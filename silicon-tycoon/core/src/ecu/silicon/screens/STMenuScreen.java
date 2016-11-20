package ecu.silicon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.*;
import ecu.silicon.SiliconTycoon;
import ecu.silicon.gui.CenterVisWindow;
import ecu.silicon.gui.CloseListener;
import ecu.silicon.models.STSaveState;

import java.io.File;
import java.io.IOException;

public class STMenuScreen implements Screen {

    private OrthographicCamera camera;
    private InputProcessor     input;

    private float logoY = 800;
    private float x;

    private TextureRegion serverRegion   = new TextureRegion(SiliconTycoon.getInstance().repository.server_background);
    private TextureRegion grassRegion    = new TextureRegion(SiliconTycoon.getInstance().repository.buildings_background);

    private float playTarget  = 0;
    private float savesTarget = 0;
    private float quitTarget  = 0;

    private float playY  = 0;
    private float savesY = 0;
    private float quitY  = 0;

    private Stage stage;
    private boolean visUIOpen = false;

    private FileHandle savesDirectory = new FileHandle("saves/");

    public STMenuScreen() {
        stage = new Stage(new ScreenViewport());
        input = new InputProcessor() {
            private int in = 0;

            @Override public boolean keyDown(int keycode) {return false;}
            @Override public boolean keyUp(int keycode) {return false;}
            @Override public boolean keyTyped(char character) {return false;}
            @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}
            @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if(!visUIOpen){
                    switch (in){
                        case 1:{
                            final VisTextField username = new VisTextField();
                            final VisTextField saveName = new VisTextField();
                            VisTextButton createButton = new VisTextButton("Create");

                            createButton.addListener(new ClickListener(){
                                @Override
                                public void clicked(InputEvent event, float x, float y) {
                                    super.clicked(event, x, y);
                                    STSaveState newState = new STSaveState(username.getText(), saveName.getText()).timestamp();
                                    openGame(newState, true);
                                }
                            });
                            CenterVisWindow usernameWindow = new CenterVisWindow("New Game");
                            usernameWindow.add(new VisLabel("username:"));
                            usernameWindow.add(username).row();
                            usernameWindow.add(new VisLabel("save location:"));
                            usernameWindow.add(saveName).row();
                            usernameWindow.add(createButton);
                            usernameWindow.setSize(300, 200);
                            usernameWindow.closeOnEscape();
                            usernameWindow.addCloseButton();
                            usernameWindow.setCloseListener(new CloseListener() {
                                @Override
                                public void onClose() {
                                    visUIOpen = false;
                                }
                            });
                            stage.addActor(usernameWindow);
                            visUIOpen = true;
                        } break;
                        case 2:openSaves(); break;
                        case 3:Gdx.app.exit(); break;
                    }
                }
                return false;
            }
            @Override public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}
            @Override public boolean mouseMoved(int screenX, int screenY) {
                in = 0;

                float originY = screenY - (Gdx.graphics.getHeight())/2;
                float originX = screenX - (Gdx.graphics.getWidth())/2;
                if(originX <= 105  && originX >= -75){
                    if(originY <= -5  && originY >= -55){ in = 1;playTarget = 10;  } else { playTarget = 0;  }
                    if(originY <= 50  && originY >=   5){ in = 2;savesTarget = 10; } else { savesTarget = 0; }
                    if(originY <= 105 && originY >=  55){ in = 3;quitTarget = 10;  } else { quitTarget = 0;  }
                } else {
                    playTarget = 0;
                    savesTarget = 0;
                    quitTarget = 0;
                }
                return false;
            }

            @Override public boolean scrolled(int amount) {return false;}
        };
        Gdx.input.setInputProcessor(new InputMultiplexer(getInputProcessor(), stage));
    }

    private void openGame(STSaveState state, boolean firstTime){
        SiliconTycoon.getInstance().setScreen(SiliconTycoon.getInstance().gameScreen = new STGameScreen(state.timestamp(), firstTime));
        SiliconTycoon.getInstance().gameScreen.postConstruct();
    }

    @Override
    public void render(float delta) {
        SiliconTycoon.getInstance().batch.setProjectionMatrix(camera.combined);
        stage.act(delta);

        Gdx.gl.glClearColor(135f/255f, 206f/255f, 235f/255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        logoY += ((-SiliconTycoon.getInstance().repository.logo.getHeight()/2 + 150) - logoY) * 3f * delta;
        float logoYAnim = logoY - (-SiliconTycoon.getInstance().repository.logo.getHeight()/2 + 150);
        playY  += (playTarget  - playY) * 8f * delta;
        quitY  += (quitTarget  - quitY) * 8f * delta;
        savesY += (savesTarget - savesY) * 8f * delta;

        serverRegion.setRegionWidth(Gdx.graphics.getWidth());
        grassRegion.setRegionWidth(Gdx.graphics.getWidth());
        serverRegion.setRegionX((int)( x - (logoY/4)));
        grassRegion.setRegionX((int) (-x + (logoY/4)));

        SiliconTycoon.getInstance().batch.begin();
        SiliconTycoon.getInstance().batch.draw(serverRegion, -Gdx.graphics.getWidth()/2,0);
        SiliconTycoon.getInstance().batch.draw(grassRegion,  -Gdx.graphics.getWidth()/2,-Gdx.graphics.getHeight()/2);
        SiliconTycoon.getInstance().batch.draw(SiliconTycoon.getInstance().repository.logo,         -SiliconTycoon.getInstance().repository.logo.getWidth()/2, logoY);
        SiliconTycoon.getInstance().batch.draw(SiliconTycoon.getInstance().repository.play_button,  -SiliconTycoon.getInstance().repository.play_button.getWidth()/4 + playY,  -((logoYAnim*1.2f)),       SiliconTycoon.getInstance().repository.play_button.getWidth()/2, SiliconTycoon.getInstance().repository.play_button.getHeight()/2);
        SiliconTycoon.getInstance().batch.draw(SiliconTycoon.getInstance().repository.saves_button, -SiliconTycoon.getInstance().repository.saves_button.getWidth()/4+ savesY, -((logoYAnim*1.8f) + 50),  SiliconTycoon.getInstance().repository.saves_button.getWidth()/2, SiliconTycoon.getInstance().repository.saves_button.getHeight()/2);
        SiliconTycoon.getInstance().batch.draw(SiliconTycoon.getInstance().repository.quit_button,  -SiliconTycoon.getInstance().repository.quit_button.getWidth()/4 + quitY,  -((logoYAnim*2.4f) + 100), SiliconTycoon.getInstance().repository.quit_button.getWidth()/2, SiliconTycoon.getInstance().repository.quit_button.getHeight()/2);
        SiliconTycoon.getInstance().batch.end();

        stage.draw();
        x += 40f * delta;
    }

    public void openSaves() {
        visUIOpen = true;
        final VisWindow savesWindow = new VisWindow("Saves");
        savesWindow.setWidth(350);
        savesWindow.setHeight(500);
        savesWindow.setX(Gdx.graphics.getWidth()/2 - savesWindow.getWidth()/2);
        savesWindow.setY(Gdx.graphics.getHeight()/2 - savesWindow.getHeight()/2);

        VisTable windowTable = new VisTable();
        VisTextButton closeWindow = new VisTextButton("Close");
        closeWindow.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                savesWindow.fadeOut();
                visUIOpen = false;
            }
        });

        windowTable.addSeparator();
        FileHandle saves = new FileHandle(SiliconTycoon.getInstance().gameDirectory + "saves/");
        for(final FileHandle save : saves.list()){
            VisTextButton play = new VisTextButton("Play");
            play.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    try{
                        openGame(STSaveState.fromJSON(new File(SiliconTycoon.getInstance().gameDirectory + "saves/" + save.name())), false);
                    } catch (IOException exception){
                        exception.printStackTrace();;
                    }
                }
            });
            VisTable cell = new VisTable();
            cell.add(play);
            cell.add(new VisLabel(save.name())).align(Align.left).growX();

            windowTable.add(cell).growX().row();
            windowTable.addSeparator();
        }

        windowTable.add(closeWindow);
        savesWindow.add(windowTable);
        stage.addActor(savesWindow);
    }

    public InputProcessor getInputProcessor(){
        return input;
    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(width, height);
        camera.update();
        stage.getViewport().update(width, height, true);
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
