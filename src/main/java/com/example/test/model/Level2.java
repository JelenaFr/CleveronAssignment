package com.example.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table
public class Level2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "permissionName_id")
    private PermissionName permissionName;

    @ManyToOne()
    @JoinColumn(name = "level1_id")
    private Level1 level1;

    public Level2(Level1 level1) {
        this.level1 = level1;
    }

}
