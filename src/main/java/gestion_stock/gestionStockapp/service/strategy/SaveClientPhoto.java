package gestion_stock.gestionStockapp.service.strategy;

import com.flickr4java.flickr.FlickrException;
import gestion_stock.gestionStockapp.dto.ClientDto;
import gestion_stock.gestionStockapp.exception.ErrorCode;
import gestion_stock.gestionStockapp.exception.InvalidOperationException;
import gestion_stock.gestionStockapp.service.ClientService;
import gestion_stock.gestionStockapp.service.FlickrPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("clientStrategy")
@Slf4j
public class SaveClientPhoto implements Strategy<ClientDto> {

    private FlickrPhotoService flickrPhotoService;
    private ClientService clientService;

    @Autowired
    public SaveClientPhoto(FlickrPhotoService flickrPhotoService, ClientService clientService) {
        this.flickrPhotoService = flickrPhotoService;
        this.clientService = clientService;
    }

    @Override
    public ClientDto savePhoto(Integer id,InputStream photo, String titre) throws FlickrException {
        ClientDto clientDto = clientService.findById(id);
        final String urlPhoto = flickrPhotoService.savePhoto(photo, titre);
        if(!StringUtils.hasLength(urlPhoto)){
            throw new InvalidOperationException("URL de la photo est invalide", ErrorCode.URL_PHOTO_INVALIDE);
        }
        clientDto.setPhoto(urlPhoto);
        return clientService.save(clientDto);
    }
}
