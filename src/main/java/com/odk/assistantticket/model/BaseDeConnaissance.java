package com.odk.assistantticket.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "basedeconnaissance")
public class BaseDeConnaissance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String link;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    private Utilisateur utilisateur;
}
