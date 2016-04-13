package com.pliskin.service;

import com.pliskin.forms.AppointmentCreationForm;

/**
 * Created by aleksandrpliskin on 14.04.16.
 */
public interface PatientHistoryService {
    void createHistory(AppointmentCreationForm form, String date);
}
