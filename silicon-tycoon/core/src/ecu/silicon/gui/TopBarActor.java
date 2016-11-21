package ecu.silicon.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import ecu.silicon.SiliconTycoon;

public class TopBarActor extends VisTable {
    public String buisnessName;
    public TopBarStyle style;

    private VisTable container;
    private VisLabel moneyLabel;

    private WeekdayDotImage t1,t2,t3,t4,t5;
    private VisImage slowTime, pauseTime, quickTime, timeState;

    public TopBarActor(String buisnessName){
        this.buisnessName = buisnessName;
        moneyLabel = new VisLabel();

        timeState = new VisImage();
        slowTime  = new VisImage(SiliconTycoon.getInstance().repository.slow);
        pauseTime = new VisImage(SiliconTycoon.getInstance().repository.pause);
        quickTime = new VisImage(SiliconTycoon.getInstance().repository.quick);

        VisTable moneyTable = new VisTable();
        moneyTable.add(moneyLabel).pad(0).padRight(10);
        moneyTable.add(new VisImage(SiliconTycoon.getInstance().repository.silicoin)).size(30,30);

        container = new VisTable();
        container.add(new VisLabel(buisnessName)).padRight(10);
        container.add(moneyTable).align(Align.left).growX();

        t1 = new WeekdayDotImage();
        t1.white();

        t2 = new WeekdayDotImage();
        t2.white();

        t3 = new WeekdayDotImage();
        t3.white();

        t4 = new WeekdayDotImage();
        t4.white();

        t5 = new WeekdayDotImage();
        t5.white();

        timeState.setDrawable(slowTime.getDrawable());

        timeState.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                toggleTimeState();
            }
        });

        container.add(timeState);
        container.add(t1);
        container.add(t2);
        container.add(t3);
        container.add(t4);
        container.add(t5);
        add(container).align(Align.left).padLeft(10).padTop(-25).growX();
    }

    public void togglePause(){
        SiliconTycoon.getInstance().gameScreen.getState().pauseTime = !SiliconTycoon.getInstance().gameScreen.getState().pauseTime;
    }

    private void toggleTimeState(){
        SiliconTycoon.getInstance().gameScreen.getState().quickTime = !SiliconTycoon.getInstance().gameScreen.getState().quickTime;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(((int)SiliconTycoon.getInstance().gameScreen.getState().stepTime - 1) % 5 == 0) t1.white();
        else                                                                               t1.grey();
        if(((int)SiliconTycoon.getInstance().gameScreen.getState().stepTime - 2) % 5 == 0) t2.white();
        else                                                                               t2.grey();
        if(((int)SiliconTycoon.getInstance().gameScreen.getState().stepTime - 3) % 5 == 0) t3.white();
        else                                                                               t3.grey();
        if(((int)SiliconTycoon.getInstance().gameScreen.getState().stepTime - 4) % 5 == 0) t4.white();
        else                                                                               t4.grey();
        if(((int)SiliconTycoon.getInstance().gameScreen.getState().stepTime - 5) % 5 == 0) t5.white();
        else                                                                               t5.grey();

        moneyLabel.setText(SiliconTycoon.getInstance().gameScreen.getState().money + "");

        if(SiliconTycoon.getInstance().gameScreen.getState().quickTime) SiliconTycoon.getInstance().gameScreen.getState().stepMul = 3;
        else      SiliconTycoon.getInstance().gameScreen.getState().stepMul = 1;
        if(SiliconTycoon.getInstance().gameScreen.getState().pauseTime) SiliconTycoon.getInstance().gameScreen.getState().stepMul = 0;

        if(SiliconTycoon.getInstance().gameScreen.getState().quickTime)  timeState.setDrawable(quickTime.getDrawable());
        else       timeState.setDrawable(slowTime.getDrawable());
        if(SiliconTycoon.getInstance().gameScreen.getState().pauseTime) timeState.setDrawable(pauseTime.getDrawable());

        setBackground(style.background);
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        setWidth(Gdx.graphics.getWidth()); setHeight(35);
        setX(0); setY(Gdx.graphics.getHeight()-getHeight());
        super.draw(batch, parentAlpha);
    }

    public TopBarActor setStyle(TopBarStyle style) {
        this.style = style;
        return this;
    }

    public TopBarStyle getStyle() {
        return style;
    }

    public String getBuisnessName() {
        return buisnessName;
    }

    public void setBuisnessName(String buisnessName) {
        this.buisnessName = buisnessName;
    }

    static public class TopBarStyle {
        public Drawable background;
        public BitmapFont barFont;
        public Color barFontColor;
        public TopBarStyle(){
        }
        public TopBarStyle(Drawable background, BitmapFont barFont, Color barFontColor){
            this.background = background;
            this.barFont = barFont;
            this.barFontColor = barFontColor;
        }
    }
}
