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
@Table(name = "role-table")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "role", unique = true)
    private String role;
    @ElementCollection
    @CollectionTable(name = "role_paths", joinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "path")
    private List<String> path;
}
