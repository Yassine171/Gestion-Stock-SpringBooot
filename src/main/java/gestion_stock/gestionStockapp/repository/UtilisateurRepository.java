package gestion_stock.gestionStockapp.repository;

import gestion_stock.gestionStockapp.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {


    //@Query("select u from Utilisateur u where u.email= :email") si on veut utiliser JPQL
    Optional<Utilisateur> findByEmail(String email);

    @Query("select u from Utilisateur u where u.email= :email and u.motPasse=:motPasse")
    Optional<Utilisateur> findUtilisateurByEmailAndMotPasse(@Param("email") String email, @Param("motPasse") String motPasse);
}
