package gestion_stock.gestionStockapp.model;
import gestion_stock.gestionStockapp.model.enumeration.EtatCommande;
import lombok.*;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true )
@Table(name = "commande_client")
@Entity
public class CommandeClient extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="code")
    private String code;

    @Column(name="date_commande")
    private Instant dateCommande;

    @Column(name="etat_commande")
    private EtatCommande etatCommande;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(mappedBy = "commandeClient")
    private List<LigneCommandeClient> ligneCommandeClients;

    @Column(name = "id_entreprise")
    private Integer idEntreprise;
}
