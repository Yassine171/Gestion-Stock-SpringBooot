package gestion_stock.gestionStockapp.service;

import gestion_stock.gestionStockapp.dto.MvtStockDto;

import java.math.BigDecimal;
import java.util.List;

public interface MvtStockService {

    BigDecimal stockReelArticle(Integer idArticle);
    List<MvtStockDto> findAllByArticleId(Integer idArticle);
    MvtStockDto entreeStock(MvtStockDto mvtStockDto);
    MvtStockDto sortieStock(MvtStockDto mvtStockDto);
    MvtStockDto correctionStockPositive(MvtStockDto mvtStockDto);
    MvtStockDto correctionStockNegative(MvtStockDto mvtStockDto);
}
