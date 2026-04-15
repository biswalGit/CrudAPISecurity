package com.pit.crudapp.service;

import com.pit.crudapp.entity.Hospital;
import com.pit.crudapp.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrudService {

    @Autowired
    CrudRepository crudRepository;



    public List<Hospital> getAllHospital() {
        return crudRepository.findAll();

    }

    public Optional<Hospital> getHospital(int id) {
        return crudRepository.findById(id);
    }

    public Hospital addHospital(Hospital hospital) {
        return crudRepository.save(hospital);
    }

    public Hospital updateHospital(Hospital hospital) {
        boolean exist = crudRepository.existsById(hospital.getId());
        Hospital update = new Hospital();
        update.setCity(hospital.getCity());
        update.setName(hospital.getName());
        update.setRatings(hospital.getRatings());
        update.setId(hospital.getId());
        if (exist) {
            crudRepository.save(update);
        } else {
            throw new RuntimeException("Hospital not found");
        }
        return hospital;

    }

    public String deleteHospital(int id) {
        try {
            if(crudRepository.existsById(id)){
                crudRepository.deleteById(id);
                return "Deleted successfully";
            }else{
                return "Hospital not found";
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }



    }
}
