package com.thuctap.busbooking.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryService {
    Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) throws IOException {
        Map uploadOptions = ObjectUtils.asMap(
                "use_filename", true,
                "unique_filename", true,
                "resource_type", "image"
        );
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadOptions);
        return uploadResult.get("secure_url").toString();
    }
}
