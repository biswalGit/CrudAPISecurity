package com.pit.crudapp.controller;

import com.pit.crudapp.entity.Hospital;
import com.pit.crudapp.response.ApiResponse;
import com.pit.crudapp.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hospital")
public class CrudController {

    @Autowired
    CrudService crudService;

    @Autowired
    ObjectMapper objectMapper;


    @GetMapping("/all")
    public ResponseEntity<String> getAllHospital() {
        List<Hospital> list = crudService.getAllHospital();
        String json = objectMapper.writeValueAsString(list);
        return ResponseEntity.ok(json);

    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<String> getHospital(@PathVariable int id) {
        Optional<Hospital> hospital = crudService.getHospital(id);
        String json = objectMapper.writeValueAsString(hospital);
        return ResponseEntity.ok(json);


    }

    @PostMapping("/add")
    public ResponseEntity<String> addHospital(@RequestBody Hospital hospital) {

        Hospital hospital1 = crudService.addHospital(hospital);
        String json = objectMapper.writeValueAsString(hospital1);
        return ResponseEntity.ok(json);

    }

    @PutMapping("/update")
    public ResponseEntity<String> updateHospital(@RequestBody Hospital hospital) {
        Hospital hospital1 = crudService.updateHospital(hospital);
        String json = objectMapper.writeValueAsString(hospital1);
        return ResponseEntity.ok(json);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteHospital(@PathVariable int id) {
        String deleteStatus =crudService.deleteHospital(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse(id, deleteStatus));
    }

}
