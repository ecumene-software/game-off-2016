package ecu.silicon.models.advisors;

import com.badlogic.gdx.graphics.Texture;
import ecu.silicon.SiliconTycoon;

public class BuisnessAdvisor implements Advisor{
    @Override
    public String getName() {
        return "Smith";
    }

    @Override
    // The only place it's spelled right, get off my back??
    public String getType() {
        return "Business";
    }

    @Override
    public Texture getImage() {
        return SiliconTycoon.getInstance().repository.advisor_buisness;
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
