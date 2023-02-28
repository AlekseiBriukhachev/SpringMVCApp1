package com.aleksei.springcourse.FirstRestApp.controllers;

import com.aleksei.springcourse.FirstRestApp.dto.PersonDTO;
import com.aleksei.springcourse.FirstRestApp.models.Person;
import com.aleksei.springcourse.FirstRestApp.services.PeopleService;
import com.aleksei.springcourse.FirstRestApp.utils.PersonErrorResponse;
import com.aleksei.springcourse.FirstRestApp.utils.PersonNotCreatedException;
import com.aleksei.springcourse.FirstRestApp.utils.PersonNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final ModelMapper modelMapper;

    @Autowired
    public PeopleController(PeopleService peopleService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<PersonDTO> getPeople() {
        return peopleService.findAll().stream()
                .map(this::convertToPersonDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int id) {
        return convertToPersonDTO(peopleService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(errorMsg.toString());
        }

        peopleService.save(convertToPersonDTO(personDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException ex){
        PersonErrorResponse response = new PersonErrorResponse(
                "Person with this id is not found",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException ex){
        PersonErrorResponse response = new PersonErrorResponse(
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Person convertToPersonDTO(PersonDTO personDTO) {

        return modelMapper.map(personDTO, Person.class);
    }
    private PersonDTO convertToPersonDTO(Person person){
        return modelMapper.map(person, PersonDTO.class);
    }

}
