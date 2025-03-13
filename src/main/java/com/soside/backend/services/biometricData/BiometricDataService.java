package com.soside.backend.services.biometricData;

import com.soside.backend.models.BiometricData;
import com.soside.backend.models.Person;
import com.soside.backend.repositories.BiometricDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BiometricDataService implements IBiometricDataService {

    @Autowired
    private BiometricDataRepository biometricDataRepository;

    @Override
    public BiometricData addBiometricData(BiometricData biometricData) {
        return biometricDataRepository.save(biometricData);
    }

    @Override
    public BiometricData getBiometricDataById(Long id) {
        Optional<BiometricData> biometricData = biometricDataRepository.findById(id);
        return biometricData.orElseThrow(() -> new RuntimeException("BiometricData not found for id: " + id));
    }

    /**
     * @param nationalityID
     * @return
     */
    @Override
    public BiometricData getBiometricDataByPersonNationalityID(String nationalityID) {
        return biometricDataRepository.findByPersonNationalityID(nationalityID);
    }


    @Override
    public List<BiometricData> getAllBiometricData() {
        return biometricDataRepository.findAll();
    }

    @Override
    public void deleteBiometricData(Long id) {
        biometricDataRepository.deleteById(id);
    }
}
