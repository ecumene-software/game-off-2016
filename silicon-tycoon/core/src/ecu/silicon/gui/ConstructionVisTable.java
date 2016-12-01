package ecu.silicon.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisImageButton;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import ecu.silicon.SiliconTycoon;
import ecu.silicon.gui.tile.BuyableTile;
import ecu.silicon.models.tiles.TileData;

import java.util.ArrayList;
import java.util.List;

public class ConstructionVisTable extends VisTable {

    public List<BuyableTile> constructionTiles;
    public List<BuyableTile> enterpriseTiles;
    public List<BuyableTile> decorationTiles;

    public ConstructionVisTable(){
        super();
        setFillParent(true);
        HorizontalGroup group = new HorizontalGroup();

        constructionTiles = new ArrayList<BuyableTile>();
        enterpriseTiles   = new ArrayList<BuyableTile>();
        decorationTiles   = new ArrayList<BuyableTile>();

        constructionTiles.add(new BuyableTile(30, "Brick Wall", "(wall)") {
            @Override
            public void applyTo(TileData data) {
                data.constructionName = "na_wall_brick";
            }

            @Override
            public boolean shouldApply(TileData data) {
                return !data.constructionName.equals("na_wall_brick");
            }

            @Override
            public TextureRegion getConstructionPreview() {
                return SiliconTycoon.getInstance().repository.tile_store.na_brickWalls[10];
            }
        });

        constructionTiles.add(new BuyableTile(20, "Grey Wall", "(wall)") {
            @Override
            public void applyTo(TileData data) {
                data.constructionName = "na_wall_grey";
            }

            @Override
            public boolean shouldApply(TileData data) {
                return !data.constructionName.equals("na_wall_grey");
            }

            @Override
            public TextureRegion getConstructionPreview() {
                return SiliconTycoon.getInstance().repository.tile_store.na_greyWalls[10];
            }
        });
        constructionTiles.add(new BuyableTile(20, "Beige Wall", "(wall)") {
            @Override
            public void applyTo(TileData data) {
                data.constructionName = "na_wall_beige";
            }

            @Override
            public boolean shouldApply(TileData data) {
                return !data.constructionName.equals("na_wall_beige");
            }

            @Override
            public TextureRegion getConstructionPreview() {
                return SiliconTycoon.getInstance().repository.tile_store.na_beigeWalls[10];
            }
        });

        final VisImageButton tab1 = new VisImageButton(new VisImage(SiliconTycoon.getInstance().repository.construction).getDrawable());
        final VisImageButton tab2 = new VisImageButton(new VisImage(SiliconTycoon.getInstance().repository.enterprise).getDrawable());
        final VisImageButton tab3 = new VisImageButton(new VisImage(SiliconTycoon.getInstance().repository.decor).getDrawable());
        tab1.setFocusBorderEnabled(false);
        tab2.setFocusBorderEnabled(false);
        tab3.setFocusBorderEnabled(false);
        group.addActor(tab1);
        group.addActor(tab2);
        group.addActor(tab3);
        add(group).padTop(35);
        row();
        addSeparator();

        Stack content = new Stack();
        final VisTable content1 = new VisTable();
        content1.align(Align.topLeft);
        content1.add(new VisLabel("Construction")).row();
        content1.addSeparator();
        {
            for(final BuyableTile tile : constructionTiles){
                VisTable tileTable = new VisTable();
                VisTable textTable = new VisTable();
                textTable.add(new VisLabel(tile.name)).pad(0).align(Align.left).row();
                VisLabel typeLabel = new VisLabel(tile.type);
                typeLabel.setColor(0.5f, 0.5f, 0.5f, 1);
                textTable.add(typeLabel).pad(0).align(Align.left).row();
                textTable.add(new VisLabel(""+tile.price+"  ")).align(Align.right);
                textTable.add(new VisImage(SiliconTycoon.getInstance().repository.silicoin_small)).align(Align.left);
                textTable.add(new VisLabel(" / tile"));

                tileTable.add(new VisImage(tile.getConstructionPreview())).padRight(10);
                tileTable.add(textTable);

                tileTable.setBackground(VisUI.getSkin().getDrawable("scroll"));

                tileTable.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        System.out.println("Selected: " + tile.name);
                        SiliconTycoon.getInstance().gameScreen.getTileRenderer().selectedBuyable = tile;
                    }
                });

                tileTable.setTouchable(Touchable.enabled);
                content1.add(tileTable).align(Align.left).growX().row();
                content1.addSeparator();
            }
        }
        final VisTable content2 = new VisTable();
        content2.align(Align.topLeft);
        content2.add(new VisLabel("Enterprise")).row();
        content2.addSeparator();
        final VisTable content3 = new VisTable();
        content3.align(Align.topLeft);
        content3.add(new VisLabel("Decorations")).row();
        content3.addSeparator();

        content.addActor(content1);
        content.addActor(content2);
        content.addActor(content3);

        add(content).expand().fill();

        ChangeListener tab_listener = new ChangeListener(){
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                content1.setVisible(tab1.isChecked());
                content2.setVisible(tab2.isChecked());
                content3.setVisible(tab3.isChecked());
                validate();
            }
        };
        tab1.addListener(tab_listener);
        tab2.addListener(tab_listener);
        tab3.addListener(tab_listener);

        ButtonGroup tabs = new ButtonGroup();
        tabs.setMinCheckCount(1);
        tabs.setMaxCheckCount(1);
        tabs.add(tab1);
        tabs.add(tab2);
        tabs.add(tab3);
    }
}
