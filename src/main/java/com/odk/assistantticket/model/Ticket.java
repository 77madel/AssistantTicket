package com.odk.assistantticket.model;


import com.odk.assistantticket.enums.TypeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.loadtime.definition.Definition;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Column(nullable = false, columnDefinition = "VARCHAR(20) DEFAULT 'EN_COURS'")
    private TypeStatus status;
    private Date createDate = new Date();
    private Date resoluDate = new Date();
    @OneToOne(cascade = CascadeType.ALL)
    private Categorie categorie;
    @OneToOne(cascade = CascadeType.ALL)
    private Priorite priorite;
    @ManyToOne(cascade = CascadeType.ALL)
    private Utilisateur utilisateur;
}
