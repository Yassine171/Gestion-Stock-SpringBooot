package gestion_stock.gestionStockapp.service.strategy;

import com.flickr4java.flickr.FlickrException;
import gestion_stock.gestionStockapp.dto.EntrepriseDto;
import gestion_stock.gestionStockapp.exception.ErrorCode;
import gestion_stock.gestionStockapp.exception.InvalidOperationException;
import gestion_stock.gestionStockapp.service.EntrepriseService;
import gestion_stock.gestionStockapp.service.FlickrPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("entrepriseStrategy")
@Slf4j
public class SaveEntreprisePhoto implements Strategy<EntrepriseDto> {

    private FlickrPhotoService flickrPhotoService;
    private EntrepriseService entrepriseService;

    @Autowired
    public SaveEntreprisePhoto(FlickrPhotoService flickrPhotoService, EntrepriseService entrepriseService) {
        this.flickrPhotoService = flickrPhotoService;
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto savePhoto(Integer id,InputStream photo, String titre) throws FlickrException {
        EntrepriseDto entrepriseDto = entrepriseService.findById(id);
        final String urlPhoto = flickrPhotoService.savePhoto(photo, titre);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("URL de la photo est invalide", ErrorCode.URL_PHOTO_INVALIDE);
        }
        entrepriseDto.setPhoto(urlPhoto);
        return entrepriseService.save(entrepriseDto);
    }
}
