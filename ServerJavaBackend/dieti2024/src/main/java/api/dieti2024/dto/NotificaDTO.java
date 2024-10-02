package api.dieti2024.dto;

import lombok.Builder;

@Builder
public record NotificaDTO(String emailUtente, String oggettoDellaNotifica,String messaggio, int AstaId, boolean visualizzato) {

}
