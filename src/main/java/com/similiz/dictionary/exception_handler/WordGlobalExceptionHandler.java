package com.similiz.dictionary.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WordGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<WordIncorrectData> handleException(NoSuchWordException noSuchWordException) {
        WordIncorrectData wordIncorrectData = new WordIncorrectData();
        wordIncorrectData.setInfo(noSuchWordException.getMessage());
        return new ResponseEntity<>(wordIncorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<WordIncorrectData> handleAllException(Exception exception) {
        WordIncorrectData wordIncorrectData = new WordIncorrectData();
        wordIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(wordIncorrectData, HttpStatus.BAD_REQUEST);
    }
}
