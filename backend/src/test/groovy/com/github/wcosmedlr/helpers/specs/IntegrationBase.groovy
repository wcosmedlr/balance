package com.github.wcosmedlr.helpers.specs

import com.github.wcosmedlr.helpers.docker.MySQLContainer
import io.micronaut.context.ApplicationContext
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.micronaut.test.support.TestPropertyProvider
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest(environments = "db")
abstract class IntegrationBase extends Specification implements TestPropertyProvider {

    @Inject
    @AutoCleanup
    @Shared
    private ApplicationContext applicationContext

    protected <T> T getBean(Class<T> beanType) {
        applicationContext.getBean(beanType)
    }

    @Override
    Map<String, String> getProperties() {
        ["datasources.default.port": MySQLContainer.MY_SQL_CONTAINER.firstMappedPort]
                as Map<String, String>
    }
}
