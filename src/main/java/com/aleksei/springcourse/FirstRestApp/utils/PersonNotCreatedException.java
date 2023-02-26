package com.aleksei.springcourse.FirstRestApp.utils;

public class PersonNotCreatedException extends RuntimeException {
    public PersonNotCreatedException(String message){
        super(message);
    }
}
