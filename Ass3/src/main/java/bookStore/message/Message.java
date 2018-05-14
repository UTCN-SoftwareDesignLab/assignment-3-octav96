package bookStore.message;

import java.util.Date;

public class Message {
    Long doctorId;
    Long patientId;
    String date;

    public Message() {
    }

    public Message(Long doctorId, Long patientId, String date) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
