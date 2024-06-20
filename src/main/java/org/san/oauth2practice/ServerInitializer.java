package org.san.oauth2practice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.san.oauth2practice.model.User.OauthType;
import org.san.oauth2practice.model.User.User;
import org.san.oauth2practice.repository.InMemoryUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ServerInitializer implements ApplicationRunner {
    private final InMemoryUserRepository inMemoryUserRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        inMemoryUserRepository.save(User.builder().name("AN-SO").oauthId("1234").oauthType(OauthType.GITHUB).build());
        inMemoryUserRepository.save(User.builder().name("san2").oauthId("5678").oauthType(OauthType.GITHUB).build());

        log.info("Initialized users.");
    }
}
