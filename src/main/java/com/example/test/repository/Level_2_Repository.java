package com.example.test.repository;

import com.example.test.model.Level2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Level_2_Repository extends JpaRepository<Level2, Long> {

    List<Level2> findLevel2ByLevel1_Id(Long id);
    void deleteLevel2ByLevel1_Id(Long id);

}
