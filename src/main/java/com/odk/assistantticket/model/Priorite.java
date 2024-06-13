package com.odk.assistantticket.model;

import com.odk.assistantticket.enums.TypePriorite;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "priorite")
public class Priorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypePriorite name;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Utilisateur utilisateur;
}
