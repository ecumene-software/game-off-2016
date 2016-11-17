package ecu.silicon.gui;

import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.*;
import ecu.silicon.models.advisors.Advice;

public class AdviceVisTable extends VisTable {
    public AdviceVisTable(Advice advice){
        update(advice);
    }

    public AdviceVisTable(){

    }

    public void update(Advice advice){
        clear();

        VisTable container = new VisTable();

        VisTextArea message = new VisTextArea(advice.message);
        message.setDisabled(true);
        VisScrollPane scrollPane = new VisScrollPane(message);
        scrollPane.setForceScroll(false, true);
        scrollPane.setFlickScroll(false);

        VisTable rightPanel = new VisTable();
        rightPanel.add(new VisLabel(advice.parent.getName() + ":")).align(Align.left).row();
        rightPanel.add(scrollPane).align(Align.left).grow();

        container.add(new VisImage(advice.parent.getImage())).size(advice.parent.getImage().getWidth()/2, advice.parent.getImage().getHeight()/2).padRight(20);
        container.addSeparator(true).padRight(20);
        container.add(rightPanel).grow();

        add(container).align(Align.left).grow();
    }
}
