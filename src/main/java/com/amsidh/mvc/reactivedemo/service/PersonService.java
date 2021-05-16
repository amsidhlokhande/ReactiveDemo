package com.amsidh.mvc.reactivedemo.service;

import com.amsidh.mvc.reactivedemo.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

    Mono<Person> getPersonById(Integer personId);

    Flux<Person> findAllPerson();
}
