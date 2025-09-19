
package com.example.Hospital._Appointment_.Bookings;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class allcontroler {

@Autowired
PatientRepository p;

@Autowired
DoctorRepository d;

@Autowired
AppointmentRepository a;


@RequestMapping("hospital")
String homes(){
    return "home";
}


@RequestMapping("/doctors")
String listDoctors(Model model) {
    model.addAttribute("doc", d.findAll());
    return "doctors";
}

@RequestMapping("/appointments/new/{doctorId}")
    String newAppointment(@PathVariable Long doctorId, Model model) {
        model.addAttribute("doctor", d.findById(doctorId).orElse(null));
        model.addAttribute("patient", new patient());
    return "appointment-form";
}

 @PostMapping("/appointments/save/{doctorId}")
    public String saveAppointment(
        @PathVariable Long doctorId,                      
        @ModelAttribute patient patient,
        @RequestParam String date) {
                                    
        patient savedPatient = p.save(patient);
        doctor doctor = d.findById(doctorId).orElse(null);

        appointment s=new appointment();
        s.setPatient(patient);
        s.setDoctor(doctor);
        s.setDate(date);
        s.setStatus("pending");
        a.save(s);
        return "redirect:/appointments";
    }

    @RequestMapping("/appointments")
    public String viewAppointments(Model model) {
        model.addAttribute("appointments", a.findAll());
        return "appointments";
    }

    @RequestMapping("/appointments/confirm/{id}")
    public String confirmAppointment(@PathVariable Long id) {
        appointment appt = a.findById(id).orElse(null);
        if (appt != null) {
            appt.setStatus("Confirmed");
            a.save(appt);
        }
        return "redirect:/appointments";
    }

    @RequestMapping("/home")
    String homepage(){
        return "home";
    }


}
