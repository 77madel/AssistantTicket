package com.odk.assistantticket.model;

import com.odk.assistantticket.enums.TypeCategorie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypeCategorie name;
    @ManyToOne(fetch = FetchType.LAZY)
    private Utilisateur utilisateur;
}
