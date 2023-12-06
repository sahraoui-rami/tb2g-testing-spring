package org.springframework.samples.petclinic.sfg;

import org.springframework.stereotype.Component;

/**
 * Created by Rami SAHRAOUI on 06/12/2023
 */
@Component
public class YannyWordProducer implements WordProducer{
    @Override
    public String getWord() {
        return "Yanny";
    }
}
