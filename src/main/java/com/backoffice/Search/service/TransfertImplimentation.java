package com.backoffice.Search.service;

import com.backoffice.Search.exceptions.TransfertDuplicatedException;
import com.backoffice.Search.repository.TransfertRepository;
import lombok.Data;
import com.backoffice.Search.enums.State;
import com.backoffice.Search.model.Transfert;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class TransfertImplimentation implements TransfertService {

    final TransfertRepository transfertRepository;

    @Override
    public Transfert save(Transfert transfert) throws TransfertDuplicatedException {
        Transfert transfertFromDB = transfertRepository.findById(transfert.getId()).orElse(null);
        if (transfertFromDB != null)
            throw new TransfertDuplicatedException(transfert.getId());
        Long id = transfertRepository.save(transfert).getId();

        //Manage the reference by updating
        String ref = "EDP837" + generateRef(id);
        transfert.setRef(ref);
        return transfertRepository.save(transfert);
    }

    @Override
    public List<Transfert> findAll() {
        return transfertRepository.findAll();
    }

    @Override
    public List<Transfert> findAllByClientId(Long clientId) {
        return transfertRepository.findByClientId(clientId);
    }

    @Override
    public List<Transfert> findAllByAgentId(Long agentId) {
        return transfertRepository.findByAgentId(agentId);
    }

    @Override
    public List<Transfert> findAllByBeneficiaireId(Long beneficiaireId) {
        return transfertRepository.findByBeneficiaireId(beneficiaireId);
    }

    @Override
    public List<Transfert> findAllByClientBanqueId(Long clientId) {
        return transfertRepository.findByClientBanqueId(clientId);
    }

    @Override
    public Transfert findByRef(String ref) {
        return transfertRepository.findByRef(ref);
    }

    private String generateRef (Long id){
        return String.format("%013d",id+1);
    }

    public Boolean verifMontantClient(double montant) {

        if(montant > 2000 ) {
            return false;

        }
        else {
            return true;
        }
    }

    public Boolean verifMontantAgence(double montant) {

        if(montant > 80000) {
            return false;

        }
        else {
            return true;
        }
    }

    public State getEtatTransfert(double montant, boolean otp) {
        State etat;

        if(verifMontantClient(montant) == false || otp == false || verifMontantAgence(montant) == false ) {
            etat = State.EXTOURNE;
        }
        else {
            etat= State.A_SERVIR;
        }
        return etat;
    }
}
