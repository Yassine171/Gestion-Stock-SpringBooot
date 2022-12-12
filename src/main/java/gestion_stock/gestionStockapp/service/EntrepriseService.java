package gestion_stock.gestionStockapp.service;

import gestion_stock.gestionStockapp.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {
    EntrepriseDto save(EntrepriseDto entrepriseDto);

    EntrepriseDto findById(Integer id);

    EntrepriseDto findByNom(String nom);

    List<EntrepriseDto> findAll();

    void delete(Integer id);
}
