package com.pliskin.api.mapper;

import com.pliskin.model.PatientHistory;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Created by aleksandrpliskin on 27.05.16.
 */
@Component
public class PatientHistoriesToStringMapper implements Function<PatientHistory, String> {
    @Override
    public String apply(PatientHistory patientHistory) {

        String cost = patientHistory.getCost() != null ? patientHistory.getCost().toString() : "нет";
        String description = patientHistory.getDescription() != null ? patientHistory.getDescription() : "нет";

        return "Врач: " + patientHistory.getDoctorSchedule().getDoctor().getFio() + "\n"
                + " Дата: " + patientHistory.getDate() + "\n"
                + " Цена: " + cost + "\n"
                + " Описание: " + description;
    }
}
