package com.example.test.controller;

import com.example.test.model.Level1;
import com.example.test.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PermissionControllerTest {

    @InjectMocks
    private PermissionController permissionController;
    @Mock
    private PermissionService permissionService;

    @Test
    void createNewFirstLevelPermissionTest() {
        permissionController.createNewFirstLevelPermission(new Level1());
        Mockito.verify(permissionService).saveLevel1(new Level1());
    }

    @Test
    void findAllSecondLevelsByFirstLevelParamTest() {
        permissionController.findAllSecondLevelsByFirstLevelParam(1L);
        Mockito.verify(permissionService).findAllSecondLevelPermissionByFirstLevelId(1L);
    }

    @Test
    void findAllThirdLevelsBySecondLevelParam() {
        permissionController.findAllThirdLevelsBySecondLevelParam(2L);
        Mockito.verify(permissionService).findAllThirdLevelPermissionBySecondLevelId(2L);
    }
}
