package es.thehillogy.thefarmerkitbackend;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.unit.DataSize;

@SpringBootApplication
@ComponentScan
@EnableConfigurationProperties
@EnableJpaRepositories
public class Main {
	
	public static void main(String... args)
	{
		SpringApplication.run(Main.class);
	}
	
	@Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(50L));
        factory.setMaxRequestSize(DataSize.ofMegabytes(50L));
        return factory.createMultipartConfig();
    }
}
