package com.dhlk.bas.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by fnan on 2017-11-10.
 */
public class LightSettingControllerTest {

    @Autowired
    private LightController lightController;

    @Test
    public void updateLightState() throws Exception {

        lightController.lightState();

    }

}