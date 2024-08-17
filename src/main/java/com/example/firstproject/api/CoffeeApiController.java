package com.example.firstproject.api;


import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CoffeeApiController {

    @Autowired
    private CoffeeRepository coffeeRepository;


    @GetMapping("/api/coffees")
    public List<Coffee> index(){
        return coffeeRepository.findAll();
    }

    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> index(@PathVariable("id") Long id){
        Coffee coffee = coffeeRepository.findById(id).orElse(null);

        return (coffee!=null) ?
                ResponseEntity.status(HttpStatus.OK).body(coffee) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> create(@RequestBody CoffeeDto coffeeDto){
        Coffee coffee = coffeeDto.toEntity();


        if(coffee.getId()!=null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

        Coffee save = coffeeRepository.save(coffee);
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }


    //Patch
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable("id") Long id, @RequestBody CoffeeDto coffeeDto){

        Coffee coffee = coffeeDto.toEntity();
        log.info("id : {} coffe: {} ",id,coffee.toString());

        Coffee target = coffeeRepository.findById(coffee.getId()).orElse(null);

        if(target==null||id!=coffee.getId()){
            log.info("잘못된 요청입니다 ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

        target.patch(coffee);

        Coffee save = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(save);


    }


    //DELETE
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable("id") Long id){
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

        coffeeRepository.delete(target);

        return ResponseEntity.status(HttpStatus.OK).body(null);

    }






}
