package gestion_stock.gestionStockapp.model;

import jakarta.persistence.*;

import lombok.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true )
@Table(name = "ligne_vente")
@Entity
public class LigneVente extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "vente")
    private Vente vente;

    @ManyToOne
    @JoinColumn(name = "article")
    private Article article;

    @Column(name = "quantite")
    private BigDecimal quantite;

    @Column(name = "id_entreprise")
    private Integer idEntreprise;
}
