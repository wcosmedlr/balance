package com.github.wcosmedlr.specs.repositories


import com.github.wcosmedlr.models.Member
import com.github.wcosmedlr.mothers.MemberMother
import com.github.wcosmedlr.services.MemberService
import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.reactivex.Maybe
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest
class MemberServiceSpec extends Specification{

    @Inject
    @Shared
    @AutoCleanup
    private EmbeddedApplication<?> application

    private MemberService memberService = application.applicationContext.getBean(MemberService)

    @Inject
    @Shared
    private MemberMother memberMother;

    /*
       Given an valid member
       When the user sends a request to add it
       Then the system must add the member
   */
    void 'add a member'() {
        given:
        Member member = memberMother.createValidUser1()

        when:
        final Maybe<Long> result = memberService.add(member);
        Long id = result.blockingGet();
        id != null

        and:
        member.setId(id)
        Member memberObtained = memberService.findById(id).blockingGet();

        then:
        member == memberObtained;
    }

}
