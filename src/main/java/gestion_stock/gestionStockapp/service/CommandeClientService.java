package gestion_stock.gestionStockapp.service;

import gestion_stock.gestionStockapp.dto.CommandeClientDto;
import gestion_stock.gestionStockapp.dto.LigneCommandeClientDto;
import gestion_stock.gestionStockapp.model.enumeration.EtatCommande;

import java.math.BigDecimal;
import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto commandeClientDto);
    CommandeClientDto updateEtatCommande(Integer idCommande , EtatCommande etatCommande);
    CommandeClientDto updateQuantite(Integer idCommande, Integer idLigneCommande , BigDecimal quantite);
    CommandeClientDto updateClient(Integer idCommande, Integer idClient);
    CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande,  Integer idArticle);
    CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande);

    CommandeClientDto findById(Integer id);

    CommandeClientDto findByCode(String code);

    List<CommandeClientDto> findAll();
    List<LigneCommandeClientDto> findAllLigneCommandeClientByCommandeClientId(Integer idCommande);

    void delete(Integer id);
}
