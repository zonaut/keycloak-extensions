package com.zonaut.keycloak.extensions.domain;

import org.keycloak.connections.jpa.entityprovider.JpaEntityProvider;

import java.util.Collections;
import java.util.List;

public class CustomJpaEntityProvider implements JpaEntityProvider {

    @Override
    public List<Class<?>> getEntities() {
        return Collections.singletonList(Product.class);
    }

    @Override
    public String getChangelogLocation() {
    	return "META-INF/custom-changelog.xml";
    }
    
    @Override
    public void close() {
    }

    @Override
    public String getFactoryId() {
        return CustomJpaEntityProviderFactory.ID;
    }

}
