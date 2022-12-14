package gestion_stock.gestionStockapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gestion_stock.gestionStockapp.model.CommandeFournisseur;
import gestion_stock.gestionStockapp.model.enumeration.EtatCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandeFournisseurDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private EtatCommande etatCommande;

    private FournisseurDto fournisseur;

     private Integer idEntreprise;


    @JsonIgnore
    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
        if (commandeFournisseur == null) {
            //TODO throw an exception
            return null;
        }
        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .code(commandeFournisseur.getCode())
                .dateCommande(commandeFournisseur.getDateCommande())
                .etatCommande(commandeFournisseur.getEtatCommande())
                .fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
                .idEntreprise(commandeFournisseur.getIdEntreprise())
                .build();
    }

    public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto) {
        if (commandeFournisseurDto == null) {
            //TODO throw an exception
            return null;
        }
        return CommandeFournisseur.builder()
                .id(commandeFournisseurDto.getId())
                .code(commandeFournisseurDto.getCode())
                .dateCommande(commandeFournisseurDto.getDateCommande())
                .etatCommande(commandeFournisseurDto.getEtatCommande())
                .fournisseur(FournisseurDto.toEntity(commandeFournisseurDto.getFournisseur()))
                .idEntreprise(commandeFournisseurDto.getIdEntreprise())
                .build();
    }

    public boolean isCommandeLivree() {
        return EtatCommande.LIVREE.equals(this.getEtatCommande());
    }
}
