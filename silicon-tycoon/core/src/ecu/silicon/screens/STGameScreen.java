package ecu.silicon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import ecu.silicon.events.STEvent;
import ecu.silicon.gui.*;
import ecu.silicon.models.ITile;
import ecu.silicon.models.STSaveState;
import ecu.silicon.SiliconTycoon;
import ecu.silicon.models.TileMap;
import ecu.silicon.models.advisors.Advice;
import ecu.silicon.models.advisors.BuisnessAdvisor;
import ecu.silicon.models.advisors.LegalAdvisor;
import ecu.silicon.models.advisors.TechAdvisor;
import ecu.silicon.models.alerts.Alert;

public class STGameScreen implements Screen {

    private OrthographicCamera camera;

    private Stage gui;
    private AlertsWindow alertsWindow;
    private AlertsVisTable alertsGUI;

    private InputAdapter gameInput;
    private boolean      firstTime;

    private STSaveState state;

    private LegalAdvisor    legalAdvisor;
    private BuisnessAdvisor buisnessAdvisor;
    private TechAdvisor     techAdvisor;

    private Bus gameBus;

    private TileMap buildingMap;

    private Road road;

    public STGameScreen(STSaveState save, boolean firstTime) {
        this.state = save;
        gameBus = new Bus(ThreadEnforcer.ANY);

        this.firstTime = firstTime;
        gui = new Stage(new ScreenViewport());

        alertsGUI = new AlertsVisTable();
        alertsGUI.update(state.alerts);

        alertsWindow = new AlertsWindow("Alerts", new Vector2(), new Vector2(100, 100));
        alertsWindow.setWidth(200);
        VisScrollPane alertsScroll = new VisScrollPane(alertsGUI);
        alertsScroll.setForceScroll(false, true);
        alertsWindow.add(alertsScroll).growX().align(Align.top);
        alertsWindow.setKeepWithinParent(false);
        alertsWindow.setKeepWithinStage(false);

        gui.addActor(alertsWindow);

        final TopBarActor bar = new TopBarActor(getState().saveName).setStyle(VisUI.getSkin().get("default", TopBarActor.TopBarStyle.class));
        gui.addActor(bar);

        gameInput = new InputAdapter(){
            @Override
            public boolean keyTyped(char character) {
                if(character == '\t'){
                    if(!alertsWindow.isVisible()) alertsWindow.setVisible(true);
                    if(alertsWindow.isDisabled()) openAlerts();
                    else                          closeAlerts();
                }
                if(character == ' ') bar.togglePause();
                return super.keyTyped(character);
            }
        };
        closeAlerts();
        alertsWindow.setVisible(false);

        techAdvisor = new TechAdvisor();
        buisnessAdvisor = new BuisnessAdvisor();
        legalAdvisor = new LegalAdvisor();

        techAdvisor.initSubscribe();
        buisnessAdvisor.initSubscribe();
        legalAdvisor.initSubscribe();

        buildingMap = new TileMap(10,10);

        for(int x = 0; x < 10; x++){
            for(int y = 0; y < 10; y++){
                buildingMap.setAt(x, y, new ITile() {
                    @Override
                    public Texture getTexture() {
                        return SiliconTycoon.getInstance().repository.temp_tile_test;
                    }
                });
            }
        }

        road = new Road();
    }

    public void postConstruct(){
        if(firstTime) {
            getTechAdvisor().advise("Greetings! I scope out the competition, and give you advice on tech stuff. I must say I'm very excited to get this thing going!");
            getLegalAdvisor().advise("Hello, I'll let you know if you get into any trouble. I'm here to help... At a price, of course.");
            getBuisnessAdvisor().advise("Hya. Im your assistant for financial, commerce, and construction conflicts. You'll hear from me when you need me.");
        }
        gameBus.post(new STEvent("POST_CONSTRUCT_GAME"));
    }

    public void postAlert(Alert alert){
        state.alerts.add(alert);
        alertsGUI.update(state.alerts);
        openAlerts();
        gameBus.post(alert);
    }

    public void postAdvice(Advice advice){
        VisLabel escToClose = new VisLabel("Press escape to close");
        escToClose.setColor(0.5f, 0.5f, 0.5f, 0.5f);
        escToClose.setFontScale(1f);

        AdviceWindow adviceWindow = new AdviceWindow("Advice from your " + advice.parent.getType() + " advisor:", new Vector2(), new Vector2(100, 100));
        adviceWindow.add(new AdviceVisTable(advice)).grow().row();
        adviceWindow.add(escToClose);
        adviceWindow.setKeepWithinParent(false);
        adviceWindow.setKeepWithinStage(false);
        adviceWindow.addCloseButton();
        adviceWindow.closeOnEscape();
        adviceWindow.setSize(450, 200);

        gui.addActor(adviceWindow);
        gameBus.post(advice);
    }

    @Override
    public void render(float delta) {
        SiliconTycoon.getInstance().batch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClearColor(135f/255f, 206f/255f, 235f/255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.state.stepTime += delta * getState().stepMul;

        gui.act(delta);

        int tileScale = 30;
        int tileSizeX = (buildingMap.getTiles().length*tileScale);
        int tileSizeY = (buildingMap.getTiles()[0].length*tileScale);

        buildingMap.render(SiliconTycoon.getInstance().batch, tileScale, -tileSizeX/2, -tileSizeY/2);

        road.render(SiliconTycoon.getInstance().batch, -(tileSizeX)/2 - road.getHeight(), -(tileSizeY/2) - road.getHeight(), (tileSizeX) + road.getHeight(), (tileSizeY) + road.getHeight());

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

    public STSaveState getState() {
        return state;
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

    public BuisnessAdvisor getBuisnessAdvisor() {
        return buisnessAdvisor;
    }

    public LegalAdvisor getLegalAdvisor() {
        return legalAdvisor;
    }

    public TechAdvisor getTechAdvisor() {
        return techAdvisor;
    }

    @Override
    public void dispose() {
        techAdvisor.initUnsubscribe();
        buisnessAdvisor.initUnsubscribe();
        legalAdvisor.initUnsubscribe();

        try{
            STSaveState.save(state);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
