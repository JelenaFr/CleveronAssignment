package com.example.test.controller;

import com.example.test.model.Level1;
import com.example.test.model.Level2;
import com.example.test.model.Level3;
import com.example.test.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/start/")
    public ResponseEntity<List<Level1>> findAllFirstLevelPermissions() {
        return ResponseEntity.ok(permissionService.findAllFirstLevelPermissions());
    }
    @PostMapping("/new1/")
    public void createNewFirstLevelPermission(Level1 newLevel1) {
        permissionService.saveLevel1(newLevel1);
    }
    @PostMapping("/new2/{param}/")
    public void createNewSecondLevelPermission(@PathVariable("param") Long id) {
        permissionService.saveLevel2(id);
    }
    @PostMapping("/new3/{param}/")
    public void createNewThirdLevelPermission(@PathVariable("param") Long id) {
        permissionService.saveLevel3(id);
    }
    @GetMapping("/level2/{param}/")
    public ResponseEntity<List<Level2>> findAllSecondLevelsByFirstLevelParam(@PathVariable("param") Long id) {
        return ResponseEntity.ok(permissionService.findAllSecondLevelPermissionByFirstLevelId(id));
    }
    @GetMapping("/level3/{param}/")
    public ResponseEntity<List<Level3>> findAllThirdLevelsBySecondLevelParam(@PathVariable("param") Long id) {
        return ResponseEntity.ok(permissionService.findAllThirdLevelPermissionBySecondLevelId(id));
    }
    @Transactional
    @DeleteMapping("/delete1/{param}/")
    public void deleteFirstLevel(@PathVariable("param") Long id) {
        permissionService.deleteFirstLevel(id);
    }

    @Transactional
    @DeleteMapping("/delete2/{param}/")
    public void deleteSecondLevel(@PathVariable("param") Long id) {
        permissionService.deleteSecondLevel(id);
    }

    @Transactional
    @DeleteMapping("/delete3/{param}/")
    public void deleteThirdLevel(@PathVariable("param") Long id) {
        permissionService.deleteThirdLevel(id);
    }
}
