package gestion_stock.gestionStockapp.dto;

import gestion_stock.gestionStockapp.model.AbstractEntity;
import gestion_stock.gestionStockapp.model.Article;
import gestion_stock.gestionStockapp.model.Client;
import gestion_stock.gestionStockapp.model.LigneVente;
import lombok.*;

import jakarta.persistence.Column;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LigneVenteDto {

    private Integer id;

    private VenteDto vente;

    private BigDecimal quantite;

    private ArticleDto article;




    public static LigneVenteDto fromEntity(LigneVente ligneVente) {
        if (ligneVente == null) {
            //TODO throw an exception
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .vente(VenteDto.fromEntity(ligneVente.getVente()))
                .article(ArticleDto.fromEntity(ligneVente.getArticle()))
                .quantite(ligneVente.getQuantite())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {
        if (ligneVenteDto == null) {
            //TODO throw an exception
            return null;
        }
        return LigneVente.builder()
                .id(ligneVenteDto.getId())
                .vente(VenteDto.toEntity(ligneVenteDto.getVente()))
                .quantite(ligneVenteDto.getQuantite())
                .article(ArticleDto.toEntity(ligneVenteDto.getArticle()))
                .build();
    }
}
