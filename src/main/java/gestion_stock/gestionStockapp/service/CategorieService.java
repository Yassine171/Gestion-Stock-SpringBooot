package gestion_stock.gestionStockapp.service;

import gestion_stock.gestionStockapp.dto.CategorieDto;

import java.util.List;

public interface CategorieService {
    CategorieDto save(CategorieDto articleDto);

    CategorieDto findById(Integer id);

    CategorieDto findByCode(String code);

    CategorieDto findByDesignation(String designation);

    List<CategorieDto> findAll();

    void delete(Integer id);

}
