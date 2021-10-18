package com.example.test.repository;


import com.example.test.model.PermissionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionNameRepository  extends JpaRepository <PermissionName, Long> {
}
