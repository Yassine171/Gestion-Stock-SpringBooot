package gestion_stock.gestionStockapp.service;

import gestion_stock.gestionStockapp.dto.ChangerMotPasseUtilisateurDto;
import gestion_stock.gestionStockapp.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto UtilisateurDto);

    UtilisateurDto findById(Integer id);

    //UtilisateurDto findByNomAndPrenom(String nom,String prenom);

    UtilisateurDto findByEmail(String email);
    UtilisateurDto changerMotPasse(ChangerMotPasseUtilisateurDto changerMotPasseUtilisateurDto);

    UtilisateurDto findByEmailAndMotPasse(String email,String motPasse);

    List<UtilisateurDto> findAll();

    void delete(Integer id);
}
