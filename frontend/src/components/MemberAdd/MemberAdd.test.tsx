import * as React from "react";
import { render, fireEvent, screen } from '@testing-library/react';
import AppContextProvider from "../../AppContext";
import { buildJestMemberMockRepository, MemberRepository } from "../../respositories/MemberRepository";
import MemberAdd from "./MemberAdd";

describe("MemberAdd", () => {

  it('adds an member', async () => {
    const memberRepository: MemberRepository = buildJestMemberMockRepository(
      Promise.resolve([]), Promise.resolve(0)
    );

    render(<AppContextProvider
      memberRepository={memberRepository}>
      <MemberAdd/>
    </AppContextProvider>);

    const button = screen.getByRole('button');
    fireEvent.click(button);

    expect(memberRepository.addMember).toHaveBeenCalledTimes(1)
  });

});
