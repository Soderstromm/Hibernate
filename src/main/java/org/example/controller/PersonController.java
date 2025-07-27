package org.example.controller;

import org.springframework.web.bind.annotation.*;
import org.example.entity.Person;
import org.example.entity.PersonId;
import org.example.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam String city) {
        return repository.findByCityOfLiving(city);
    }

    @GetMapping("/younger-than")
    public List<Person> getPersonsYoungerThan(@RequestParam int age) {
        return repository.findByIdAgeLessThanOrderByIdAgeAsc(age);
    }

    @GetMapping("/by-name")
    public Optional<Person> getPersonByNameAndSurname(
            @RequestParam String name,
            @RequestParam String surname) {
        return repository.findByIdNameAndIdSurname(name, surname);
    }

    @GetMapping
    public List<Person> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{name}/{surname}/{age}")
    public Person getById(
            @PathVariable String name,
            @PathVariable String surname,
            @PathVariable int age) {
        return repository.findById(new PersonId(name, surname, age))
                .orElseThrow(() -> new RuntimeException("Person not found"));
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return repository.save(person);
    }

    @DeleteMapping("/{name}/{surname}/{age}")
    public void delete(
            @PathVariable String name,
            @PathVariable String surname,
            @PathVariable int age) {
        repository.deleteById(new PersonId(name, surname, age));
    }
}
