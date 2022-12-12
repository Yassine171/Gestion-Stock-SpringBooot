package gestion_stock.gestionStockapp.validator;

import gestion_stock.gestionStockapp.dto.CommandeClientDto;
import gestion_stock.gestionStockapp.util.StaticUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto commandeClientDto){
        List<String> errors = new ArrayList<>();

        if(commandeClientDto!=null ){
            if(!StringUtils.hasLength(commandeClientDto.getCode())){
                errors.add(StaticUtil.CODE_OBLIGATOIRE);
            }
            if(commandeClientDto.getDateCommande()==null){
                errors.add(StaticUtil.DATE_COMMANDE_OBLIGATOIRE);
            }
            if(StringUtils.hasLength(commandeClientDto.getEtatCommande().toString())){
                errors.add(StaticUtil.ETAT_COMMANDE_OBLIGATOIRE);
            }
            if(commandeClientDto.getClient()==null){
                errors.add(StaticUtil.CLIENT_OBLIGATOIRE);
            }
        }else{
            errors.add(StaticUtil.ENTITE_NULL);
        }

        return errors;
    }
}
