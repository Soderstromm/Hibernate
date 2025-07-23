package org.example.controller;

import org.springframework.web.bind.annotation.*;
import org.example.entity.Person;
import org.example.repository.PersonRepository;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    // GET /persons/by-city?city=Moscow
    @GetMapping("/by-city")
    public List<Person> getPersonsByCity(@RequestParam String city) {
        return repository.getPersonsByCity(city);
    }

    // Дополнительно: получить всех старше N лет
    @GetMapping("/older-than")
    public List<Person> getPersonsOlderThan(@RequestParam(defaultValue = "27") int age) {
        return repository.getPersonsOlderThan(age);
    }
}