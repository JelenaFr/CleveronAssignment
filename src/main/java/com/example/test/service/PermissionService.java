package com.example.test.service;


import com.example.test.model.*;
import com.example.test.repository.Level_1_Repository;
import com.example.test.repository.Level_2_Repository;
import com.example.test.repository.Level_3_Repository;
import com.example.test.repository.PermissionNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    @Autowired
    private Level_1_Repository level_1_repository;
    @Autowired
    private Level_2_Repository level_2_repository;
    @Autowired
    private Level_3_Repository level_3_repository;
    @Autowired
    private PermissionNameRepository permissionNameRepository;




    public List<Level1> findAllFirstLevelPermissions() {
        List<Level1> firstLevelList = level_1_repository.findAll();
        return firstLevelList;
    }

    public List<Level2> findAllSecondLevelPermissionByFirstLevelId(Long id) {
        List<Level2> secondLevelList = level_2_repository.findLevel2ByLevel1_Id(id);
        return secondLevelList;
    }

    public List<Level3> findAllThirdLevelPermissionBySecondLevelId(Long id) {
        List<Level3> thirdLevelList = level_3_repository.findLevel3ByLevel2_Id(id);
        return thirdLevelList;
    }

    public Level1 saveLevel1(Level1 newLevel) {
        PermissionName permissionName = new PermissionName(new NameGenerator().getRandomName());
        newLevel.setPermissionName(permissionName);
        permissionNameRepository.save(permissionName);
        return level_1_repository.save(newLevel);
    }

    public Level2 saveLevel2(Long id) {
        PermissionName permissionName = new PermissionName(new NameGenerator().getRandomName());
        Optional<Level1> level1 = level_1_repository.findById(id);
        Level2 newLevelName = new Level2(level1.orElseThrow());
        newLevelName.setPermissionName(permissionName);
        permissionNameRepository.save(permissionName);
        return level_2_repository.save(newLevelName);
    }

    public Level3 saveLevel3(Long id) {
        PermissionName permissionName = new PermissionName(new NameGenerator().getRandomName());
        Optional<Level2> level2 = level_2_repository.findById(id);
        Level3 newLevelName = new Level3(level2.orElseThrow().getLevel1(), level2.orElseThrow());
        newLevelName.setPermissionName(permissionName);
        permissionNameRepository.save(permissionName);
        return level_3_repository.save(newLevelName);
    }

    @Transactional
    public void deleteFirstLevel(Long id) {
        level_3_repository.deleteLevel3ByLevel1_id(id);
        level_2_repository.deleteLevel2ByLevel1_Id(id);
        level_1_repository.deleteById(id);
    }

    @Transactional
    public void deleteSecondLevel(Long id) {
        level_3_repository.deleteLevel3ByLevel2_Id(id);
        level_2_repository.deleteById(id);
    }

    @Transactional
    public void deleteThirdLevel(Long id) {
        level_3_repository.deleteById(id);

    }
}
