package pl.put.CinemaManagement.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

@RequiredArgsConstructor
@Slf4j
@Configuration
public class CustomRepositoryRestConfiguration {

    @Value("${angular.client}")
    String angularClient;

    private final EntityManager entityManager;

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
                config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));
                cors.addMapping("/**")
                        .allowedMethods("GET", "PUT", "POST", "DELETE")
                        .allowedHeaders("*")
                        .allowedOrigins(angularClient);
            }
        };
    }
}
