package bookStore.service;

import bookStore.dto.PatientDTO;
import bookStore.entity.Patient;
import bookStore.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> findAll();

    Patient create(PatientDTO patientDTO);
    void updatePatient(String personalNumericCode, String address);

    Optional<Patient> findById(Long id);

}
