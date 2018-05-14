package bookStore.repository;

import bookStore.dto.PatientDTO;
import bookStore.entity.Consultation;
import bookStore.entity.Patient;
import bookStore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    Consultation save(Consultation consultation);

    List<Consultation> findAll();

   /* @Transactional
    void delete(Long id);
*/
    List<Consultation> findAllByPatient(Patient patient);
  // @Query("SELECT * FROM Patient p where p.patientId = ?1")
   //List<Consultation> findAllPastConsultationsByPatientId(Long patientId);

    Consultation findByDoctorAndPatientAndDate(User doctor, Patient patient, Date date);


    @Transactional
    void deleteById(Long id);


    @Transactional
    @Modifying
    @Query("update Consultation c set c.detail = ?1 where c.id = ?2")
    void updateConsultation(String detail, Long id);
    //@Query("update User u set u.address = ?1 where u.username = ?2")
}
