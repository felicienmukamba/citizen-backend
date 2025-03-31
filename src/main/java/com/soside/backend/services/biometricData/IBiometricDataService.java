package com.soside.backend.services.biometricData;
import com.soside.backend.models.BiometricData;
import com.soside.backend.models.Person;

import java.util.List;

public interface IBiometricDataService {

    BiometricData addBiometricData(BiometricData biometricData);
    BiometricData getBiometricDataById(Long id);

//    BiometricData getBiometricDataByPersonNationalityID(String nationalityID);

    List<BiometricData> getAllBiometricData();

    void deleteBiometricData(Long id);


}