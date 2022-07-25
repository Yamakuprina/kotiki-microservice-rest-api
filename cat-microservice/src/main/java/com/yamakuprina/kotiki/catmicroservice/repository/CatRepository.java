package com.yamakuprina.kotiki.catmicroservice.repository;

import entities.Cat;
import entities.CatColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, String> {
    List<Cat> findByColor(CatColor color);

    List<Cat> findByColorAndOwnerId(CatColor color, String ownerId);

    List<Cat> findByOwnerId(String ownerId);
}
