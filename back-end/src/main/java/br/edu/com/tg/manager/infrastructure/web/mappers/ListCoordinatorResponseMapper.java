package br.edu.com.tg.manager.infrastructure.web.mappers;

import br.edu.com.tg.manager.core.usecases.ListTgCoordinatorsCase;
import br.edu.com.tg.manager.infrastructure.web.dtos.responses.professor
        .ListCoordinatorResponse;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ListCoordinatorResponseMapper {
    public ListCoordinatorResponse toResponse(
            ListTgCoordinatorsCase.Output result
    ) {
        return new ListCoordinatorResponse(
                result.name(),
                result.registration()
        );
    }

    public List<ListCoordinatorResponse> toResponseList(
            List<ListTgCoordinatorsCase.Output> results
    ) {
        return results.stream().map(this::toResponse).toList();
    }
}