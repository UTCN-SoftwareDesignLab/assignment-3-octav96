package bookStore.service;

import bookStore.dto.ConsultationDTO;
import bookStore.dto.PatientDTO;
import bookStore.entity.Consultation;
import bookStore.entity.Patient;
import bookStore.entity.User;

import java.util.Date;
import java.util.List;

public class CachingConsultationService implements ConsultationService{
    private ConsultationService origin;

    public CachingConsultationService(ConsultationService origin) {
        this.origin = origin;
    }

    @Override
    public Consultation create(ConsultationDTO consultationDTO) {
        return origin.create(consultationDTO);
    }

    @Override
    public List<Consultation> findAll() {
        return origin.findAll();
    }

    /*@Override
    public void deleteConsultation(Long id) {
        origin.deleteConsultation(id);
    }*/

    @Override
    public void delete(Long id) {
        origin.delete(id);
    }

    @Override
    public User getOne(Long id) {
        return origin.getOne(id);
    }

    @Override
    public List<Consultation> findAllByPatient(PatientDTO patientDTO) {
        return origin.findAllByPatient(patientDTO);
    }

    @Override
    public void updateConsultation(String detail, Long id) {
        origin.updateConsultation(detail, id);
    }

    @Override
    public Consultation findByDoctorAndPatientAndDate(User doctor, Patient patient, Date date) {
        return origin.findByDoctorAndPatientAndDate(doctor, patient, date);
    }


}
