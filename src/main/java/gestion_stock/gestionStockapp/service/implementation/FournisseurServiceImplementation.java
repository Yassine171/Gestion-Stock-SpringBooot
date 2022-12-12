package gestion_stock.gestionStockapp.service.implementation;

import gestion_stock.gestionStockapp.dto.FournisseurDto;
import gestion_stock.gestionStockapp.exception.EntityNotFoundException;
import gestion_stock.gestionStockapp.exception.ErrorCode;
import gestion_stock.gestionStockapp.exception.InvalidEntityException;
import gestion_stock.gestionStockapp.exception.InvalidOperationException;
import gestion_stock.gestionStockapp.model.CommandeFournisseur;
import gestion_stock.gestionStockapp.model.Fournisseur;
import gestion_stock.gestionStockapp.repository.CommandeFournisseurRepository;
import gestion_stock.gestionStockapp.repository.FournisseurRepository;
import gestion_stock.gestionStockapp.service.FournisseurService;
import gestion_stock.gestionStockapp.util.StaticUtil;
import gestion_stock.gestionStockapp.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("FournisseurServiceImplementation")
@Slf4j
public class FournisseurServiceImplementation implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;
    private CommandeFournisseurRepository commandeFournisseurRepository;

    @Autowired
    public FournisseurServiceImplementation(FournisseurRepository fournisseurRepository, CommandeFournisseurRepository commandeFournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
        this.commandeFournisseurRepository = commandeFournisseurRepository;
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {
        List<String> errors = FournisseurValidator.validate(fournisseurDto);
        if (!errors.isEmpty()) {
            log.error("Le fournisseur est invalide");
            throw new InvalidEntityException("Le fournisseur est invalide", ErrorCode.CATEGORIE_NOT_VALIDE, errors);
        }
        return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(fournisseurDto)));
    }

    @Override
    public FournisseurDto findById(Integer id) {
        if (id == null) {
            log.error("Le fournisseur avec l'id " + id + " n'est pas présent dans la BDD");
            return null;
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        return Optional.of(FournisseurDto.fromEntity(fournisseur.get()))
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.CLIENT_NOT_FOUND));
    }

    @Override
    public FournisseurDto findByMail(String mail) {
        if (mail == null) {
            log.error("Le fournisseur avec l'email " + mail + " n'est pas présent dans la BDD");
            return null;
        }
        Optional<Fournisseur> fournisseur = fournisseurRepository.findByMail(mail);
        return Optional.of(FournisseurDto.fromEntity(fournisseur.get()))
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.CLIENT_NOT_FOUND));

    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurRepository.findAll()
                .stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Impossible de supprimer le client, l'id est null");
            return;
        }
        List<CommandeFournisseur> commandeFournisseurs = commandeFournisseurRepository.findAllByFournisseurId(id);
        if (!commandeFournisseurs.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer le fournisseur, is est utilisé dans des commandes ", ErrorCode.FOURNISSEUR_ALREADY_IN_USE);
        }
        fournisseurRepository.deleteById(id);
    }
}
