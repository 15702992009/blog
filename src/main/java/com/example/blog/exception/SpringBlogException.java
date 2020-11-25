package com.example.blog.exception;

public class SpringBlogException extends RuntimeException {
    public SpringBlogException(String exception_occured_while_loading_keystore) {
        super(exception_occured_while_loading_keystore);
    }
}
