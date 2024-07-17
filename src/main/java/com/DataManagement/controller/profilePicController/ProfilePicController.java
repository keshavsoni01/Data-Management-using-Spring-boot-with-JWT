package com.DataManagement.controller.profilePicController;

import com.DataManagement.service.profilePicsService.ProfilePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ProfilePicController {
    @Autowired
    private ProfilePicService profilePicService;

    // upload profile pic
    @PostMapping("/uploadPic/{username}")
    public ResponseEntity<String> uploadImage(@PathVariable String username,@RequestParam("image") MultipartFile file) throws IOException {
        return ResponseEntity.ok(profilePicService.uploadImage(username,file));
    }

    // get profile pic using username
    @GetMapping("/getPic/{username}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String username){
//        byte[] imageData = profilePicService.downloadImage(username);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(imageData);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("image/png"))
                .body(profilePicService.downloadImage(username));
    }

    // delete profile pic

    // update profile pic

}
