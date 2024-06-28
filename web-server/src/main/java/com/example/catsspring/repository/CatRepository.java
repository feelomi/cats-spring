package com.example.catsspring.repository;

import com.example.catsspring.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
}
