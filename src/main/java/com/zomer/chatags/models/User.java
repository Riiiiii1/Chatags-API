package com.zomer.chatags.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String avatar;
    // Para saber de donde vino el registro
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;
    private String providerId;

    // Crear una tabla intermediaria que maneja la relacion muchos a muchos.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_common_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    // Se usa un Set para evitar los duplicados
    private Set<CommonTag> commonTags = new HashSet<>();


    // Crear la relacion con user_id en tabla custom tags.
    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CustomTag> customTags;
}
