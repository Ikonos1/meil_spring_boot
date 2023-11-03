package com.grapplesoft.meil_backend.models.request;

public record BaseSendMail(
        String subject,
        String message,
        String receipent
) {
}
