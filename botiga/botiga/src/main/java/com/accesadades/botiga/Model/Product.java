package com.accesadades.botiga.Model;

import lombok.*;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // Esta clase es una entidad JPA
@Table(name = "products") // Nombre de la tabla en la base de datos
public class Product implements Serializable {
    @Id // Indica que este campo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente valores para esta columna (identidad)
    private long product_id;

    @Column // Este campo se asignará a una columna en la tabla
    private String name;

    @Column // Este campo se asignará a una columna en la tabla
    private String description;

    @Column // Este campo se asignará a una columna en la tabla
    private String company;

    @Column // Este campo se asignará a una columna en la tabla
    private float price;

    @Column // Este campo se asignará a una columna en la tabla
    private long units;

    @Column(name = "creation_at") // Nombre de la columna en la tabla para esta propiedad
    private LocalDateTime creationDate;

    @Column(name = "updated_at") // Nombre de la columna en la tabla para esta propiedad
    private LocalDateTime updateDate;

    @ManyToOne(cascade=CascadeType.PERSIST) // Relación Many-to-One con Subcategory
    @JoinColumn(name = "subcategory_id", nullable = false) // Columna en la tabla que representa la relación
    private Subcategory subcategory; // Referencia a la subcategoría asociada
}
