package com.lu.excel.annotation;

import com.lu.excel.handler.CustomHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@CellMarker(handler = CustomHandler.class, width = 32, title = "这是自定义注解")
public @interface CustomAnnotation {

}
