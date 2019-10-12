package ca.mcgill.ecse223.block.controller;

public class InvalidInputException extends Exception {

private static final long serialVersionUID = -6247178907623016189L;
    
    public InvalidInputException(String errorMessage) {
        super(errorMessage);
    }

}
