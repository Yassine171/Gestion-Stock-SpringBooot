package gestion_stock.gestionStockapp.service.strategy;

import com.flickr4java.flickr.FlickrException;
import gestion_stock.gestionStockapp.dto.FournisseurDto;
import gestion_stock.gestionStockapp.exception.ErrorCode;
import gestion_stock.gestionStockapp.exception.InvalidOperationException;
import gestion_stock.gestionStockapp.service.FournisseurService;
import gestion_stock.gestionStockapp.service.FlickrPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("fournisseurStrategy")
@Slf4j
public class SaveFournisseurPhoto implements Strategy<FournisseurDto> {

    private FlickrPhotoService flickrPhotoService;
    private FournisseurService fournisseurService;

    @Autowired
    public SaveFournisseurPhoto(FlickrPhotoService flickrPhotoService, FournisseurService fournisseurService) {
        this.flickrPhotoService = flickrPhotoService;
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto savePhoto(Integer id,InputStream photo, String titre) throws FlickrException {
        FournisseurDto fournisseurDto = fournisseurService.findById(id);
        final String urlPhoto = flickrPhotoService.savePhoto(photo, titre);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("URL de la photo est invalide", ErrorCode.URL_PHOTO_INVALIDE);
        }
        fournisseurDto.setPhoto(urlPhoto);
        return fournisseurService.save(fournisseurDto);
    }
}
