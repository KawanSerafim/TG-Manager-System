package br.edu.com.tg.manager.infrastructure.web.dtos.responses;

import br.edu.com.tg.manager.core.domain.entities.UserAccount;
import br.edu.com.tg.manager.core.domain.enums.UserAccountStatus;

public record LoginResponse(
        String userName,
        String email,
        UserAccountStatus userAccountStatus
) {}