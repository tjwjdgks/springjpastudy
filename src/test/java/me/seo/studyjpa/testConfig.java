package me.seo.studyjpa;

import me.seo.studyjpa.listener.PPostLisener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class testConfig {
    @Bean
    public PPostLisener pPostLisener(){
        return new PPostLisener();
    }
}
