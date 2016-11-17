package ecu.silicon.models.advisors;

import com.badlogic.gdx.graphics.Texture;
import ecu.silicon.SiliconTycoon;

public class LegalAdvisor implements Advisor {
    @Override
    public String getName() {
        return "Anderson";
    }

    @Override
    public String getType() {
        return "Legal";
    }

    @Override
    public Texture getImage() {
        return SiliconTycoon.getInstance().repository.advisor_legal;
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
