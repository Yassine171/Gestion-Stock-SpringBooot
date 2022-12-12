package gestion_stock.gestionStockapp.validator;

import gestion_stock.gestionStockapp.dto.LigneCommandeFournisseurDto;
import gestion_stock.gestionStockapp.util.StaticUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LigneCommandeFournisseurValidator {

    public static List<String> validate(LigneCommandeFournisseurDto commandeFournisseurDto){
        List<String> errors = new ArrayList<>();

        if(commandeFournisseurDto!=null ){
            if(commandeFournisseurDto.getArticle()==null){
                errors.add(StaticUtil.ARTICLE_OBLIGATOIRE);
            }
            if(commandeFournisseurDto.getQuantite()==null || BigDecimal.ZERO.compareTo(commandeFournisseurDto.getQuantite())==0){
                errors.add(StaticUtil.QUANTITE_OBLIGATOIRE);
            }
            if(commandeFournisseurDto.getPrixUnitaire()==null || BigDecimal.ZERO.compareTo(commandeFournisseurDto.getPrixUnitaire())==0){
                errors.add(StaticUtil.PRIX_UNITAIRE_OBLIGATOIRE);
            }
        }else{
            errors.add(StaticUtil.ENTITE_NULL);
        }

        return errors;
    }
}
