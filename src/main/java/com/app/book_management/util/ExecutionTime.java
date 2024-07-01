package com.app.book_management.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация используется для маркировки методов, время выполнения которых нужно измерить.
 * @Target(ElementType.METHOD): указывает, что эта аннотация может применяться только к методам.
 * @Retention(RetentionPolicy.RUNTIME): указывает, что аннотация будет доступна во время выполнения
 * необходимое для работы с AOP.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExecutionTime {
}
