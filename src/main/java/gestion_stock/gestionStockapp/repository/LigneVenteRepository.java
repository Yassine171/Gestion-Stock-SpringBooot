package gestion_stock.gestionStockapp.repository;

import gestion_stock.gestionStockapp.model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneVenteRepository extends JpaRepository<LigneVente,Integer> {

    List<LigneVente> findAllByArticleId(Integer idArticle);

    List<LigneVente> findAllByVenteId(Integer idCommande);
}
