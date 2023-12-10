package org.springframework.samples.petclinic.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Rami SAHRAOUI on 09/12/2023
 */
@ExtendWith(MockitoExtension.class)
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {
    @Autowired
    OwnerController ownerController;

    @Autowired
    ClinicService clinicService;

    MockMvc mockMvc;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void processFindFormNotFound() throws Exception {
        // clinicService mock doesn't return any owner.
        mockMvc.perform(get("/owners")
                        .param("lastName", "Don't find ME")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void processFindFormOneOwner() throws Exception {
        // given
        Owner owner = new Owner();
        owner.setId(1);
        String findJustOneOwner = "Find Just One Owner";
        owner.setLastName(findJustOneOwner);
        given(clinicService.findOwnerByLastName(findJustOneOwner))
                .willReturn(List.of(owner));

        // when
        mockMvc.perform(get("/owners")
                        .param("lastName", findJustOneOwner)
                )// then
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        then(clinicService).should().findOwnerByLastName(anyString());
    }

    @Test
    void processFindFormListOfOwners() throws Exception {
        // given
        given(clinicService.findOwnerByLastName(anyString()))
                .willReturn(Lists.newArrayList(new Owner(), new Owner()));

        // when
        mockMvc.perform(get("/owners")
                ) // then
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"));

        then(clinicService).should()
                .findOwnerByLastName(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue()).isEmpty();
    }

    @Test
    void processCreationFormValid() throws Exception {
        mockMvc.perform(
                post("/owners/new")
                        .param("firstName", "Jimmy")
                        .param("lastName", "Buffett")
                        .param("address", "123 Duval St")
                        .param("city", "Key West")
                        .param("telephone", "3151231234")
        )
                .andExpect(status().is3xxRedirection());

        then(clinicService).should().saveOwner(any(Owner.class));
    }

    @AfterEach
    void tearDown() {
        reset(clinicService);
    }
}