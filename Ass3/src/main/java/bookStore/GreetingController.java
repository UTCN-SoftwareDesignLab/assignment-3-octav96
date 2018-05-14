package bookStore;
import bookStore.message.Message;
import bookStore.message.TransferMessage;
import bookStore.service.PatientService;
import bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class GreetingController {

    @Autowired
    PatientService patientService;
    @Autowired
    UserService userService;

    @MessageMapping("/chat/{topic}")
    @SendTo("/topic/consultations")
    public TransferMessage consultation(@DestinationVariable("topic") String topic, org.springframework.messaging.Message<Message> message1) throws Exception {
        Message message = message1.getPayload();
        System.out.println("bou");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(message.getDate());
        return new TransferMessage(patientService.findById(message.getPatientId()).get().getName(),
               userService.findById(message.getDoctorId()).get().getUsername(), date);
    }

}