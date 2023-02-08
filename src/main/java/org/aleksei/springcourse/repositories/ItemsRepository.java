package org.aleksei.springcourse.repositories;

import org.aleksei.springcourse.models.Item;
import org.aleksei.springcourse.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
    List<Item> findByItemName(String itemName);
    List<Item> findByOwner(Person owner);
}
