package com.backoffice.Search.service;


import com.backoffice.Search.exceptions.TransfertDuplicatedException;
import com.backoffice.Search.model.Transfert;

import java.util.List;

public interface TransfertService {

    Transfert save(Transfert transfert) throws TransfertDuplicatedException;
    List<Transfert> findAll();
    List<Transfert> findAllByClientId(Long clientId);
    List<Transfert> findAllByAgentId(Long clientId);
    List<Transfert> findAllByBeneficiaireId(Long beneficiaireId);
    List<Transfert> findAllByClientBanqueId(Long clientId);

    Transfert findByRef(String ref);
}
