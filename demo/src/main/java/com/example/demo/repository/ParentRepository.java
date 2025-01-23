package com.example.demo.repository;

import com.example.demo.entity.Parent;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query("SELECT p FROM Parent p JOIN FETCH p.children")
    List<Parent> findAllWithChildren();

    @EntityGraph(value = "Parent.children", type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT p FROM Parent p")
    List<Parent> findAllWithEntityGraph();
}
