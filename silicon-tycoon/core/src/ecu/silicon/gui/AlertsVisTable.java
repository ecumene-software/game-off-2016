package ecu.silicon.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import ecu.silicon.models.alerts.Alert;

import java.util.List;

/**
 * Proprietary VisUI Table for viewing alerts
 */
public class AlertsVisTable extends VisTable {

    public AlertsVisTable(List<Alert> alerts){
        update(alerts);
    }

    public AlertsVisTable(){

    }

    public void update(final List<Alert> alerts){
        clear();

        if(alerts.size() != 0) {
            addSeparator(false);
            for (final Alert alert : alerts) {
                final VisTable cell = new VisTable();

                VisLabel clickToDismiss = new VisLabel("Click to Dismiss");
                clickToDismiss.setColor(0.5f, 0.5f, 0.5f, 1);
                clickToDismiss.setFontScale(0.8f);

                cell.add(new VisLabel(alert.message)).row();
                cell.add(clickToDismiss).align(Align.left).row();
                cell.add().row();
                cell.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        alerts.remove(alert);
                        removeActor(cell);
                    }
                });
                cell.addSeparator(false);

                add(cell).growX().row();
            }
        }
    }
}
