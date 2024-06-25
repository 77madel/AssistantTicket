package com.odk.assistantticket.controller;

import com.odk.assistantticket.enums.TypeStatus;
import com.odk.assistantticket.model.Categorie;
import com.odk.assistantticket.model.Ticket;
import com.odk.assistantticket.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("ticket")
@Tag(name = "Ticket", description = "Géstion des tickets")
public class TicketController {

    private TicketService ticketService;

    @GetMapping
    @Operation(summary = "Liste des tickets", description = "Liste de tous les tickets")
    public Iterable<Ticket> AllTickets() {
        return this.ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Liste du ticket par Id", description = "Affichage des tickets par leur Id")
    public Optional<Ticket> TicketsById(@PathVariable long id) {
        return ticketService.getTicketsById(id);
    }

    @PostMapping
    @Operation(summary = "Créer un ticket", description = "Création des tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ticket crée avec succés"),
            @ApiResponse(responseCode = "400", description = "mauvaise requete ")
    })
    public void CreateTicket(@RequestBody Ticket ticket) {
        ticketService.insertTicket(ticket);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    @Operation(summary = "Modifier un ticket", description = "Modification d'un ticket par son Id")
    public ResponseEntity<Ticket> updateCategorie(@PathVariable Long id, @RequestBody Ticket updatedTicket) {
        Ticket ticket = ticketService.updateTicket(id, updatedTicket);
        if (ticket != null) {
            return ResponseEntity.ok(updatedTicket);
        }
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un ticket", description = "Suppression d'un ticket par son Id")
    public void deleteTicket(@PathVariable long id) {
        this.ticketService.supprimerTicket(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("traiter/{id}")
    @Operation(summary = "Traiter un ticket", description = "Traitement d'un ticket par son Id et par le Formateur")
    public void traiterTicket(@PathVariable Long id, @RequestParam("status") String status, @RequestParam("reponseContent") String reponseContent) {
        ticketService.traiterTicket(id, status, reponseContent);
    }
}
