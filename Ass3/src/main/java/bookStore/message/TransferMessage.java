package bookStore.message;

import java.util.Date;

public class TransferMessage {
    String patientName;
    String doctorName;
    Date date;

    public TransferMessage() {
    }

    public TransferMessage(String patientName, String doctorName, Date date) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
