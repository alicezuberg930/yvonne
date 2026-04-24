// package server.rem.controllers;

// import org.springframework.messaging.handler.annotation.MessageMapping;
// import org.springframework.messaging.handler.annotation.SendTo;
// import org.springframework.stereotype.Controller;

// @Controller
// public class NotificationController {
//     // access from client /app/send
//     @MessageMapping("/send")
//     @SendTo("/topic/notification")
//     public String sendMessage(String mes) {
//         return mes;
//     }
// }
