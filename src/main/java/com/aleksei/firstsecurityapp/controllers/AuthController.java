package com.aleksei.firstsecurityapp.controllers;

import com.aleksei.firstsecurityapp.dto.PersonDTO;
import com.aleksei.firstsecurityapp.models.Person;
import com.aleksei.firstsecurityapp.security.JWTUtil;
import com.aleksei.firstsecurityapp.services.RegistrationService;
import com.aleksei.firstsecurityapp.utils.PersonValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService,
                          JWTUtil jwtUtil, ModelMapper modelMapper) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }
    @GetMapping("/registration")
    public String registrationPage(@RequestBody @Valid PersonDTO personDTO){
        return "/auth/registration";
    }
    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                      BindingResult bindingResult){
        Person person = convertToPerson(personDTO);

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return Map.of("message", "Error!");

        registrationService.register(person);

        String token = jwtUtil.generateToken(personDTO.getUsername());
        return Map.of("jwt_token", token);
    }
    public Person convertToPerson(PersonDTO personDTO){
        return this.modelMapper.map(personDTO, Person.class);
    }
}
