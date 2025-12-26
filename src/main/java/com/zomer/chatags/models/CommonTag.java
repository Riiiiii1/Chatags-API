package com.zomer.chatags.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "common_tags")
public class CommonTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String category; // Para agrupar despues si agrego front.
}
