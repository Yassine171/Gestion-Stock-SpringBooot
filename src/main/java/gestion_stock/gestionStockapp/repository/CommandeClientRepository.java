package gestion_stock.gestionStockapp.repository;

import gestion_stock.gestionStockapp.model.CommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface CommandeClientRepository extends JpaRepository<CommandeClient,Integer> {


    Optional<CommandeClient> findByCode(String code);
    List<CommandeClient> findAllByClientId(Integer id);
}
