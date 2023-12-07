package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Rami SAHRAOUI on 06/12/2023
 */
@Profile("base-test")
@Configuration
public class YannyConfig {
    @Bean
    YannyWordProducer yannyWordProducer() {
        return new YannyWordProducer();
    }
}
