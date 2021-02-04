package com.github.wcosmedlr;

import io.micronaut.context.annotation.Factory;
import org.modelmapper.ModelMapper;

import javax.inject.Singleton;

@Factory
public class AppConfiguration {
    @Singleton
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
