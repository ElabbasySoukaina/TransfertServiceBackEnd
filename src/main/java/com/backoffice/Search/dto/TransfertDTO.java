package com.backoffice.Search.dto;

import com.backoffice.Search.enums.State;
import lombok.Data;


@Data
public class TransfertDTO {
    protected Long id;
    protected double montant;
    protected String ref;
    protected double commission;
    protected String motif;
    protected Long idEmetteur;
    protected Long idClient;
    protected Long idBeneficiaire;
    protected State etat = State.A_SERVIR;
    protected Long idEmetteurServ;
    protected int PIN;
}
