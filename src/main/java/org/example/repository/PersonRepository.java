package org.example.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.example.entity.Person;
import org.example.entity.PersonId;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, PersonId> {

    // 1. Найти всех, кто живёт в городе
    List<Person> findByCityOfLiving(String city);

    // 2. Найти всех младше заданного возраста, отсортированных по возрастанию
    List<Person> findByIdAgeLessThanOrderByIdAgeAsc(int age);

    // 3. Найти по имени и фамилии\
    Optional<Person> findByIdNameAndIdSurname(String name, String surname);

    // 4. Пример кастомного запроса через @Query\
    @Query("SELECT p FROM Person p WHERE LOWER(p.cityOfLiving) = LOWER(:city)")
    List<Person> getPersonsByCityCaseInsensitive(@Param("city") String city);
}
