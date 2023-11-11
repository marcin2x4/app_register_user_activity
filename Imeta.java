package com.apporders;
import java.util.Random;

public interface Imeta {

    Random rand = new Random();
    int runid = rand.nextInt(50);

    default String showMetaData() {
        return "app created by marcin2x4, your run_id: " + runid;
    }
}
