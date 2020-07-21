package com.ilyabuglakov.shapes.validator;

public interface Validator<T> {
    boolean validate(T obj);
}
