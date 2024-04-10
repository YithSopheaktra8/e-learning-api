package co.istad.elearningapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class ResourceHandlers implements WebMvcConfigurer {

    @Value("${media.server-path}")
    private String serverPath;

    @Value("${media.client-path}")
    private String clientPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(clientPath)
                .addResourceLocations("file:"+serverPath);
    }
}
