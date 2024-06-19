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
    private TypeStatus status;
    private Date createDate = new Date();
    private Date resoluDate;
    @OneToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
    @OneToOne
    @JoinColumn(name = "priorite_id")
    private Priorite priorite;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;
}
