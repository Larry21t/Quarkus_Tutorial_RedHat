package com.redhat.developers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository //Repository ist das zwischen Entity und Resource
public interface SpringFruitRepository extends JpaRepository<Fruit, Long>{
    public List<Fruit> findBySeason(String season);
    public List<Fruit> findByName(String name);
}
