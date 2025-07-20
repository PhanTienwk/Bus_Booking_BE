package com.thuctap.busbooking.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dxxswaeor",
                "api_key", "556294491589589",
                "api_secret", "zvVZjk0lyAOUUMXczSJzVt6IHwo",
                "secure", true
        ));
    }


}
