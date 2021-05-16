package com.amsidh.mvc.reactivedemo.service.impl;

import com.amsidh.mvc.reactivedemo.domain.Person;
import com.amsidh.mvc.reactivedemo.service.PersonService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    private static final List<Person> persons = new ArrayList<>();

    static {
        persons.addAll(Arrays.asList(
                new Person(1, "Amsidh", "Lokhande", 37),
                new Person(2, "Anjali", "Lokhande", 33),
                new Person(3, "Aditya", "Lokhande", 7),
                new Person(4, "Adithi", "Lokhande", 10)
        ));
    }

    @Override
    public Mono<Person> getPersonById(Integer personId) {
        Person p = persons.parallelStream().filter(person -> person.getPersonId() == personId).findFirst().orElse(new Person());
        return Mono.just(p);
    }

    @Override
    public Flux<Person> findAllPerson() {
        return Flux.fromIterable(persons);
    }
}
