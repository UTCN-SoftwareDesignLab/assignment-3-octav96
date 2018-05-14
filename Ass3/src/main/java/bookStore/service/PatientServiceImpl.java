package bookStore.service;

import bookStore.dto.PatientDTO;
import bookStore.entity.Patient;
import bookStore.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PatientServiceImpl implements PatientService{

    PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient create(PatientDTO patientDTO) {
        Patient patient = new Patient(patientDTO.getName(), patientDTO.getIdentityCardNumber(),
                patientDTO.getPersonalNumericCode(), patientDTO.getDateOfBirth(), patientDTO.getAddress());
        return patientRepository.save(patient);
    }

    @Override
    public void updatePatient(String personalNumericCode, String address) {
        patientRepository.updatePatient(personalNumericCode, address);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return patientRepository.findById(id);
    }
}
