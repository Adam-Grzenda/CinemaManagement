package pl.put.CinemaManagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

@Configuration //#TODO zamienić na coś nie deprecated?
public class MyRepositoryRestConfigurerAdapter extends RepositoryRestConfigurerAdapter {

    private final EntityManager entityManager;

    public MyRepositoryRestConfigurerAdapter(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));
    }

}