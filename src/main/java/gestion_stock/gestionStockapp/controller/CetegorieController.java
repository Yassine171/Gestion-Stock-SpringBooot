package gestion_stock.gestionStockapp.controller;

import gestion_stock.gestionStockapp.controller.api.CategorieApi;
import gestion_stock.gestionStockapp.dto.CategorieDto;
import gestion_stock.gestionStockapp.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CetegorieController implements CategorieApi {

    @Autowired
    @Qualifier("CategorieServiceImplementation")
    private CategorieService categorieService;

    public CetegorieController() {
    }

    @Override
    public CategorieDto save(CategorieDto categorieDto) {
        return categorieService.save(categorieDto);
    }

    @Override
    public CategorieDto findById(Integer id) {
        return categorieService.findById(id);
    }

    @Override
    public CategorieDto findByCode(String code) {
        return categorieService.findByCode(code);
    }

    @Override
    public CategorieDto findByDesignation(String designation) {
        return categorieService.findByDesignation(designation);
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieService.findAll();
    }

    @Override
    public void delete(Integer id) {
        categorieService.delete(id);
    }
}
