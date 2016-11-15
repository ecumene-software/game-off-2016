package ecu.silicon.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisTable;
import ecu.silicon.STSaveState;
import ecu.silicon.SiliconTycoon;
import ecu.silicon.alerts.Alert;
import ecu.silicon.alerts.AlertsVisTable;
import ecu.silicon.gui.AlertsWindow;

import java.util.ArrayList;
import java.util.List;

public class STGameScreen implements Screen {

    private OrthographicCamera camera;
    private STSaveState state;

    private Stage gui;
    private AlertsWindow alertsWindow;
    private AlertsVisTable alertsGUI;

    private InputAdapter gameInput;

    public STGameScreen(STSaveState save){
        this.state = save;
        gui = new Stage(new ScreenViewport());

        alertsGUI = new AlertsVisTable();
        List<Alert> alerts = new ArrayList<Alert>();
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alerts.add(new Alert("Hey! Listen!"));
        alertsGUI.update(alerts);

        alertsWindow = new AlertsWindow("Alerts", new Vector2(), new Vector2(100, 100));
        alertsWindow.setWidth(200);
        VisScrollPane alertsScroll = new VisScrollPane(alertsGUI);
        alertsScroll.setForceScroll(false, true);
        alertsWindow.add(alertsScroll).growX().align(Align.top);
        alertsWindow.setKeepWithinParent(false);
        alertsWindow.setKeepWithinStage(false);

        gui.addActor(alertsWindow);

        gameInput = new InputAdapter(){
            @Override
            public boolean keyTyped(char character) {
                if(character == '\t'){
                    if(!alertsWindow.isVisible()) alertsWindow.setVisible(true);
                    if(alertsWindow.isDisabled()) openAlerts();
                    else                          closeAlerts();
                }
                return super.keyTyped(character);
            }
        };
        closeAlerts();
        alertsWindow.setVisible(false);
    }

    @Override
    public void render(float delta) {
        SiliconTycoon.getInstance().batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(135f/255f, 206f/255f, 235f/255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!state.isFromJSON()) {
            // TODO: Setup Quicksave
        } else {
            // TODO: Load from left off
        }

        gui.act(delta);
        gui.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera = new OrthographicCamera(width, height);
        camera.update();
        gui.getViewport().update(width, height, true);
        alertsWindow.setHeight(height * 0.8f);
        if(!alertsWindow.isDisabled())
            alertsWindow.setPosition(Gdx.graphics.getWidth()-alertsWindow.getWidth(), Gdx.graphics.getHeight()/2 - alertsWindow.getHeight()/2);
    }

    public void openAlerts(){
        alertsWindow.setStarting(new Vector2(Gdx.graphics.getWidth()+alertsWindow.getWidth(), Gdx.graphics.getHeight()/2 - alertsWindow.getHeight()/2));
        alertsWindow.setTarget(new Vector2(Gdx.graphics.getWidth()-alertsWindow.getWidth(), Gdx.graphics.getHeight()/2 - alertsWindow.getHeight()/2));
        alertsWindow.bounce();
        gui.setScrollFocus(alertsGUI);
        alertsWindow.setDisabled(false);
    }

    public void closeAlerts(){
        alertsWindow.setStarting(new Vector2(Gdx.graphics.getWidth()-alertsWindow.getWidth(), Gdx.graphics.getHeight()/2 - alertsWindow.getHeight()/2));
        alertsWindow.setTarget(new Vector2(Gdx.graphics.getWidth()+alertsWindow.getWidth(), Gdx.graphics.getHeight()/2 - alertsWindow.getHeight()/2));
        alertsWindow.bounce();
        alertsWindow.setDisabled(true);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputMultiplexer(gameInput, gui));
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
