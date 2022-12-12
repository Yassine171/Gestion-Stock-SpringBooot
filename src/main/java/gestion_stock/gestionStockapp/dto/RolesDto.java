package gestion_stock.gestionStockapp.dto;

import gestion_stock.gestionStockapp.model.Utilisateur;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolesDto {

    private String roleName;

    private Utilisateur utilisateur;
}
