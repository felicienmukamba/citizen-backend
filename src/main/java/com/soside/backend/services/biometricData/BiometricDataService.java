package com.soside.backend.services.biometricData;

import com.soside.backend.exceptions.BiometricDataNotFoundException;
import com.soside.backend.models.BiometricData;
import com.soside.backend.repositories.BiometricDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BiometricDataService implements IBiometricDataService {

    private final BiometricDataRepository biometricDataRepository;

    @Autowired
    public BiometricDataService(BiometricDataRepository biometricDataRepository) {
        this.biometricDataRepository = biometricDataRepository;
    }

    @Override
    public BiometricData addBiometricData(BiometricData biometricData) {
        return biometricDataRepository.save(biometricData);
    }

    @Override
    public BiometricData getBiometricDataById(Long id) {
        return biometricDataRepository.findById(id)
                .orElseThrow(() -> new BiometricDataNotFoundException("Biometric data not found with id: " + id));
    }

    @Override
    public List<BiometricData> getAllBiometricData() {
        return biometricDataRepository.findAll();
    }
    // Et dans le service
    public BiometricData getByNationalityID(String nationalityID) {
        return biometricDataRepository.findByPersonNationalityID(nationalityID)
                .orElseThrow(() -> new BiometricDataNotFoundException("No biometric data found for person with ID: " + nationalityID));
    }


    @Override
    public void deleteBiometricData(Long id) {
        if (!biometricDataRepository.existsById(id)) {
            throw new BiometricDataNotFoundException("Cannot delete. Biometric data not found with id: " + id);
        }
        biometricDataRepository.deleteById(id);
    }
}
