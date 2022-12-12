package gestion_stock.gestionStockapp.dto;

import gestion_stock.gestionStockapp.model.Article;
import gestion_stock.gestionStockapp.model.enumeration.SourceMvtStock;
import gestion_stock.gestionStockapp.model.enumeration.TypeMvt;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MvStkDto {
   // private Integer id;

    private Article article;

    private BigDecimal quantite;

    private Instant dateMvt;

    private TypeMvt typeMvt;

    private SourceMvtStock sourceMvtStock;

    private Integer idEntreprise;

}
