package gestion_stock.gestionStockapp.service;

import gestion_stock.gestionStockapp.dto.CommandeFournisseurDto;
import gestion_stock.gestionStockapp.dto.LigneCommandeFournisseurDto;
import gestion_stock.gestionStockapp.model.enumeration.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto);
    CommandeFournisseurDto updateEtatCommande(Integer idCommande , EtatCommande etatCommande);
    CommandeFournisseurDto updateQuantite(Integer idCommande, Integer idLigneCommande , BigDecimal quantite);
    CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);
    CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande,  Integer idArticle);
    CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande);

    CommandeFournisseurDto findById(Integer id);

    CommandeFournisseurDto findByCode(String code);

    List<CommandeFournisseurDto> findAll();
    List<LigneCommandeFournisseurDto> findAllLigneCommandeFournisseurByCommandeFournisseurId(Integer idCommande);

    void delete(Integer id);
}
