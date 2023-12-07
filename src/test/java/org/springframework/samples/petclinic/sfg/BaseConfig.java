package org.springframework.samples.petclinic.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Rami SAHRAOUI on 06/12/2023
 */
@Configuration
public class BaseConfig {
    @Bean
    HearingInterpreter hearingInterpreter(WordProducer wordProducer) {
        return new HearingInterpreter(wordProducer);
    }
}
