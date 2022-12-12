package gestion_stock.gestionStockapp.validator;

import gestion_stock.gestionStockapp.dto.LigneCommandeClientDto;
import gestion_stock.gestionStockapp.util.StaticUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LigneCommandeClientValidator {

    public static List<String> validate(LigneCommandeClientDto ligneCommandeClientDto){
        List<String> errors = new ArrayList<>();

        if(ligneCommandeClientDto!=null ){
            if(ligneCommandeClientDto.getArticle()==null){
                errors.add(StaticUtil.ARTICLE_OBLIGATOIRE);
            }
            if(ligneCommandeClientDto.getQuantite()==null || BigDecimal.ZERO.compareTo(ligneCommandeClientDto.getQuantite())==0){
                errors.add(StaticUtil.QUANTITE_OBLIGATOIRE);
            }
            if(ligneCommandeClientDto.getPrixUnitaire()==null || BigDecimal.ZERO.compareTo(ligneCommandeClientDto.getPrixUnitaire())==0){
                errors.add(StaticUtil.PRIX_UNITAIRE_OBLIGATOIRE);
            }
        }else{
            errors.add(StaticUtil.ENTITE_NULL);
        }

        return errors;
    }
}
