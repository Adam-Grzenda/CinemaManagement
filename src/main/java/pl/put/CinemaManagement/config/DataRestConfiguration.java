package pl.put.CinemaManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import pl.put.CinemaManagement.model.Movie;

@Configuration
public class DataRestConfiguration {
    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return RepositoryRestConfigurer.withConfig(
                (config) -> config.exposeIdsFor(Movie.class)
        );
    }
}
