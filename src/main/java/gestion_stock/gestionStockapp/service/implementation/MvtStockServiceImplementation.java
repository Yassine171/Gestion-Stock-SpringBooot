package gestion_stock.gestionStockapp.service.implementation;

import gestion_stock.gestionStockapp.dto.MvtStockDto;
import gestion_stock.gestionStockapp.exception.ErrorCode;
import gestion_stock.gestionStockapp.exception.InvalidEntityException;
import gestion_stock.gestionStockapp.model.enumeration.TypeMvt;
import gestion_stock.gestionStockapp.repository.MvtStockRepository;
import gestion_stock.gestionStockapp.service.ArticleService;
import gestion_stock.gestionStockapp.service.MvtStockService;
import gestion_stock.gestionStockapp.validator.MvtStockValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service("MvtStockServiceImplementation")
@Slf4j
public class MvtStockServiceImplementation implements MvtStockService {

    private final MvtStockRepository mvtStockRepository;
    private final ArticleService articleService;

    @Autowired
    public MvtStockServiceImplementation(MvtStockRepository mvtStockRepository, ArticleService articleService) {
        this.mvtStockRepository = mvtStockRepository;
        this.articleService = articleService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        if (idArticle == null) {
            log.warn("ID article est null");
            return BigDecimal.valueOf(-1);
        }
        articleService.findById(idArticle);
        return mvtStockRepository.stockReelArticle(idArticle);
    }

    @Override
    public List<MvtStockDto> findAllByArticleId(Integer idArticle) {
        return mvtStockRepository.findAllByArticleId(idArticle)
                .stream()
                .map(MvtStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MvtStockDto entreeStock(MvtStockDto mvtStockDto) {
        saveMouvementStock(mvtStockDto, TypeMvt.ENTREE);
        mvtStockDto.setQuantite(BigDecimal.valueOf(Math.abs(mvtStockDto.getQuantite().doubleValue())));
        return MvtStockDto.fromEntity(mvtStockRepository.save(MvtStockDto.toEntity(mvtStockDto)));
    }


    @Override
    public MvtStockDto sortieStock(MvtStockDto mvtStockDto) {
        saveMouvementStock(mvtStockDto, TypeMvt.SORTIE);
        mvtStockDto.setQuantite(BigDecimal.valueOf(Math.abs(mvtStockDto.getQuantite().doubleValue()) * -1));
        return MvtStockDto.fromEntity(mvtStockRepository.save(MvtStockDto.toEntity(mvtStockDto)));
    }

    @Override
    public MvtStockDto correctionStockPositive(MvtStockDto mvtStockDto) {
        saveMouvementStock(mvtStockDto, TypeMvt.CORRECTION_POSITIVE);
        mvtStockDto.setQuantite(BigDecimal.valueOf(Math.abs(mvtStockDto.getQuantite().doubleValue())));
        return MvtStockDto.fromEntity(mvtStockRepository.save(MvtStockDto.toEntity(mvtStockDto)));
    }

    @Override
    public MvtStockDto correctionStockNegative(MvtStockDto mvtStockDto) {
        saveMouvementStock(mvtStockDto, TypeMvt.CORRECTION_NEGATIVE);
        mvtStockDto.setQuantite(BigDecimal.valueOf(Math.abs(mvtStockDto.getQuantite().doubleValue()) * -1));
        return MvtStockDto.fromEntity(mvtStockRepository.save(MvtStockDto.toEntity(mvtStockDto)));
    }

    private void saveMouvementStock(MvtStockDto mvtStockDto, TypeMvt typeMvt) {
        List<String> errors = MvtStockValidator.validate(mvtStockDto);
        if (!errors.isEmpty()) {
            log.error("Le  mouvement du stock est invalide {}" + mvtStockDto);
            throw new InvalidEntityException("Le mouvement du stock est invalide", ErrorCode.MVT_NOT_VALIDE, errors);
        }
        mvtStockDto.setTypeMvt(typeMvt);
    }
}
