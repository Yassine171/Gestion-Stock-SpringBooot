package gestion_stock.gestionStockapp.service.implementation;

import gestion_stock.gestionStockapp.dto.ChangerMotPasseUtilisateurDto;
import gestion_stock.gestionStockapp.dto.UtilisateurDto;
import gestion_stock.gestionStockapp.exception.EntityNotFoundException;
import gestion_stock.gestionStockapp.exception.ErrorCode;
import gestion_stock.gestionStockapp.exception.InvalidEntityException;
import gestion_stock.gestionStockapp.exception.InvalidOperationException;
import gestion_stock.gestionStockapp.model.Utilisateur;
import gestion_stock.gestionStockapp.repository.UtilisateurRepository;
import gestion_stock.gestionStockapp.service.UtilisateurService;
import gestion_stock.gestionStockapp.util.StaticUtil;
import gestion_stock.gestionStockapp.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("UtilisateurServiceImplementation")
@Slf4j
public class UtilisateurServiceImplementation implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurServiceImplementation(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        List<String> errors = UtilisateurValidator.validate(utilisateurDto);
        if (!errors.isEmpty()) {
            log.error("L'utilisateur est invalide" + utilisateurDto);
            log.error(errors.toString());
            throw new InvalidEntityException("Le utilisateur est invalide", ErrorCode.CATEGORIE_NOT_VALIDE, errors);
        }
        if (userAlreadyExists(utilisateurDto.getEmail())) {
            log.error("L'utilisateur existe d??j?? avec le meme email");
            throw new InvalidEntityException("Un utilisateur avec le meme email existe d??j?? ", ErrorCode.UTILISATEUR_ALREADY_EXISTS, errors);
        }
        utilisateurDto.setMotPasse(passwordEncoder.encode(utilisateurDto.getMotPasse()));
        return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto)));
    }

    private boolean userAlreadyExists(String email) {
        return utilisateurRepository.findByEmail(email).isPresent();
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null) {
            log.error("L'utilisateur avec l'id " + id + " n'est pas pr??sent dans la BDD");
            return null;
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return Optional.of(UtilisateurDto.fromEntity(utilisateur.get()))
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.CLIENT_NOT_FOUND));
    }

    @Override
    public UtilisateurDto findByEmail(String email) {
        if (email == null) {
            log.error("L'utilisateur avec l'email " + email + " n'est pas pr??sent dans la BDD");
            return null;
        }
        System.err.println(utilisateurRepository.findByEmail(email).get());
        return utilisateurRepository.findByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public UtilisateurDto findByEmailAndMotPasse(String email, String motPasse) {
        if (!StringUtils.hasLength(email) || motPasse == null || !StringUtils.hasLength(motPasse)) {
            log.error("L'email et le mot de passe sont vides !!");
            return null;
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findUtilisateurByEmailAndMotPasse(email, motPasse);
        return Optional.of(UtilisateurDto.fromEntity(utilisateur.get()))
                .orElseThrow(() -> new EntityNotFoundException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll()
                .stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.warn("Impossible de supprimer un utilisateur avec un id null");
            throw new InvalidEntityException(StaticUtil.AUCUN_ELEMENT_TROUVE, ErrorCode.ARTICLE_NOT_FOUND);
        } else {
            utilisateurRepository.deleteById(id);
        }
    }

    @Override
    public UtilisateurDto changerMotPasse(ChangerMotPasseUtilisateurDto changerMotPasseUtilisateurDto) {
        validateChangerMotPasse(changerMotPasseUtilisateurDto);
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(changerMotPasseUtilisateurDto.getIdUtilisateur());
        if (utilisateurOptional.isEmpty()) {
            log.warn("Impossible de trouver l'utilisateur pour changer le mot de passe");
            throw new EntityNotFoundException("Impossible de trouver l'utilisateur pour changer le mot de passe", ErrorCode.UTILISATEUR_NOT_FOUND);
        }
        Utilisateur utilisateur = utilisateurOptional.get();
        utilisateur.setMotPasse(passwordEncoder.encode(changerMotPasseUtilisateurDto.getMotPasse()));
        return UtilisateurDto.fromEntity(utilisateurRepository.save(utilisateur));
    }

    private void validateChangerMotPasse(ChangerMotPasseUtilisateurDto changerMotPasseUtilisateurDto) {
        if (changerMotPasseUtilisateurDto == null) {
            log.warn("Impossible de modifier le mot de passe d'un utilisateur null");
            throw new InvalidOperationException("Aucune information n'a ??t??e fournie pour pouvoir changer mot de passe", ErrorCode.UTILISATEUR_CHANGE_PASSWORD_NOT_VALIDE);
        }
        if (changerMotPasseUtilisateurDto.getIdUtilisateur() == null) {
            log.warn("Impossible de modifier le mot de passe d'un utilisateur un id null");
            throw new InvalidOperationException("Id utlisateurn'a pas ??t?? fourni pour pouvoir changer mot de passe", ErrorCode.UTILISATEUR_CHANGE_PASSWORD_NOT_VALIDE);
        }
        if (StringUtils.hasLength(changerMotPasseUtilisateurDto.getMotPasse()) || StringUtils.hasLength(changerMotPasseUtilisateurDto.getConfirmMotPasse())) {
            log.warn("Impossible de modifier le mot de passe d'un utilisateur avec un mot de passe vide");
            throw new InvalidOperationException("Impossible de modifier le mot de passe d'un utilisateur un un mot de passe vide", ErrorCode.UTILISATEUR_CHANGE_PASSWORD_NOT_VALIDE);
        }
        if (!changerMotPasseUtilisateurDto.getMotPasse().equals(changerMotPasseUtilisateurDto.getConfirmMotPasse())) {
            log.warn("Impossible de modifier le mot de passe d'un utilisateur avec deux mots de passe d??fferents");
            throw new InvalidOperationException("Mot de passe utilisateur non conforme, impossible de changer mot de passe ", ErrorCode.UTILISATEUR_CHANGE_PASSWORD_NOT_VALIDE);
        }
    }

}
