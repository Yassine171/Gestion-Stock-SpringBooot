package gestion_stock.gestionStockapp.controller.api;

import gestion_stock.gestionStockapp.dto.MvtStockDto;
import gestion_stock.gestionStockapp.util.StaticRoot;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping(StaticRoot.MVT_ENDPOINT)
@CrossOrigin(origins = "http://localhost:4200")
public interface MvtStockApi {

    @GetMapping(StaticRoot.MVT_ENDPOINT + "/stockreel/{idArticle}")
    BigDecimal stockReelArticle(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(StaticRoot.MVT_ENDPOINT + "/filter/article/{idArticle}")
    List<MvtStockDto> findAllByArticleId(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(StaticRoot.MVT_ENDPOINT + "/stockreel/entree")
    MvtStockDto entreeStock(@RequestBody MvtStockDto mvtStockDto);

    @GetMapping(StaticRoot.MVT_ENDPOINT + "/stockreel/sortie")
    MvtStockDto sortieStock(@RequestBody MvtStockDto mvtStockDto);

    @GetMapping(StaticRoot.MVT_ENDPOINT + "/stockreel/correction/positive")
    MvtStockDto correctionStockPositive(@RequestBody MvtStockDto mvtStockDto);

    @GetMapping(StaticRoot.MVT_ENDPOINT + "/stockreel/correction/negative")
    MvtStockDto correctionStockNegative(@RequestBody MvtStockDto mvtStockDto);
}
