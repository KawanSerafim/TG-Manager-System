package br.edu.com.tg.manager.infrastructure.configuration;

import br.edu.com.tg.manager.core.domain.entities.Administrator;
import br.edu.com.tg.manager.core.domain.entities.UserAccount;
import br.edu.com.tg.manager.core.domain.enums.UserAccountStatus;
import br.edu.com.tg.manager.core.ports.repositories.AdministratorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {
    private static final String ADMIN_EMAIL = "admin@tgmanager.com";
    private static final String ADMIN_PASSWORD = "admin123";

    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(
            AdministratorRepository administratorRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(administratorRepository.findByEmail(ADMIN_EMAIL).isPresent()){
            return;
        }

        String hashedPassword = passwordEncoder.encode(ADMIN_PASSWORD);

        UserAccount adminAccount = new UserAccount();
        adminAccount.setEmail(ADMIN_EMAIL);
        adminAccount.setPassword(hashedPassword);
        adminAccount.setStatus(UserAccountStatus.ACTIVE);

        Administrator administrator = new Administrator(
                "Default Administrador",
                adminAccount
        );

        administratorRepository.save(administrator);
    }
}