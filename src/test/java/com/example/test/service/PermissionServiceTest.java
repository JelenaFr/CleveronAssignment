package com.example.test.service;

import com.example.test.model.*;
import com.example.test.repository.Level_1_Repository;
import com.example.test.repository.Level_2_Repository;
import com.example.test.repository.Level_3_Repository;
import com.example.test.repository.PermissionNameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PermissionServiceTest {


    @InjectMocks
    private PermissionService permissionService;

    @Mock
    private Level_1_Repository level_1_repository;

    @Mock
    private Level_2_Repository level_2_repository;

    @Mock
    private Level_3_Repository level_3_repository;

    @Mock
    private PermissionNameRepository permissionNameRepository;


    @Test
    void findAllFirstLevelPermissions() {
        permissionService.findAllFirstLevelPermissions();
        Mockito.verify(level_1_repository).findAll();
    }

    @Test
    void findAllSecondLevelPermissionByFirstLevelId() {
        permissionService.findAllSecondLevelPermissionByFirstLevelId(1L);
        Mockito.verify(level_2_repository).findLevel2ByLevel1_Id(1L);

    }

    @Test
    void findAllThirdLevelPermissionBySecondLevelId() {
        permissionService.findAllThirdLevelPermissionBySecondLevelId(1L);
        Mockito.verify(level_3_repository).findLevel3ByLevel2_Id(1L);
    }


    @Test
    void saveLevel1() {
        Level1 newLevel = new Level1();
        PermissionName permissionName = new PermissionName(new NameGenerator().getRandomName());
        newLevel.setPermissionName(permissionName);
        permissionNameRepository.save(permissionName);
        level_1_repository.save(newLevel);
        Mockito.verify(permissionNameRepository).save(permissionName);
        Mockito.verify(level_1_repository).save(newLevel);

    }

    @Test
    void saveLevel2() {
        Level2 newLevel2 = new Level2();
        level_2_repository.save(newLevel2);
        Mockito.verify(level_2_repository).save(newLevel2);
    }

    @Test
    void saveLevel3() {
        Level3 newLevel3 = new Level3();
        level_3_repository.save(newLevel3);
        Mockito.verify(level_3_repository).save(newLevel3);
    }

    @Test
    void deleteFirstLevel() {
        permissionService.deleteFirstLevel(1L);
        Mockito.verify(level_1_repository).deleteById(1L);
    }

    @Test
    void deleteSecondLevel() {
        permissionService.deleteSecondLevel(1L);
        Mockito.verify(level_2_repository).deleteById(1L);
    }

    @Test
    void deleteThirdLevel() {
        permissionService.deleteThirdLevel(1L);
        Mockito.verify(level_3_repository).deleteById(1L);
    }

}
