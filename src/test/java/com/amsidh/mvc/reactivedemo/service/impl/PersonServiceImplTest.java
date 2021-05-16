package com.amsidh.mvc.reactivedemo.service.impl;

import com.amsidh.mvc.reactivedemo.domain.Person;
import com.amsidh.mvc.reactivedemo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class PersonServiceImplTest {

    PersonService personService;

    @BeforeEach
    public void initial() {
        this.personService = new PersonServiceImpl();
    }

    @Test
    public void testMonoByPersonIdBlock() {
        Integer personId = 2;
        Mono<Person> personMono = this.personService.getPersonById(personId);
        Person person = personMono.block();
        System.out.println(person.toString());
    }

    @Test
    public void testMonoByPersonIdSubscribe() {
        Integer personId = 2;
        Mono<Person> personMono = this.personService.getPersonById(personId);
        personMono.subscribe(System.out::println);
    }

    @Test
    public void testMonoByPersonIdNext() {
        Integer personId = 2;
        Mono<Person> personMono = this.personService.getPersonById(personId);
        personMono.doOnNext(System.out::println);
    }

    @Test
    public void testMonoByPersonIdNotFound() {
        Integer personId = 10;
        Mono<Person> personMono = this.personService.getPersonById(personId).single();
        personMono.subscribe(person -> {
            System.out.println(person.toString());
        });
    }

    @Test
    public void testMonoByPersonIdNotFoundWithException() {
        Integer personId = 10;
        Mono<Person> personMono = this.personService.getPersonById(personId).single();
        personMono.subscribe(person -> {
            System.out.println(person.toString());
        });
    }

    @Test
    public void testFluxFindAllPersonNotFoundWithException() {
        Integer personId = 10;
        Mono<Person> personMono = this.personService.findAllPerson().filter(person -> person.getPersonId() == personId).single();
        personMono.subscribe(person -> {
            System.out.println(person.toString());
        });
    }

    @Test
    public void testFindAllPersonNotFoundWithCustomException() {
        Integer personId = 10;
        Mono<Person> personMono = this.personService.findAllPerson().filter(person -> person.getPersonId() == personId).single();
        personMono.doOnError(throwable -> System.out.println("Person not found with personId " + personId)).subscribe(person -> {
            System.out.println(person.toString());
        });
    }

    @Test
    public void testFindAllPersonNotFoundWithException() {
        Integer personId = 10;
        Mono<Person> personMono = this.personService.findAllPerson().filter(person -> person.getPersonId() == personId).single();
        personMono.doOnError(throwable -> System.out.println("Person not found with personId " + personId))
                .onErrorReturn(Person.builder().personId(personId).build())
                .subscribe(person -> {
                    System.out.println(person.toString());
                });
    }
}
