package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository repository;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test Find All")
    @Test
    void findAll() {
        //given
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        given(repository.findAll()).willReturn(visits);

        //when
        Set<Visit> foundVisits = service.findAll();

        //then
        assertThat(foundVisits).hasSize(1);
        then(repository).should().findAll();
    }

    @DisplayName("Test Find Visit by Id")
    @Test
    void findById() {
        //given
        Visit visit = new Visit();
        given(repository.findById(anyLong())).willReturn(Optional.of(visit));

        //when
        Visit foundVisit = service.findById(5L);

        //then
        assertThat(foundVisit).isNotNull();
        then(repository).should().findById(anyLong());
    }

    @DisplayName("Test Save Visit")
    @Test
    void save() {
        //given
        Visit visit = new Visit();
        given(repository.save(any(Visit.class))).willReturn(visit);

        //when
        Visit savedVisit = service.save(visit);

        //then
        assertThat(savedVisit).isNotNull();
        then(repository).should().save(any(Visit.class));
    }

    @DisplayName("Test Delete Visit")
    @Test
    void delete() {
        //given
        Visit visit = new Visit();

        //when
        service.delete(visit);

        //then
        then(repository).should().delete(any(Visit.class));
    }

    @DisplayName("Test Delete Visit By Id")
    @Test
    void deleteById() {
        //given - none

        //when
        service.deleteById(123L);

        //then
        then(repository).should().deleteById(anyLong());
    }

}