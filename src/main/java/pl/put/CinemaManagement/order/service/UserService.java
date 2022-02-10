package pl.put.CinemaManagement.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Service;
import pl.put.CinemaManagement.model.Client;
import pl.put.CinemaManagement.repository.ClientRepository;

import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final ClientRepository clientRepository;

    Client getClientFromProvider(Principal principal) {
        AccessToken accessToken = getAccessToken(principal);
        String externalId = accessToken.getSubject();
        log.info("Retrieved user with external ID: : " + externalId);
        return clientRepository.getClientByExternalId(externalId).orElseGet(
                () ->
                        clientRepository.save(Client.fromExternalId(externalId, accessToken.getName()))
        );
    }

    private AccessToken getAccessToken(Principal principal) {
        KeycloakAuthenticationToken keycloakToken = (KeycloakAuthenticationToken) principal;
        return keycloakToken.getAccount().getKeycloakSecurityContext().getToken();
    }

}
