package gestion_stock.gestionStockapp.repository;

import gestion_stock.gestionStockapp.model.Article;
import gestion_stock.gestionStockapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface ClientRepository extends JpaRepository<Client,Integer> {

    Optional<Client> findByMail(String mail);
}
