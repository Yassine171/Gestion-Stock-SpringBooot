package gestion_stock.gestionStockapp.controller.api;

import gestion_stock.gestionStockapp.dto.FournisseurDto;
import gestion_stock.gestionStockapp.util.StaticRoot;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(StaticRoot.ENTREPRISE_ENDPOINT)
@CrossOrigin(origins = "http://localhost:4200")
public interface FournisseurApi {

    @PostMapping(value = StaticRoot.FOURNISSEUR_ENDPOINT_SAVE,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Enregistrer un fournisseur (Ajouter / Modifier)",notes = "Cette méthode permet de créer ou modifier un fournisseur",response = FournisseurDto.class)
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description ="Utlisateur est crée/modifié")
    })
    FournisseurDto save(@RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = StaticRoot.FOURNISSEUR_ENDPOINT_FINDBYID,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Rechercher un fournisseur par son id",notes = "Cette méthode permet de rechercher un fournisseur par son id",response = FournisseurDto.class)
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description ="Utlisateur est trouvé"),
            @ApiResponse(responseCode ="404" , description ="Utlisateur n'est pas trouvé")
    })
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(value = StaticRoot.FOURNISSEUR_ENDPOINT_FINDBYMAIL,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Rechercher un fournisseur par mail",notes = "Cette méthode permet de rechercher un fournisseur par son email",response = FournisseurDto.class)
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description ="Utlisateur est trouvé dans la BDD"),
            @ApiResponse(responseCode ="404" , description ="Utlisateur n'est pas trouvé dans la BDD")

    })
    FournisseurDto findByMail(@PathVariable("mail") String email);

    @GetMapping(value = StaticRoot.FOURNISSEUR_ENDPOINT,produces = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Charger la liste des fournisseurs",notes = "Cette méthode permet de charger la liste des fournisseurs ",responseContainer ="List<FournisseurDto>")
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description ="les Utlisateurs sont trouvés dans la BDD"),
            @ApiResponse(responseCode ="404" , description ="les Utlisateurs ne sont pas trouvés dans la BDD"),

    })
    List<FournisseurDto> findAll();

    @DeleteMapping(value = StaticRoot.FOURNISSEUR_ENDPOINT_DELETE)
    //@ApiOperation(value = "Supprimer un fournisseur", notes = "Cette méthode permet de supprimer un fournisseur par son ID ")
    @ApiResponses(value ={
            @ApiResponse(responseCode ="200" , description = "L'objet fournisseur est supprimé de la BDD"),
            @ApiResponse(responseCode ="404" , description = "L'objet fournisseur n'est pas supprimé de la BDD")
    })
    void delete(@PathVariable("idFournisseur") Integer id);

}
