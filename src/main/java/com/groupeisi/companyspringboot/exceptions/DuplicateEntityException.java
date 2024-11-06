package com.groupeisi.companyspringboot.exceptions;

public class DuplicateEntityException extends RuntimeException{
    public DuplicateEntityException(String message) {
        super(message);
    }
}
