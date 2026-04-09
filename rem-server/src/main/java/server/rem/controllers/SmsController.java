package server.rem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import server.rem.dtos.sms.SendSmsDto;
import server.rem.services.SmsService;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SmsController {

    private final SmsService smsService;

    @PostMapping("/twilio")
    public ResponseEntity<String> sendSmsTwilio(@RequestBody SendSmsDto dto) {
        smsService.sendSmsTwilio(dto);
        return ResponseEntity.ok("SMS sent successfully");
    }

     @PostMapping("/vonage")
     public ResponseEntity<String> sendSmsVonage(@RequestBody SendSmsDto dto) {
         smsService.sendSmsVonage(dto);
         return ResponseEntity.ok("SMS sent successfully");
     }
}