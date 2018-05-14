package bookStore.service;

import bookStore.dto.PatientDTO;
import bookStore.entity.Patient;

import java.util.List;
import java.util.Optional;

public class CachingPatientService implements PatientService {

    private PatientService origin;

    public CachingPatientService(PatientService origin) {
        this.origin = origin;
    }

    @Override
    public List<Patient> findAll() {
        return origin.findAll();
    }

    @Override
    public Patient create(PatientDTO patientDTO) {
        return origin.create(patientDTO);
    }

    @Override
    public void updatePatient(String personalNumericCode, String address) {
        origin.updatePatient(personalNumericCode, address);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return origin.findById(id);
    }
}
