package com.backoffice.Search.model;

import com.backoffice.Search.enums.State;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;




@Data
@Entity
public class Transfert {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    protected String ref;
    protected double montant;
    protected double commission;
    protected String motif;
    protected Long idEmetteur;
    protected Long idClient;
    protected Long idBeneficiaire;
    protected State etat = State.A_SERVIR;
    protected Long idEmetteurServ;
    protected int PIN;
}
