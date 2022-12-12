package gestion_stock.gestionStockapp.service;

import gestion_stock.gestionStockapp.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {
    FournisseurDto save(FournisseurDto FournisseurDto);

    FournisseurDto findById(Integer id);

    //FournisseurDto findByNomAndPrenom(String nom,String prenom);

    FournisseurDto findByMail(String mail);

    List<FournisseurDto> findAll();

    void delete(Integer id);
}
