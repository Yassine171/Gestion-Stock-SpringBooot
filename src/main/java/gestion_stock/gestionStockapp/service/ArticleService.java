package gestion_stock.gestionStockapp.service;

import gestion_stock.gestionStockapp.dto.ArticleDto;
import gestion_stock.gestionStockapp.dto.LigneCommandeClientDto;
import gestion_stock.gestionStockapp.dto.LigneCommandeFournisseurDto;
import gestion_stock.gestionStockapp.dto.LigneVenteDto;

import java.util.List;

public interface ArticleService {
    ArticleDto save(ArticleDto articleDto);
    ArticleDto findById(Integer id);
    ArticleDto findByCode(String code);
    List<ArticleDto> findAll();
    List<ArticleDto> findAllByCategorie(Integer idCategorie);
    List<LigneVenteDto> findHistoriqueVente(Integer idArticle);
    List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle);
    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);
    void delete(Integer id);
}
