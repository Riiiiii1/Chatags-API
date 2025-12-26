package com.zomer.chatags.models;
import java.util.UUID;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "chat_rooms", uniqueConstraints = {
        // No se hace una fila con la misma pareja de usuarios
        @UniqueConstraint(columnNames = {"user_1_id", "user_2_id"})
})
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID seguro para la URL (ej: "a1b2-c3d4...")
    @Column(unique = true, nullable = false)
    private String publicId;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_1_id", nullable = false)
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user_2_id", nullable = false)
    private User user2;

    public ChatRoom() {
        this.publicId = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }
}
