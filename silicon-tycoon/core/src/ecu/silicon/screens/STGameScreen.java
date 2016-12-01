package ecu.silicon.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import ecu.silicon.STCamera;
import ecu.silicon.events.STEvent;
import ecu.silicon.gui.*;
import ecu.silicon.gui.tile.TileMapControl;
import ecu.silicon.gui.tile.TileMapRenderer;
import ecu.silicon.models.STSaveState;
import ecu.silicon.SiliconTycoon;
import ecu.silicon.models.tiles.TileData;
import ecu.silicon.models.tiles.TileMap;
import ecu.silicon.models.advisors.Advice;
import ecu.silicon.models.advisors.BuisnessAdvisor;
import ecu.silicon.models.advisors.LegalAdvisor;
import ecu.silicon.models.advisors.TechAdvisor;
import ecu.silicon.models.alerts.Alert;

public class STGameScreen implements Screen {

    private STCamera camera;

    private Stage gui;
    private AlertsWindow alertsWindow;
    private AlertsVisTable alertsGUI;
    private ConstructionWindow constructionWindow;
    private ConstructionVisTable constructionGUI;
    private TopBarActor bar;

    private STInputProcessor gameInput;
    private boolean          firstTime;

    private STSaveState state;

    private LegalAdvisor    legalAdvisor;
    private BuisnessAdvisor buisnessAdvisor;
    private TechAdvisor     techAdvisor;

    private Bus gameBus;

    private TileMapRenderer buildingMapRenderer;
    private Road road;
    private TileMapControl tileMapControl;

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

        constructionGUI = new ConstructionVisTable();
        constructionWindow = new ConstructionWindow("", new Vector2(), new Vector2(100, 100));
        constructionWindow.setWidth(200);
        constructionWindow.add(constructionGUI).grow().align(Align.top);
        constructionWindow.setKeepWithinParent(false);
        constructionWindow.setKeepWithinStage(false);

        gui.addActor(alertsWindow);
        gui.addActor(constructionWindow);

        bar = new TopBarActor(getState().saveName).setStyle(VisUI.getSkin().get("default", TopBarActor.TopBarStyle.class));
        gui.addActor(bar);

        gameInput = new STInputProcessor();

        closeAlerts();
        closeConstruction();
        alertsWindow.setVisible(false);
        constructionWindow.setVisible(false);

        techAdvisor = new TechAdvisor();
        buisnessAdvisor = new BuisnessAdvisor();
        legalAdvisor = new LegalAdvisor();

        techAdvisor.initSubscribe();
        buisnessAdvisor.initSubscribe();
        legalAdvisor.initSubscribe();

        road = new Road();

        tileMapControl = new TileMapControl(state.tileMap, buildingMapRenderer = new TileMapRenderer(state.tileMap), road);
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
        Gdx.gl.glClearColor(135f/255f, 206f/255f, 235f/255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SiliconTycoon.getInstance().batch.setProjectionMatrix(camera.combined);

        this.state.stepTime += delta * getState().stepMul;

        gameInput.onRender(delta);

        camera.zoom += (state.zoom - camera.zoom) * 8 * delta;
        camera.x += (state.cameraX - camera.x) * 12 * delta;
        camera.y += (state.cameraY - camera.y) * 12 * delta;

        camera.update();
        tileMapControl.render(SiliconTycoon.getInstance().batch);

        gui.act(delta);
        gui.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera = new STCamera(width, height);
        gui.getViewport().update(width, height, true);

        alertsWindow.setHeight(height * 0.8f);
        if(!alertsWindow.isDisabled())
            alertsWindow.setPosition(Gdx.graphics.getWidth()-alertsWindow.getWidth(), Gdx.graphics.getHeight()/2 - alertsWindow.getHeight()/2);
        constructionWindow.setHeight(height * 0.8f);
        if(!constructionWindow.isDisabled())
            constructionWindow.setPosition(0, Gdx.graphics.getHeight()/2 - constructionWindow.getHeight()/2);
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

    public void openConstruction(){
        constructionWindow.setStarting(new Vector2(-constructionWindow.getWidth(), Gdx.graphics.getHeight()/2 - constructionWindow.getHeight()/2));
        constructionWindow.setTarget(new Vector2(0, Gdx.graphics.getHeight()/2 - constructionWindow.getHeight()/2));
        constructionWindow.bounce();
        constructionWindow.setDisabled(false);
    }

    public void closeConstruction(){
        constructionWindow.setStarting(new Vector2(0, Gdx.graphics.getHeight()/2 - constructionWindow.getHeight()/2));
        constructionWindow.setTarget(new Vector2(-constructionWindow.getWidth(), Gdx.graphics.getHeight()/2 - constructionWindow.getHeight()/2));
        constructionWindow.bounce();
        constructionWindow.setDisabled(true);
    }

    public STSaveState getState() {
        return state;
    }

    public STCamera getCamera() {
        return camera;
    }

    public STInputProcessor getGameInput() {
        return gameInput;
    }

    public TileMapControl getTileMapControl() {
        return tileMapControl;
    }

    public Bus getBus() {
        return gameBus;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputMultiplexer(gameInput, gui, tileMapControl, buildingMapRenderer));
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

    public TileMapRenderer getTileRenderer() {
        return buildingMapRenderer;
    }

    @Override
    public void dispose() {
        techAdvisor.initUnsubscribe();
        buisnessAdvisor.initUnsubscribe();
        legalAdvisor.initUnsubscribe();

        try{
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public class STInputProcessor implements InputProcessor {
        private boolean up,down,left,right;

        @Override
        public boolean keyTyped(char character) {
            if(character == '\t'){
                if(!alertsWindow.isVisible()) alertsWindow.setVisible(true);
                if(alertsWindow.isDisabled()) openAlerts();
                else                          closeAlerts();
            }
            if(character == 't'){
                if(!constructionWindow.isVisible()) constructionWindow.setVisible(true);
                if(constructionWindow.isDisabled()) openConstruction();
                else                                closeConstruction();
            }
            if(character == ' ') bar.togglePause();
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            if(keycode == Input.Keys.W) up = false;
            if(keycode == Input.Keys.S) down = false;
            if(keycode == Input.Keys.D) left = false;
            if(keycode == Input.Keys.A) right = false;
            return false;
        }

        @Override
        public boolean keyDown(int keycode) {
            up    = keycode == Input.Keys.W;
            down  = keycode == Input.Keys.S;
            left  = keycode == Input.Keys.D;
            right = keycode == Input.Keys.A;
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            state.zoom += amount*0.10f;
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        public void onRender(float delta){
            float frameSpeed = 10;
            if(up)    state.cameraY -= frameSpeed;
            if(down)  state.cameraY += frameSpeed;
            if(right) state.cameraX += frameSpeed;
            if(left)  state.cameraX -= frameSpeed;
        }
    }
}
