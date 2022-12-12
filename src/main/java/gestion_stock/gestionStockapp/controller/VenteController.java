package gestion_stock.gestionStockapp.controller;

import gestion_stock.gestionStockapp.controller.api.VenteApi;
import gestion_stock.gestionStockapp.dto.FournisseurDto;
import gestion_stock.gestionStockapp.dto.VenteDto;
import gestion_stock.gestionStockapp.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public class VenteController implements VenteApi {
    @Qualifier("VenteServiceImplementation")
    private final VenteService venteService;

    @Autowired
    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @Override
    public VenteDto save(VenteDto VenteDto) {
        return venteService.save(VenteDto);
    }

    @Override
    public VenteDto findById(Integer id) {
        return venteService.findById(id);
    }

    @Override
    public VenteDto findByCode(String code) {
        return venteService.findByCode(code);
    }

    @Override
    public VenteDto findByDateVente(Instant dateVente) {
        return venteService.findByDateVente(dateVente);
    }

    @Override
    public List<VenteDto> findAll() {
        return venteService.findAll();
    }

    @Override
    public void delete(Integer id) {
        venteService.delete(id);
    }
}
