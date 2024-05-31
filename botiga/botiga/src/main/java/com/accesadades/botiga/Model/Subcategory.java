package com.accesadades.botiga.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // Esta clase es una entidad JPA
@Table(name = "subcategory") // Nombre de la tabla en la base de datos
public class Subcategory {
    @Id // Indica que este campo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente valores para esta columna (identidad)
    private Long subcategory_id;

    @Column // Este campo se asignará a una columna en la tabla
    private String name;

    @Column(name = "creation_at") // Nombre de la columna en la tabla para esta propiedad
    private LocalDateTime creationDate;

    @Column(name = "update_at") // Nombre de la columna en la tabla para esta propiedad
    private LocalDateTime updateDate;

    @ManyToOne(cascade=CascadeType.PERSIST) // Relación Many-to-One con Category
    @JoinColumn(name = "category_id", nullable = false) // Columna en la tabla que representa la relación
    private Category category; // Referencia a la categoría asociada
}
