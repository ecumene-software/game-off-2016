package ecu.silicon.models.advisors;

import com.badlogic.gdx.graphics.Texture;
import ecu.silicon.SiliconTycoon;

public class TechAdvisor implements Advisor {
    @Override
    public String getName() {
        return "Michael";
    }

    @Override
    public String getType() {
        return "Technology";
    }

    @Override
    public Texture getImage() {
        return SiliconTycoon.getInstance().repository.advisor_tech;
    }

    @Override
    public void initSubscribe() {

    }

    @Override
    public void initUnsubscribe() {

    }

    public void advise(String message){
        SiliconTycoon.getInstance().gameScreen.postAdvice(new Advice(this, message));
    }
}
