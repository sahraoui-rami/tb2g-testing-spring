package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Rami SAHRAOUI on 07/12/2023
 */
@ActiveProfiles("yanny")
@SpringJUnitConfig(classes = HearingInterpreterActiveProfileTest.TestConfig.class)
class HearingInterpreterActiveProfileTest {

    @Configuration
    @ComponentScan("org.springframework.samples.petclinic.sfg")
    static class TestConfig {}

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHeard() {
        String word = hearingInterpreter.whatIHeard();

        assertEquals("Yanny", word);
    }
}
