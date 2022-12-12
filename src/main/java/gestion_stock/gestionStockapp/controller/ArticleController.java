package gestion_stock.gestionStockapp.controller;

import gestion_stock.gestionStockapp.controller.api.ArticleApi;
import gestion_stock.gestionStockapp.dto.ArticleDto;
import gestion_stock.gestionStockapp.dto.LigneCommandeClientDto;
import gestion_stock.gestionStockapp.dto.LigneCommandeFournisseurDto;
import gestion_stock.gestionStockapp.dto.LigneVenteDto;
import gestion_stock.gestionStockapp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {

    @Autowired
    @Qualifier("ArticleServiceImplementation")
    private ArticleService articleService;

    public ArticleController() {
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        return articleService.save(articleDto);
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCode(String code) {
        return articleService.findByCode(code);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);
    }

    @Override
    public List<ArticleDto> findAllByCategorie(Integer idCategorie) {
        return articleService.findAllByCategorie(idCategorie);
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVente(Integer idArticle) {
        return articleService.findHistoriqueVente(idArticle);
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle) {
        return articleService.findHistoriqueCommandeClient(idArticle);
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return articleService.findHistoriqueCommandeFournisseur(idArticle);
    }
}
