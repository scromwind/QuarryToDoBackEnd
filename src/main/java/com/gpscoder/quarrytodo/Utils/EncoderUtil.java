package com.gpscoder.quarrytodo.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class EncoderUtil {

    @Bean
    public BCryptPasswordEncoder EncoderPassword(){

        return new BCryptPasswordEncoder();
    }
}
