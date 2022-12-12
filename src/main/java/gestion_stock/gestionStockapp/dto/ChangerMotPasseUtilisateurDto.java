package gestion_stock.gestionStockapp.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChangerMotPasseUtilisateurDto {

    private Integer idUtilisateur;

    //private String email;

    private String motPasse;

    private String confirmMotPasse;
}
