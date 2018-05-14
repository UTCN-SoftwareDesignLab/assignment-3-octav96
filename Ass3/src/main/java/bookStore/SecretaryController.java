package bookStore;

import bookStore.dto.ConsultationDTO;
import bookStore.dto.PatientDTO;
import bookStore.dto.UserDTO;
import bookStore.entity.Consultation;
import bookStore.entity.Patient;
import bookStore.entity.User;
import bookStore.service.ConsultationService;
import bookStore.service.PatientService;
import bookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Controller
public class SecretaryController {
    @Autowired
    private ConsultationService consultationService;
    @Autowired
    private PatientService patientService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/secretary")
    public String secretary(){
        return "/secretary";
    }

    @GetMapping("/add_consultation")
    public String addConsultation(Model model){
        model.addAttribute("consultation", new ConsultationDTO());
            return "add_consultation";
    }

    @PostMapping("/add_consultation")
    public String addConsultationResult(@ModelAttribute("consultation") @Valid ConsultationDTO consultationDTO,
                                        BindingResult bindingResult){

        User user = userService.findById(consultationDTO.getDoctorId()).orElse(null);
     //   System.out.print(user.getId());
        if(user == null){
            bindingResult.rejectValue("doctorId", "error.consultation",
                    "DOCTOR DOES NOT EXIST!");
            return "add_consultation";
        }
//        System.out.println(userService.getOne(consultationDTO.getPatientId()).getUsername());
        Patient patient = patientService.findById(consultationDTO.getPatientId()).orElse(null);
        if(patient == null){
            bindingResult.rejectValue("patientId", "error.consultation",
                    "PATIENT DOES NOT EXIST");
            return "add_consultation";
        }
        consultationService.create(consultationDTO);
        return "add_consultation_result";
    }

   /* @PostMapping("/add_consultation")
    @MessageMapping("/topic/consultations")
    public String addConsultationResult(@ModelAttribute("consultation") @Valid ConsultationDTO consultationDTO,
                                        BindingResult bindingResult, Model model, Principal principal){

        User user = userService.findById(consultationDTO.getDoctorId()).orElse(null);
     //   System.out.print(user.getId());
        if(user == null){
            bindingResult.rejectValue("doctorId", "error.consultation",
                    "DOCTOR DOES NOT EXIST!");
            return "add_consultation";
        }

        Patient patient = patientService.findById(consultationDTO.getPatientId()).orElse(null);
        if(patient == null){
            bindingResult.rejectValue("patientId", "error.consultation",
                    "PATIENT DOES NOT EXIST");
            return "add_consultation";
        }
        consultationService.create(consultationDTO);
        HelloMessage message = new HelloMessage();
        message.setName(patientService.findById(consultationDTO.getPatientId()).get().getName() + " to doctor "
        + userService.findById(consultationDTO.getDoctorId()).get().getUsername() + " at date " +
                consultationDTO.getDate());
        return "add_consultation_result";
    }*/
    @GetMapping("add_patient")
    public String addPatient(Model model){
        model.addAttribute("patient", new PatientDTO());
        return "add_patient";
    }

    @PostMapping("add_patient")
    public String addPatientResult(@ModelAttribute("patient") PatientDTO patientDTO){
        patientService.create(patientDTO);
        return "add_patient_result";
    }

    @GetMapping("/update_patient")
    public String updatePatient(Model model){
        model.addAttribute("patient", new PatientDTO());
        return "update_patient";
    }

    @PostMapping("/update_patient")
    public String updatePatientResult(@ModelAttribute("patient") PatientDTO patientDTO){
        patientService.updatePatient(patientDTO.getPersonalNumericCode(), patientDTO.getAddress());
        return "update_patient_result";
    }

    @GetMapping("/list_all_patients")
    public ModelAndView listAllPatients(){
        List<Patient> patients = patientService.findAll();
        ModelAndView mav = new ModelAndView("list_all_patients");
        mav.addObject("patients", patients);
        return mav;
    }

    @GetMapping("/list_all_consultations")
    public ModelAndView listAllConsultations(){
        List<Consultation> consultations = consultationService.findAll();
        ModelAndView mav = new ModelAndView("list_all_consultations");
        mav.addObject("consultations", consultations);
        return mav;
    }

    @GetMapping("/delete_consultation")
    public String deleteConsultation(Model model){
        model.addAttribute("consultation", new Consultation());
        return "delete_consultation";
    }

    @PostMapping("/delete_consultation")
    public String deleteConsultationResult(@ModelAttribute("consultation") Consultation consultation){
        consultationService.delete(consultation.getId());
        return "delete_consultation_result";
    }

    @GetMapping("/list_all_consultations_before_today")
    public ModelAndView listAllConsultationsBeforeToday(){
       // final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        List<Consultation> consultations = consultationService.findAll();
        Date date = new Date();
        for(int i = 0;i<consultations.size();i++){
            if(consultations.get(i).getDate().after(date)){
                consultations.remove(i);
            }
        }
        ModelAndView mav = new ModelAndView("list_all_consultations");
        mav.addObject("consultations", consultations);;
        return mav;

    }

    @GetMapping("/list_all_consultations_for_patient")
    public String listAllConsultationsForPatient(Model model){
        model.addAttribute("patient", new PatientDTO());
        return "list_all_consultations_for_patient";
    }

    @PostMapping("/list_all_consultations_for_patient")
    public ModelAndView listAllConsultationsForPatientResult(@ModelAttribute("patient") PatientDTO patientDTO){
        ModelAndView mav = new ModelAndView("list_all_consultations");
        List<Consultation> consultations = consultationService.findAllByPatient(patientDTO);
        mav.addObject("consultations", consultations);
        return mav;
    }


}
