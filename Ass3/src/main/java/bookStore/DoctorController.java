package bookStore;

import bookStore.dto.ConsultationDTO;
import bookStore.dto.PatientDTO;
import bookStore.entity.Consultation;
import bookStore.entity.Patient;
import bookStore.entity.User;
import bookStore.service.ConsultationService;
import bookStore.service.PatientService;
import bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    private UserService userService;
    @Autowired
    private ConsultationService consultationService;
    @Autowired
    private PatientService patientService;

    @GetMapping("/doctor")
    public String doctor(){
        return "/doctor";
    }

    @GetMapping("/update_consultation")
    public String updateConsultation(Model model){
        model.addAttribute("consultation", new ConsultationDTO());
        return "update_consultation";
    }

    @PostMapping("/update_consultation")
    public String updateConsultationResult(@ModelAttribute("consultation") @Valid ConsultationDTO consultationDTO,
                                           BindingResult bindingResult){
        // consultationService.updateConsultation(consultation.getId())
        User doctor = userService.findById(consultationDTO.getDoctorId()).orElse(null);
        Patient patient = patientService.findById(consultationDTO.getPatientId()).orElse(null);
        //Consultation consultation = consultationService.findAll().get(0);
        Consultation consultation = consultationService
                .findByDoctorAndPatientAndDate(doctor, patient, consultationDTO.getDate());
        consultationService.updateConsultation(consultation.getDetail() + ", " + consultationDTO.getDetail(),
                consultation.getId());
        return "update_consultation_result";
    }

    @GetMapping("/list_all_past_consultations")
    public String listAllPastConsultations(Model model){
        model.addAttribute("patient", new PatientDTO());
        return "list_all_past_consultations";
    }

    @PostMapping("/list_all_past_consultations")
    public ModelAndView listAllPastConsultationsResult(@ModelAttribute("patient") PatientDTO patientDTO){
        // final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        List<Consultation> consultations = consultationService.findAll();
        System.out.println(patientDTO.getPersonalNumericCode());
        Date date = new Date();
        for(int i = 0;i<consultations.size();i++){
            System.out.println(consultations.get(i).getPatient().getPersonalNumericCode() + " " +
                    patientDTO.getPersonalNumericCode());
            System.out.println(consultations.get(i).getDate() + " " + date);
            if(!consultations.get(i).getPatient().getPersonalNumericCode().equals(patientDTO.getPersonalNumericCode()))
                consultations.remove(i);
            else if(consultations.get(i).getDate().after(date)){
                consultations.remove(i);
            }
        }

        ModelAndView mav = new ModelAndView("list_all_past_consultations_result");
        mav.addObject("consultations", consultations);;
        return mav;

    }

}
