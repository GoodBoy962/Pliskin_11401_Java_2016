package com.pliskin.controller;

import com.itextpdf.text.Document;
import com.pliskin.forms.AppointmentChangeForm;
import com.pliskin.model.Doctor;
import com.pliskin.model.PatientHistory;
import com.pliskin.service.DoctorScheduleService;
import com.pliskin.service.DoctorService;
import com.pliskin.service.PatientHistoryService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    DoctorScheduleService doctorScheduleService;

    @Autowired
    PatientHistoryService patientHistoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getDoctorIndex(Model model) {
        Doctor doctor = doctorService.getDoctor();
        model.addAttribute("doctor", doctor);
        model.addAttribute("isTimetable", doctorScheduleService.getDoctorSchedule(doctor));
        return "/doctor";
    }

    @RequestMapping(value = "/appointments", method = RequestMethod.GET)
    public String getAppointments(Model model) {
        Doctor doctor = doctorService.getDoctor();
        model.addAttribute("doctor", doctor);
        model.addAttribute("appointments", patientHistoryService.getHistoriesByDoctor(doctor));
        return "/appointments";
    }

    @RequestMapping(value = "/appointments/{id}/change", method = RequestMethod.GET)
    public String getFormToChangeAppointment(Model model, @PathVariable("id") Long id) {
        model.addAttribute("changeForm", new AppointmentChangeForm(patientHistoryService.getHistoryById(id)));
        model.addAttribute("appointmentId", id);
        return "/change-appointment";
    }

    @RequestMapping(value = "/appointments/{id}/change", method = RequestMethod.POST)
    public String changePatientHistory(@PathVariable("id") Long id,
                                       @ModelAttribute("changeForm") @Valid AppointmentChangeForm form,
                                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/change-appointment";
        }
        Document document = patientHistoryService.changeAppointment(id, form);
        model.addAttribute(document);
        return "redirect:/doctor/appointments/" + id + "/download";
    }

    @Value("/Users/aleksandrpliskin/Desktop/static/sem1/pdf/")//TODO
    private String path;

    @RequestMapping(value = "appointments/{id}/download")
    public void getLogFile(HttpSession session, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        PatientHistory patientHistory = patientHistoryService.getHistoryById(id);
        try {
            String filePathToBeServed = path + patientHistory.getId() + '_' + patientHistory.getPatient().getFio() + ".pdf";//complete file name with path;
            File fileToDownload = new File(filePathToBeServed);
            InputStream inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename=" + id + ".pdf");
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
