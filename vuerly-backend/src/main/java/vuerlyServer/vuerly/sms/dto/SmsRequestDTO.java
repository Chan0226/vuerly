package vuerlyServer.vuerly.sms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vuerlyServer.vuerly.sms.dto.MessageDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class SmsRequestDTO {
    private String type;
    private String contentType;
    private String countryCode;
    private String from;
    private String content;
    private List<MessageDTO> messages;
}

