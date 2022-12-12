package gestion_stock.gestionStockapp.controller.api;


import gestion_stock.gestionStockapp.dto.UtilisateurDto;
import gestion_stock.gestionStockapp.util.StaticRoot;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(StaticRoot.UTILISATEUR_ENDPOINT)
public interface UtilisateurApi {

    @PostMapping(value = StaticRoot.UTILISATEUR_ENDPOINT_SAVE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Enregistrer un utilisateur (Ajouter / Modifier)", notes = "Cette méthode permet de créer ou modifier un utilisateur", response = UtilisateurDto.class)
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description = "Utlisateur est crée/modifié")
    })
    UtilisateurDto save(@RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(value = StaticRoot.UTILISATEUR_ENDPOINT_FINDBYID)
    //@ApiOperation(value = "Rechercher un utilisateur", notes = "Cette méthode permet de rechercher un utilisateur par son id", response = UtilisateurDto.class)
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description = "Utlisateur est trouvé"),
            @ApiResponse(responseCode ="404" , description = "Utlisateur n'est pas trouvé")
    })
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);


    @GetMapping(value = StaticRoot.UTILISATEUR_ENDPOINT_FINDBYMAIL)
    //@ApiOperation(value = "Rechercher un utilisateur", notes = "Cette méthode permet de rechercher un utilisateur par son email", response = UtilisateurDto.class)
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description = "Utlisateur est trouvé dans la BDD"),
            @ApiResponse(responseCode ="404" , description = "Utlisateur n'est pas trouvé dans la BDD")

    })
    @CrossOrigin
    UtilisateurDto findByEmail(String email);

    @GetMapping(value = StaticRoot.UTILISATEUR_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Charger la liste des utilisateurs", notes = "Cette méthode permet de charger la liste des utilisateurs ", responseContainer = "List<UtilisateurDto>")
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description = "les Utlisateurs sont trouvés dans la BDD"),
            @ApiResponse(responseCode ="404" , description = "les Utlisateurs ne sont pas trouvés dans la BDD"),

    })
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = StaticRoot.UTILISATEUR_ENDPOINT_DELETE)
    //@ApiOperation(value = "Supprimer un utilisateur", notes = "Cette méthode permet de supprimer un utilisateur par son ID ")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200" , description = "L'objet utilisateur est supprimé de la BDD"),
            @ApiResponse(responseCode ="404" , description = "L'objet utilisateur n'est pas supprimé de la BDD")
    })
    void delete(@PathVariable("idUtilisateur") Integer id);

}
