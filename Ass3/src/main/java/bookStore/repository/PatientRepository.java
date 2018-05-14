package bookStore.repository;

import bookStore.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findAll();

    Patient save(Patient patient);

    Patient findByPersonalNumericCode(String personalNumericCode);

    Patient getOne(Long id);

    @Transactional
    @Modifying
    @Query("update Patient p set p.address = ?2 where p.personalNumericCode = ?1")
    void updatePatient(String personalNumericCode, String address);

    Optional<Patient> findById(Long id);
}
