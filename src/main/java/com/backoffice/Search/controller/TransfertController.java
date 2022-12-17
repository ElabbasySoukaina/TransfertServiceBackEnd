package com.backoffice.Search.controller;


import com.backoffice.Search.dto.TransfertDTO;
import com.backoffice.Search.model.TransfertConverter;
import com.backoffice.Search.repository.TransfertRepository;
import com.backoffice.Search.service.TransfertService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("transfert")
@Data
public class TransfertController {

    private final TransfertService transfertService;
    private final TransfertRepository transfertRepository;
    private final TransfertConverter transfertConverter;


    //Get Transfert by id
    @GetMapping("/{id}")
    public TransfertDTO getTransfertById(@PathVariable("id") Long id){
        return transfertConverter.convertToDTO(transfertRepository.getById(id));
    }


    @GetMapping("/")
    public ResponseEntity<List<TransfertDTO>> findAll() {
        return ResponseEntity.ok().body(transfertConverter.convertToDTOs(transfertService.findAll()));
    }

    @GetMapping("/client/{idClient}")
    List<TransfertDTO> getTransfertsByClient(@PathVariable("idClient") Long idClient){
        return transfertConverter.convertToDTOs(transfertService.findAllByClientId(idClient));
    }

    @GetMapping("/agent/{idEmetteur}")
    List<TransfertDTO> getTransfertsByAgent(@PathVariable("idEmetteur") Long idEmetteur){
        return transfertConverter.convertToDTOs(transfertRepository.findByAgentId(idEmetteur));
    }

    @GetMapping("/beneficiaire/{idBeneficiaire}")
    List<TransfertDTO> getTransfertsByBeneficiaire(@PathVariable("idBeneficiaire") Long idBeneficiaire){
        return transfertConverter.convertToDTOs(transfertService.findAllByBeneficiaireId(idBeneficiaire));
    }

}

