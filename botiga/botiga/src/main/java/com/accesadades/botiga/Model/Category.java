package com.accesadades.botiga.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id;
    @Column
    private String name;
    @Column(name = "creation_at")
    private LocalDateTime creationDate;
    @Column(name = "update_at")
    private LocalDateTime updateDate;
}
