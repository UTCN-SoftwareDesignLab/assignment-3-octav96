package bookStore.service;

import bookStore.dto.ConsultationDTO;
import bookStore.dto.PatientDTO;
import bookStore.entity.Consultation;
import bookStore.entity.Patient;
import bookStore.entity.User;
import bookStore.repository.ConsultationRepository;
import bookStore.repository.PatientRepository;
import bookStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ConsultationServiceImpl implements ConsultationService {

    ConsultationRepository consultationRepository;

    PatientRepository patientRepository;

    UserRepository userRepository;
    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository,
                                   PatientRepository patientRepository,
                                   UserRepository userRepository) {
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Consultation create(ConsultationDTO consultationDTO) {
        Consultation consultation = new Consultation(userRepository.getOne(consultationDTO.getDoctorId()),
                patientRepository.getOne(consultationDTO.getPatientId()),
                consultationDTO.getDate(), consultationDTO.getDetail());

        return consultationRepository.save(consultation);
    }

    @Override
    public List<Consultation> findAll() {
        return consultationRepository.findAll();
    }

    /*@Override
    public void deleteConsultation(Long id) {
        consultationRepository.delete(id);
    }*/

    @Override
    public void delete(Long id) {
        consultationRepository.deleteById(id);
    }

    @Override
    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<Consultation> findAllByPatient(PatientDTO patientDTO) {
        Patient patient = patientRepository.findByPersonalNumericCode(patientDTO.getPersonalNumericCode());
        return consultationRepository.findAllByPatient(patient);
    }

    @Override
    public void updateConsultation(String detail, Long id) {
        consultationRepository.updateConsultation(detail, id);
    }

    @Override
    public Consultation findByDoctorAndPatientAndDate(User doctor, Patient patient, Date date) {
        return consultationRepository.findByDoctorAndPatientAndDate(doctor, patient, date);
    }
}
