package com.example.Hospital._Appointment_.Bookings;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String date;
    String status;
    @ManyToOne
    private patient patient;

    @ManyToOne
    private doctor doctor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public patient getPatient() {
        return patient;
    }

    public void setPatient(patient patient) {
        this.patient = patient;
    }

    public doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(doctor doctor) {
        this.doctor = doctor;
    }

}
