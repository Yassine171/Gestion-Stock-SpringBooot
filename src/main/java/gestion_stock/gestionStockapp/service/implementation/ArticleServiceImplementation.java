package gestion_stock.gestionStockapp.service.implementation;

import gestion_stock.gestionStockapp.dto.ArticleDto;
import gestion_stock.gestionStockapp.dto.LigneCommandeClientDto;
import gestion_stock.gestionStockapp.dto.LigneCommandeFournisseurDto;
import gestion_stock.gestionStockapp.dto.LigneVenteDto;
import gestion_stock.gestionStockapp.exception.EntityNotFoundException;
import gestion_stock.gestionStockapp.exception.ErrorCode;
import gestion_stock.gestionStockapp.exception.InvalidEntityException;
import gestion_stock.gestionStockapp.exception.InvalidOperationException;
import gestion_stock.gestionStockapp.model.Article;
import gestion_stock.gestionStockapp.model.LigneCommandeClient;
import gestion_stock.gestionStockapp.model.LigneCommandeFournisseur;
import gestion_stock.gestionStockapp.model.LigneVente;
import gestion_stock.gestionStockapp.repository.ArticleRepository;
import gestion_stock.gestionStockapp.repository.LigneCommandeClientRepository;
import gestion_stock.gestionStockapp.repository.LigneCommandeFournisseurRepository;
import gestion_stock.gestionStockapp.repository.LigneVenteRepository;
import gestion_stock.gestionStockapp.service.ArticleService;
import gestion_stock.gestionStockapp.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ArticleServiceImplementation")
@Slf4j
public class ArticleServiceImplementation implements ArticleService {

    private ArticleRepository articleRepository;
    private LigneVenteRepository ligneVenteRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;

    @Autowired
    public ArticleServiceImplementation(ArticleRepository articleRepository, LigneVenteRepository ligneVenteRepository, LigneCommandeClientRepository ligneCommandeClientRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        List<String> errors = ArticleValidator.validate(articleDto);
        if (!errors.isEmpty()) {
            log.error("L'article n'est pas valide");
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCode.ARTICLE_NOT_VALIDE, errors);
        }
        return ArticleDto.fromEntity(
                articleRepository.save(ArticleDto.toEntity(articleDto))
        );
    }

    @Override
    public ArticleDto findById(Integer id) {
        if (id == null) {
            log.error("Article ID est null");
            return null;
        }
        Optional<Article> article = articleRepository.findById(id);
        ArticleDto articleDto = ArticleDto.fromEntity(article.get());
        return Optional.of(articleDto)
                .orElseThrow(() -> new EntityNotFoundException("Aucun article avec l'id = " + id + " n'est trouvé dans la BDD", ErrorCode.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCode(String code) {
        if (code == null) {
            log.error("Article code est null");
            return null;
        }
        Article article = articleRepository.findByCode(code);
        ArticleDto articleDto = ArticleDto.fromEntity(article);
        return Optional.of(articleDto)
                .orElseThrow(() -> new EntityNotFoundException("Aucun article avec le code = " + code + " n'est trouvé dans la BDD", ErrorCode.ARTICLE_NOT_FOUND));
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll()
                .stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findAllByCategorie(Integer idCategotie) {
        return articleRepository.findAllByCategorieId(idCategotie).stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneVenteDto> findHistoriqueVente(Integer idArticle) {
        return ligneVenteRepository.findAllByArticleId(idArticle)
                .stream()
                .map(LigneVenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle) {
        return ligneCommandeClientRepository.findAllByArticleId(idArticle)
                .stream()
                .map(LigneCommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle) {
        return ligneCommandeFournisseurRepository.findAllByArticleId(idArticle)
                .stream()
                .map(LigneCommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Impossible de supprimer l'artile, l'id est null");
            return;
        }
        List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByArticleId(id);
        if (!ligneCommandeClients.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer l'article, il est utilisée dans des lignes commande client", ErrorCode.ARTICLE_ALREADY_IN_USE);
        }
        List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByArticleId(id);
        if (!ligneCommandeFournisseurs.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer l'article, il est utilisée dans des lignes commande fournisseur", ErrorCode.ARTICLE_ALREADY_IN_USE);
        }
        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByArticleId(id);
        if (!ligneVentes.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer l'article, il est utilisée dans des lignes de vente", ErrorCode.ARTICLE_ALREADY_IN_USE);
        }
        articleRepository.deleteById(id);

    }
}
