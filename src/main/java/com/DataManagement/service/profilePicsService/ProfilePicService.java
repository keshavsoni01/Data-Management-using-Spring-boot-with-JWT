package com.DataManagement.service.profilePicsService;

import com.DataManagement.entity.user.User;
import com.DataManagement.exception.BadRequestException;
import com.DataManagement.repository.userRepo.UserRepo;
import com.DataManagement.security.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProfilePicService {
    @Autowired
    private UserRepo userRepo;

    // upload profile pic
    public String uploadImage(String username,MultipartFile file) throws IOException, BadRequestException {

        User user = userRepo.findByUsernameAndIsDeletedFalse(username).orElseThrow(()-> new BadRequestException(401,"user not found"));
        user.setProfilePic(ImageUtils.compressImage(file.getBytes()));
        userRepo.save(user);

        if (user != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return "file is not uploaded successfully";
    }

    // get profile pic using username
    public byte[] downloadImage(String username){
        User dbImageData = userRepo.findByUsernameAndIsDeletedFalse(username).orElseThrow(()->new BadRequestException(401,"user not found with :"+username));
        byte[] images=ImageUtils.decompressImage(dbImageData.getProfilePic());
        return images;
    }

    // delete profile pic

    // update pofile pic

}
