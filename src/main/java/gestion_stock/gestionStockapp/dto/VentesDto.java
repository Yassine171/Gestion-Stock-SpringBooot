package gestion_stock.gestionStockapp.dto;

import gestion_stock.gestionStockapp.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentesDto {

    private String code;

    private Instant dateVente;

    private String commentaire;
}
