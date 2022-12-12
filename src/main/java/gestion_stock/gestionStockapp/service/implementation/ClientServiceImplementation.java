package gestion_stock.gestionStockapp.service.implementation;

import gestion_stock.gestionStockapp.dto.ClientDto;
import gestion_stock.gestionStockapp.exception.EntityNotFoundException;
import gestion_stock.gestionStockapp.exception.ErrorCode;
import gestion_stock.gestionStockapp.exception.InvalidEntityException;
import gestion_stock.gestionStockapp.exception.InvalidOperationException;
import gestion_stock.gestionStockapp.model.Client;
import gestion_stock.gestionStockapp.model.CommandeClient;
import gestion_stock.gestionStockapp.repository.ClientRepository;
import gestion_stock.gestionStockapp.repository.CommandeClientRepository;
import gestion_stock.gestionStockapp.service.ClientService;
import gestion_stock.gestionStockapp.util.StaticUtil;
import gestion_stock.gestionStockapp.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("ClientServiceImplementation")
@Slf4j
public class ClientServiceImplementation implements ClientService {

    private final ClientRepository clientRepository;
    private CommandeClientRepository commandeClientRepository;


    @Autowired
    public ClientServiceImplementation(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository) {
        this.clientRepository = clientRepository;
        this.commandeClientRepository = commandeClientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) {
        List<String> errors = ClientValidator.validate(clientDto);
        if (!errors.isEmpty()) {
            log.error("Le client est invalide");
            throw new InvalidEntityException("Le client est invalide", ErrorCode.CLIENT_NOT_VALIDE, errors);
        }
        return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(clientDto)));
    }

    @Override
    public ClientDto findById(Integer id) {
        if (id == null) {
            log.error("Le client avec l'id " + id + " n'est pas présent dans la BDD");
            return null;
        }
        Optional<Client> client = clientRepository.findById(id);
        return Optional.of(ClientDto.fromEntity(client.get()))
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.CLIENT_NOT_FOUND));
    }

    @Override
    public ClientDto findByMail(String mail) {
        if (mail == null) {
            log.error("Le client avec l'email " + mail + " n'est pas présent dans la BDD");
            return null;
        }
        Optional<Client> client = clientRepository.findByMail(mail);
        return Optional.of(ClientDto.fromEntity(client.get()))
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.CLIENT_NOT_FOUND));

    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Impossible de supprimer le client, l'id est null");
            return;
        }
        List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
        if (!commandeClients.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer le client, is est utilisé dans des commandes", ErrorCode.CLIENT_ALREADY_IN_USE);
        }
        clientRepository.deleteById(id);

    }
}
