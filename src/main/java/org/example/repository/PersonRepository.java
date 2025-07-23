package org.example.repository;

import org.springframework.stereotype.Repository;
import org.example.entity.Person;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> getPersonsByCity(String city) {
        String jpql = "SELECT p FROM Person p WHERE LOWER(p.cityOfLiving) = LOWER(:city)";
        TypedQuery<Person> query = entityManager.createQuery(jpql, Person.class);
        query.setParameter("city", city);
        return query.getResultList();
    }

    public List<Person> getPersonsOlderThan(int age) {
        String jpql = "SELECT p FROM Person p WHERE p.id.age > :age ORDER BY p.id.age DESC";
        TypedQuery<Person> query = entityManager.createQuery(jpql, Person.class);
        query.setParameter("age", age);
        return query.getResultList();
    }
}