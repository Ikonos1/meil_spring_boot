package com.grapplesoft.meil_backend.models;

import java.io.Serializable;

/**
 * Custom Result class to return from services
 * Implmentation of Kotlin Result class
 *
 * @param value Value on success of service (Generics type T)
 * @param error Error on failure of service
 * @param <T>
 * @Author Vishwesh Shukla
 * @SeeAlso <a href="https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/">Kotlin Result class</a>
 */
public record Result<T>(
        T value,
        Throwable error
) implements Serializable {

    public static <T> Result<T> success(T value) {
        return new Result<>(value, null);
    }

    public static <T> Result<T> failure(Throwable error) {
        return new Result<>(null, error);
    }

    public boolean isSuccess() {
        return value != null;
    }

    public boolean isFailure() {
        return error != null;
    }

    public T getValue() {
        if (isSuccess()) {
            return value;
        } else {
            throw new IllegalStateException("Result is not success");
        }
    }

    public Throwable getError() {
        if (isFailure()) {
            return error;
        } else {
            throw new IllegalStateException("Result does not have an error");
        }
    }
}
