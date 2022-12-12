package gestion_stock.gestionStockapp.service;

import gestion_stock.gestionStockapp.dto.ClientDto;
import gestion_stock.gestionStockapp.dto.FournisseurDto;

import java.util.List;

public interface ClientService {
    ClientDto save(ClientDto ClientDto);

    ClientDto findById(Integer id);

   // ClientDto findByNomAndPrenom(String nom,String prenom);

    ClientDto findByMail(String mail);

    List<ClientDto> findAll();

    void delete(Integer id);
}
