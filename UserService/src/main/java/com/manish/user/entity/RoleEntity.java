package com.manish.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "role", unique = true)
    private String role;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccessEntity> accesses;
}
