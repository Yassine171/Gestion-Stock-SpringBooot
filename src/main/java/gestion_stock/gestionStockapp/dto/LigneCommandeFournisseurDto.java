package gestion_stock.gestionStockapp.dto;

import gestion_stock.gestionStockapp.model.AbstractEntity;
import gestion_stock.gestionStockapp.model.Client;
import gestion_stock.gestionStockapp.model.CommandeFournisseur;
import gestion_stock.gestionStockapp.model.LigneCommandeFournisseur;
import lombok.*;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommandeFournisseurDto {

    private Integer id;

    private ArticleDto article;

    private CommandeFournisseurDto commandeFournisseur;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;


    public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
        if (ligneCommandeFournisseur == null) {
            //TODO throw an exception
            return null;
        }
        return LigneCommandeFournisseurDto.builder()
                .id(ligneCommandeFournisseur.getId())
                .article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
                .commandeFournisseur(CommandeFournisseurDto.fromEntity(ligneCommandeFournisseur.getCommandeFournisseur()))
                .quantite(ligneCommandeFournisseur.getQuantite())
                .prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())
                .build();
    }

    public static LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
        if (ligneCommandeFournisseurDto == null) {
            //TODO throw an exception
            return null;
        }
        return LigneCommandeFournisseur.builder()
                .id(ligneCommandeFournisseurDto.getId())
                .article(ArticleDto.toEntity(ligneCommandeFournisseurDto.getArticle()))
                .commandeFournisseur(CommandeFournisseurDto.toEntity(ligneCommandeFournisseurDto.getCommandeFournisseur()))
                .quantite(ligneCommandeFournisseurDto.getQuantite())
                .prixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire())
                .build();
    }
}
