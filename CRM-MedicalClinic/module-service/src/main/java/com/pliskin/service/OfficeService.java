package com.pliskin.service;

import com.pliskin.forms.OfficeAdminCreationForm;
import com.pliskin.model.MedicalClinic;
import com.pliskin.model.Office;

import java.util.List;

/**
 * Created by aleksandrpliskin on 06.04.16.
 */
public interface OfficeService {

    List<Office> getMedClinicOffices(MedicalClinic medicalClinic);

    void createOfficeAndAdmin(OfficeAdminCreationForm form, Long medicalClinicId);

    Office getOfficeByAdminCredentials();

    Office getOffice(Long id);

    List<Office> getOfficesByCity(String city);

    Office getOfficeByCityLikeAndAddress(String city, String address);

    Office getOfficeByCityAndAddress(String city, String address);

    List<Office> getOfficesLikeCity(String city);

    List<Office> getOfficeByCity(String city);
}
