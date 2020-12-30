package com.resturant.restapi.controller;

import com.resturant.restapi.Model.Media;
import com.resturant.restapi.dto.MediaDto;
import com.resturant.restapi.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3006"})
@RestController
@RequestMapping("/media")
public class MediaController {
    @Autowired
    MediaService mediaService;

    @GetMapping("/getAll")
    public List<MediaDto> getAll(){
        return mediaService.getAllMedia();
    }

    @PostMapping("/add")
    public String addAllMedia(@NotNull @RequestParam("file") MultipartFile file,@NotNull @RequestParam String imageName)
    {
       return mediaService.saveFile(file,imageName);
    }
}
