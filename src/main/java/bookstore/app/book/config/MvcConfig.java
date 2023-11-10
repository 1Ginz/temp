package bookstore.app.book.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;
@AllArgsConstructor
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private final AppConfig appConfig;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        Path uploadDir = Paths.get("./" + appConfig.getFileUploadPath());
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        registry
                .addResourceHandler(String.format("/%s/**", appConfig.getFileUploadPath()))
                .addResourceLocations("file:/" + uploadPath + "/");
        if (!registry.hasMappingForPattern("/assets/**")) {
            registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
        }
    }


}
