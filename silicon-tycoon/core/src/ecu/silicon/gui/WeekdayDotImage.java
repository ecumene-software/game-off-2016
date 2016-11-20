package ecu.silicon.gui;

import com.kotcrab.vis.ui.widget.VisImage;
import ecu.silicon.SiliconTycoon;

public class WeekdayDotImage extends VisImage {
    private static VisImage whiteDot;
    private static VisImage greyDot;

    public WeekdayDotImage() {
        if(whiteDot == null) whiteDot = new VisImage(SiliconTycoon.getInstance().repository.whitedot);
        if(greyDot  == null) greyDot  = new VisImage(SiliconTycoon.getInstance().repository.greydot);
    }

    public void white(){
        setDrawable(whiteDot.getDrawable());
    }
    public void grey(){
        setDrawable(greyDot.getDrawable());
    }
}
