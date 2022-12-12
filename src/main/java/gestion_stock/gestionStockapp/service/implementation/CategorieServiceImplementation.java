package gestion_stock.gestionStockapp.service.implementation;

import gestion_stock.gestionStockapp.dto.CategorieDto;
import gestion_stock.gestionStockapp.exception.EntityNotFoundException;
import gestion_stock.gestionStockapp.exception.ErrorCode;
import gestion_stock.gestionStockapp.exception.InvalidEntityException;
import gestion_stock.gestionStockapp.exception.InvalidOperationException;
import gestion_stock.gestionStockapp.model.Article;
import gestion_stock.gestionStockapp.model.Categorie;
import gestion_stock.gestionStockapp.repository.ArticleRepository;
import gestion_stock.gestionStockapp.repository.CategorieRepository;
import gestion_stock.gestionStockapp.service.CategorieService;
import gestion_stock.gestionStockapp.util.StaticUtil;
import gestion_stock.gestionStockapp.validator.CategorieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("CategorieServiceImplementation")
@Slf4j
public class CategorieServiceImplementation implements CategorieService {

    private final CategorieRepository categorieRepository;
    private ArticleRepository articleRepository;
    @Autowired
    public CategorieServiceImplementation(CategorieRepository categorieRepository, ArticleRepository articleRepository) {
        this.categorieRepository = categorieRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CategorieDto save(CategorieDto categorieDto) {
        List<String> errors = CategorieValidator.validate(categorieDto);
        if (!errors.isEmpty()) {
            log.error("La catégorie n'est pas valide");
            throw new InvalidEntityException("La catégorie n'est pas valide", ErrorCode.CATEGORIE_NOT_VALIDE, errors);
        }
        return CategorieDto.fromEntity(
                categorieRepository.save(CategorieDto.toEntity(categorieDto))
        );
    }

    @Override
    public CategorieDto findById(Integer id) {
        if (id == null) {
            log.error("L'id de la catégorie est null");
            return null;
        }
        Optional<Categorie> categorie = categorieRepository.findById(id);
        CategorieDto categorieDto = CategorieDto.fromEntity(categorie.get());
        return Optional.of(categorieDto)
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.CATEGORIE_NOT_FOUND));
    }

    @Override
    public CategorieDto findByCode(String code) {
        if (code == null) {
            log.error("Le code de la catégorie est null");
            return null;
        }
        return Optional.of(CategorieDto.fromEntity(categorieRepository.findByCode(code)))
                .orElseThrow(()-> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE,ErrorCode.CATEGORIE_NOT_FOUND));
    }

    @Override
    public CategorieDto findByDesignation(String designation) {
        if (designation == null) {
            log.error("La designation de la catégorie est null");
            return null;
        }
        Categorie categorie = categorieRepository.findByDesignation(designation);
        CategorieDto articleDto = CategorieDto.fromEntity(categorie);
        return Optional.of(articleDto)
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.CATEGORIE_NOT_FOUND));
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieRepository.findAll()
                .stream()
                .map(CategorieDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id==null){
            log.error("Impossible de supprimer la categorie, l'id est null");
            return;
        }
        List<Article> articles = articleRepository.findAllByCategorieId(id);
        if(!articles.isEmpty()){
            throw new InvalidOperationException("Impossible de supprimer la catégorie, elle est utilisée dans des articles", ErrorCode.CATEGORIE_ALREADY_IN_USE);
        }
            categorieRepository.deleteById(id);

    }
}
