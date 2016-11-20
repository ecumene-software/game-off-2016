package ecu.silicon.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import ecu.silicon.SiliconTycoon;

public class TopBarActor extends VisTable {
    public int money;
    public int time;
    public String buisnessName;
    public TopBarStyle style;

    private VisTable container;
    private VisLabel moneyLabel;

    private WeekdayDotImage t1,t2,t3,t4,t5;

    public TopBarActor(String buisnessName, int money, int time){
        this.buisnessName = buisnessName;
        this.money = money;
        this.time = time;
        moneyLabel = new VisLabel(money + "");

        VisTable moneyTable = new VisTable();
        moneyTable.add(new VisImage(SiliconTycoon.getInstance().repository.silicoin)).size(30,30).pad(0).padRight(10);
        moneyTable.add(moneyLabel);

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

        container.add(t1);
        container.add(t2);
        container.add(t3);
        container.add(t4);
        container.add(t5);
        add(container).align(Align.left).padLeft(10).padTop(-25).growX();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
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

    public int getMoney() {
        return money;
    }

    public int getTime() {
        return time;
    }

    public String getBuisnessName() {
        return buisnessName;
    }

    public void setBuisnessName(String buisnessName) {
        this.buisnessName = buisnessName;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setTime(int time) {
        this.time = time;
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
