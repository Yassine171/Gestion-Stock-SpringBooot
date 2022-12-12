package gestion_stock.gestionStockapp.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import jakarta.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Adresse {

    @Column(name = "adresse1")
    private String adresse1;

    @Column(name = "adresse2")
    private String adresse2;

    @Column(name = "ville")
    private String ville;

    @Column(name = "code_postale")
    private String codePostale;

    @Column(name = "pays")
    private String pays;
}
