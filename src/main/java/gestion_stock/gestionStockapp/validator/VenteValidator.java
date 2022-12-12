package gestion_stock.gestionStockapp.validator;

import gestion_stock.gestionStockapp.dto.VenteDto;
import gestion_stock.gestionStockapp.util.StaticUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {

    public static List<String> validate(VenteDto venteDto) {
        List<String> errors = new ArrayList<>();

        if (venteDto != null) {
            if (!StringUtils.hasLength(venteDto.getCode())) {
                errors.add(StaticUtil.CODE_OBLIGATOIRE);
            }
            if (venteDto.getDateVente() == null) {
                errors.add(StaticUtil.DATE_OBLIGATOIRE);
            }
        } else {
            errors.add(StaticUtil.ENTITE_NULL);
            errors.add(StaticUtil.CODE_OBLIGATOIRE);
            errors.add(StaticUtil.DATE_OBLIGATOIRE);
        }

        return errors;
    }
}
