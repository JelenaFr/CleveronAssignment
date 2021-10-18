package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Level3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "permissionName_id")
    private PermissionName permissionName;

    @ManyToOne()
    @JoinColumn(name = "level1_id")
    private Level1 level1;

    @ManyToOne()
    @JoinColumn(name = "level2_id")
    private Level2 level2;

    public Level3(Long id, PermissionName permissionName) {
        this.id = id;
        this.permissionName = permissionName;
    }

    public Level3(Level1 level1, Level2 level2) {
        this.level1 = level1;
        this.level2 = level2;
    }
}
