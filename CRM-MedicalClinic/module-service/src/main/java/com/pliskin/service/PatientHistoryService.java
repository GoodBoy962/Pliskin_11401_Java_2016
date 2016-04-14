package com.pliskin.service;

import com.pliskin.model.Credentials;
import com.pliskin.model.PatientHistory;

import java.util.List;

/**
 * Created by aleksandrpliskin on 14.04.16.
 */
public interface PatientHistoryService {
    void createHistory(String fio, String wDay, String time, String date);

    List<PatientHistory> getHistories(Credentials credentials);
}
