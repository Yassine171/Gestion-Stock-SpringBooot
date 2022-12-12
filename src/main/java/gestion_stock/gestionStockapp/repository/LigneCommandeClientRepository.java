package gestion_stock.gestionStockapp.repository;

import gestion_stock.gestionStockapp.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient,Integer> {


    List<LigneCommandeClient> findAllByCommandeClientId(Integer idCommande);
    List<LigneCommandeClient> findAllByArticleId(Integer idArticle);
}
