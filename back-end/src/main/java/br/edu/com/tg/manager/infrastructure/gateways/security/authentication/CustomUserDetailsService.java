package br.edu.com.tg.manager.infrastructure.gateways.security.authentication;

import br.edu.com.tg.manager.core.ports.repositories.AdministratorRepository;
import br.edu.com.tg.manager.core.ports.repositories.ProfessorRepository;
import br.edu.com.tg.manager.core.ports.repositories.StudentRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AdministratorRepository administratorRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;

    public CustomUserDetailsService(
            AdministratorRepository administratorRepository,
            ProfessorRepository professorRepository,
            StudentRepository studentRepository
    ) {
        this.administratorRepository = administratorRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        var adminOpt = administratorRepository.findByEmail(username);

        if(adminOpt.isPresent()) {
            var admin = adminOpt.get();
            List<GrantedAuthority> authorities = Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_ADMIN")
            );

            return new CustomUserDetails(
                    admin.getEmail(),
                    admin.getPassword(),
                    authorities,
                    admin.getUserAccount().getStatus()
            );
        }

        var profOpt = professorRepository.findByEmail(username);
        if(profOpt.isPresent()) {
            var prof = profOpt.get();
            List<GrantedAuthority> authorities = Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_PROFESSOR")
            );

            return new CustomUserDetails(
                    prof.getEmail(),
                    prof.getPassword(),
                    authorities,
                    prof.getUserAccount().getStatus()
            );
        }

        var studentOpt = studentRepository.findByEmail(username);
        if(studentOpt.isPresent()) {
            var student = studentOpt.get();

            if(student.getUserAccount() == null) {
                throw new UsernameNotFoundException(
                        "Conta de aluno pendente de registro: " + username
                );
            }

            List<GrantedAuthority> authorities = Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_STUDENT")
            );

            return new CustomUserDetails(
                    student.getEmail(),
                    student.getPassword(),
                    authorities,
                    student.getUserAccount().getStatus()
            );
        }

        throw new UsernameNotFoundException(
                "Usuário não encontrado com o email: " + username
        );
    }
}
