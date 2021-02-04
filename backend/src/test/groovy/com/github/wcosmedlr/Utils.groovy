package com.github.wcosmedlr

import io.micronaut.context.annotation.Factory

import javax.inject.Singleton
import java.time.format.DateTimeFormatter

@Factory
class Utils {

    @Singleton
    DateTimeFormatter textBook() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    }
}
