package com.pedroostanik.rifa.exception;

public class SlotUnavailableException extends RuntimeException {
    public SlotUnavailableException() {
        super("Slot is no longer available");
    }
}
