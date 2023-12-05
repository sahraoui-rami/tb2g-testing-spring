package org.springframework.samples.petclinic.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by Rami SAHRAOUI on 05/12/2023
 */
@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {

    @Mock
    PetRepository petRepository;

    @Mock
    VetRepository vetRepository;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    ClinicServiceImpl clinicService;

    @Test
    void findPetTypes() {
        // given
        List<PetType> petTypeList = new ArrayList<>();
        given(petRepository.findPetTypes()).willReturn(petTypeList);

        // when
        Collection<PetType> returnedPetTypes = clinicService.findPetTypes();

        // then
        then(petRepository).should().findPetTypes();
        assertThat(returnedPetTypes).isNotNull();
        verifyNoMoreInteractions(petRepository);
    }

    @Test
    void findOwnerById() {
        // given
        Owner owner = new Owner();
        given(ownerRepository.findById(anyInt())).willReturn(owner);

        // when
        Owner returnedOwner = clinicService.findOwnerById(1);

        // then
        then(ownerRepository).should().findById(anyInt());
        assertThat(returnedOwner).isNotNull();
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    void findOwnerByLastName() {
        // given
        List<Owner> owners = new ArrayList<>();
        given(ownerRepository.findByLastName(anyString())).willReturn(owners);

        // when
        Collection<Owner> returnedOwners = clinicService.findOwnerByLastName("Buck");

        // then
        then(ownerRepository).should().findByLastName(anyString());
        assertThat(returnedOwners).isNotNull();
        verifyNoMoreInteractions(ownerRepository);
    }

    @Test
    void findPetById() {
        // given
        Pet pet = new Pet();
        given(petRepository.findById(anyInt())).willReturn(pet);

        // when
        Pet returnedPet = clinicService.findPetById(1);

        // then
        then(petRepository).should().findById(anyInt());
        assertThat(returnedPet).isNotNull();
        verifyNoMoreInteractions(petRepository);
    }

    @Test
    void findVets() {
        // given
        List<Vet> vets = new ArrayList<>();
        given(vetRepository.findAll()).willReturn(vets);

        // when
        Collection<Vet> returnedVets = clinicService.findVets();

        // then
        then(vetRepository).should().findAll();
        assertThat(returnedVets).isNotNull();
        verifyNoMoreInteractions(vetRepository);
    }

    @Test
    void findVisitsByPetId() {
        // given
        List<Visit> visits = new ArrayList<>();
        given(visitRepository.findByPetId(anyInt())).willReturn(visits);

        // when
        Collection<Visit> returnedVisits = clinicService.findVisitsByPetId(1);

        // then
        then(visitRepository).should().findByPetId(anyInt());
        assertThat(returnedVisits).isNotNull();
        verifyNoMoreInteractions(visitRepository);
    }
}
