package com.odk.assistantticket.model;


import com.odk.assistantticket.enums.TypeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private String status;
    private Date createDate = new Date();
    private Date resoluDate;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "priorite_id")
    private Priorite priorite;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "locked_by_id")
    private Utilisateur lockedBy;

}
