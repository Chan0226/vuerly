package vuerlyServer.vuerly.sms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vuerlyServer.vuerly.sms.dto.MessageDTO;
import vuerlyServer.vuerly.sms.dto.SmsResponseDTO;
import vuerlyServer.vuerly.sms.service.SmsService;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/sms/send")
    public SmsResponseDTO sendSms(@RequestBody MessageDTO messageDto) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        SmsResponseDTO responseDto = smsService.sendSms(messageDto);
        return responseDto;
    }
}