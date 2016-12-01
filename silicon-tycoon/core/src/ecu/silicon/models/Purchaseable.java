package ecu.silicon.models;

import ecu.silicon.SiliconTycoon;

public abstract class Purchaseable {

    public Purchaseable() {
    }

    public abstract int getPrice();

    public void purchase(){
        SiliconTycoon.getInstance().gameScreen.getState().money -= getPrice();
    }
}
