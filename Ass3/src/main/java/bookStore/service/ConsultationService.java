package bookStore.service;

import bookStore.dto.ConsultationDTO;
import bookStore.dto.PatientDTO;
import bookStore.entity.Consultation;
import bookStore.entity.Patient;
import bookStore.entity.User;

import java.util.Date;
import java.util.List;

public interface ConsultationService {
    public Consultation create(ConsultationDTO consultationDTO);

    public List<Consultation> findAll();

   // public void deleteConsultation(Long id);

    public void delete(Long id);

    public User getOne(Long id);

    public List<Consultation> findAllByPatient(PatientDTO patientDTO);

    public void updateConsultation(String detail, Long id);
    public Consultation findByDoctorAndPatientAndDate(User doctor, Patient patient, Date date);
}
