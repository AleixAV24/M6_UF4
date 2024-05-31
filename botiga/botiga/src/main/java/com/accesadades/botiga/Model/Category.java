package com.accesadades.botiga.Model;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // Esta anotación indica que esta clase es una entidad JPA
@Table(name = "category")// Esta anotación especifica el nombre de la tabla en la base de datos
public class Category {
    @Id // Indica que este campo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente valores para esta columna (identidad)
    private Long category_id;

    @Column // Esta anotación especifica que este campo se asignará a una columna en la tabla
    private String name;

    @Column(name = "creation_at") // Se especifica el nombre de la columna en la tabla para esta propiedad
    private LocalDateTime creationDate;

    @Column(name = "update_at") // Se especifica el nombre de la columna en la tabla para esta propiedad
    private LocalDateTime updateDate;
/*
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Subcategory> subcategories;
*/
}
