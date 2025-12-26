package com.zomer.chatags.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "custom_tags")
public class CustomTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
