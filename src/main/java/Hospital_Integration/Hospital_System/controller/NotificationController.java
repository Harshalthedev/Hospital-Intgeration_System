package Hospital_Integration.Hospital_System.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import Hospital_Integration.Hospital_System.model.NotificationMessage;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void notifyDoctor(String doctorId, String message) {
        messagingTemplate.convertAndSend("/topic/doctor/" + doctorId,
            new NotificationMessage(doctorId, message));
    }

    public void notifyUser(String userId, String message) {
        messagingTemplate.convertAndSend("/topic/user/" + userId,
            new NotificationMessage(userId, message));
    }
}

