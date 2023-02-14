package vuerlyServer.vuerly.sms.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SmsResponseDTO {
    private String requestId;
    private LocalDateTime requestTime;
    private String statusCode;
    private String statusName;
    private String smsConfirmNum;

}
