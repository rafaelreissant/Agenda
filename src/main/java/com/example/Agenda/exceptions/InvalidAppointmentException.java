package com.example.Agenda.exceptions;

public class InvalidAppointmentException extends RuntimeException{
    public InvalidAppointmentException(String message) {
        super(message);
    }
}
