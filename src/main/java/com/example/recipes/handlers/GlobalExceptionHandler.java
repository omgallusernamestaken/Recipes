package com.example.recipes.handlers;

import com.example.recipes.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @ExceptionHandler(ItemNotFoundException.class)
    public String handleRecipeNotFound(ItemNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "error/item_not_found";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("message", "Coś poszło nie tak. Spróbuj ponownie później.");
        if ("dev".equals(activeProfile)) {
            model.addAttribute("errorDetails", ex.getMessage());
        }
        return "error/general_error";
    }
}
