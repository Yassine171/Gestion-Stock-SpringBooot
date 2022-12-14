package gestion_stock.gestionStockapp.model;
import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "client")
@Entity
public class Client extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "telephone")
    private String telephone;

    @Embedded
    private Adresse adresse;
    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;

    @Column(name = "id_entreprise")
    private Integer idEntreprise;
}
