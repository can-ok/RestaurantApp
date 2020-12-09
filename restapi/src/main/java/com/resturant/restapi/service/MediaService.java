package com.resturant.restapi.service;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.converter.MediaDtoConverter;
import com.resturant.restapi.dto.MediaDto;
import com.resturant.restapi.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class MediaService {

    private static final String JPG_EXTENSION=".jpg";
    private static final String PNG_EXTENSION=".png";
    private static final String BMP_EXTENSION=".bmp";

    private static final String BMP_CONTENT="image/bmp";
    private static final String PNG_CONTENT="image/png";

    @Value("C:/Users/canok/Desktop/RestaurantGit/restapi/target/media")
    private String uploadDir;

    @Autowired
    MediaRepository mediaRepository;


    public List<MediaDto> getAllMedia(){


        return MediaDtoConverter.mediaListToMediaDtoList( mediaRepository.findAll());
    }

    public String saveFile(MultipartFile multipartFile, String imageName){


        try {
            Files.createDirectories(Paths.get(uploadDir));
            String filePath=generateFullFilePath(multipartFile);
            Path targetLocation= FileSystems.getDefault().getPath(filePath);
            Files.copy(multipartFile.getInputStream(),targetLocation,StandardCopyOption.REPLACE_EXISTING);


            byte[] fileBytes=multipartFile.getBytes();

            Media media=new Media();

            media.setFileContent(fileBytes);
            media.setName(imageName);

            mediaRepository.save(media);
            return "Success";


        } catch (IOException e) {
            e.printStackTrace();
            return "Fail";

        }


    }

    public String generateUUID(){
        return String.valueOf(java.util.UUID.randomUUID());
    }

    public String generateFullFilePath(MultipartFile multipartFile){
        String extension=JPG_EXTENSION;
        if(BMP_CONTENT.equals(multipartFile.getContentType())){
            extension=BMP_EXTENSION;
        }
        else if(PNG_CONTENT.equals(multipartFile.getContentType())){
            extension=PNG_EXTENSION;
        }
        return uploadDir+generateUUID()+extension;
    }

}
