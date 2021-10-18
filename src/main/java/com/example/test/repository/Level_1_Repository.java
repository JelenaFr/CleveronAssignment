package com.example.test.repository;

import com.example.test.model.Level1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Level_1_Repository extends JpaRepository <Level1, Long> {

    List<Level1> findAll ();

    @Override
    Optional<Level1> findById(Long aLong);


    @Override
    void deleteById (Long aLong);

}
