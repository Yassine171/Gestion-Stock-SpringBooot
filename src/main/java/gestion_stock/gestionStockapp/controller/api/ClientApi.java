package gestion_stock.gestionStockapp.controller.api;


import gestion_stock.gestionStockapp.dto.ClientDto;
import gestion_stock.gestionStockapp.util.StaticRoot;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(StaticRoot.APP_ROOT+"clients")
@CrossOrigin(origins = "http://localhost:4200")
public interface ClientApi {

    @PostMapping(value = StaticRoot.APP_ROOT+"/clients/create",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  //  @ApiOperation(value = "Enregistrer un client (Ajouter / Modifier)",notes = "Cette méthode permet de créer ou modifier un client",response = ClientDto.class)
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description ="Utlisateur est crée/modifié")
    })
    ClientDto save(@RequestBody ClientDto clientDto);

    @GetMapping(value = StaticRoot.APP_ROOT+"/clients/{idClient}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
  //  @ApiOperation(value = "Rechercher un client par son id",notes = "Cette méthode permet de rechercher un client par son id",response = ClientDto.class)
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description ="Utlisateur est trouvé"),
            @ApiResponse(responseCode ="404" , description ="Utlisateur n'est pas trouvé")
    })
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = StaticRoot.APP_ROOT+"/clients/{mail}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Rechercher un client par mail",notes = "Cette méthode permet de rechercher un client par son email",response = ClientDto.class)
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description ="Utlisateur est trouvé dans la BDD"),
            @ApiResponse(responseCode ="404" , description ="Utlisateur n'est pas trouvé dans la BDD")

    })
    ClientDto findByMail(@PathVariable("mail") String email);

    @GetMapping(value = StaticRoot.APP_ROOT+"/clients",produces = MediaType.APPLICATION_JSON_VALUE)
    //@ApiOperation(value = "Charger la liste des clients",notes = "Cette méthode permet de charger la liste des clients ",responseContainer ="List<ClientDto>")
    @ApiResponses({
            @ApiResponse(responseCode ="200" , description ="les Utlisateurs sont trouvés dans la BDD"),
            @ApiResponse(responseCode ="404" , description ="les Utlisateurs ne sont pas trouvés dans la BDD"),

    })
    List<ClientDto> findAll();

    @DeleteMapping(value = StaticRoot.APP_ROOT + "/clients/delete/{idClient}")
    //@ApiOperation(value = "Supprimer un client", notes = "Cette méthode permet de supprimer un client par son ID ")
    @ApiResponses(value ={
            @ApiResponse(responseCode ="200" , description ="L'objet client est supprimé de la BDD"),
            @ApiResponse(responseCode ="404" , description ="L'objet client n'est pas supprimé de la BDD")
    })
    void delete(@PathVariable("idClient") Integer id);

}
