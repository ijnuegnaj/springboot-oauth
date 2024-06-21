package org.san.oauth2practice.httpinterface.config;

import org.springframework.web.client.RestClient;

public interface HttpInterfaceFactory {
    <S> S create(Class<S> clientClass, RestClient restClient);
}
