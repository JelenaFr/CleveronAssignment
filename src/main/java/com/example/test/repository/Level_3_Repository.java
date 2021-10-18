package com.example.test.repository;

import com.example.test.model.Level3;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Level_3_Repository extends JpaRepository<Level3, Long> {
    List<Level3> findLevel3ByLevel2_Id(Long id);

    void deleteLevel3ByLevel2_Id(Long id);

    void deleteLevel3ByLevel1_id(Long id);
}
