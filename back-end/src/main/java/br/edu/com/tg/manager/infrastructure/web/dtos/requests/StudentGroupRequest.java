package br.edu.com.tg.manager.infrastructure.web.dtos.requests;

import br.edu.com.tg.manager.core.domain.enums.Discipline;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;

/**
 * Porta-dados de infraestrutura:
 * Carrega os dados que o coordenador de TG usa para enviar a requisição.
 */
public record StudentGroupRequest(

    String courseName,
    Discipline discipline,
    MultipartFile file
) {

    public InputStream inputStream() throws IOException {

        return this.file.getInputStream();
    }
}