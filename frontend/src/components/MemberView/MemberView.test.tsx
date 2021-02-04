import { render } from "@testing-library/react";
import * as React from "react";
import AppContextProvider from "../../AppContext";
import { buildMember, Member } from "../../models/member";
import { buildJestMemberMockRepository, MemberRepository } from "../../respositories/MemberRepository";
import MemberView, { MemberText } from "./MemberView";

describe("MemberView", () => {

  it('shows a message when members list is empty', async () => {
    const memberRepository: MemberRepository = buildJestMemberMockRepository(
      Promise.resolve([]), Promise.resolve(0)
    );
    const view = render(<AppContextProvider memberRepository={memberRepository}>
      <MemberView/>
    </AppContextProvider>);

    expect(await view.findByText(MemberText.emptyMessage)).toBeInTheDocument();
  });

  it('shows a list of members', async () => {
    const members: Member[] = [
      buildMember({id:0, name: 'Member 1'}),
      buildMember({id:1, name: 'Member 2'})
    ];
    const memberRepository: MemberRepository = buildJestMemberMockRepository(
      Promise.resolve(members), Promise.resolve(0)
    );
    
    const view = render(<AppContextProvider memberRepository={memberRepository}
      initialMembers={members}>
      <MemberView/>
    </AppContextProvider>);

    //for loop to sequential processing: it avoids view parallel finds errors
    for (const member of members){
      expect(await view.findByText(member.name)).toBeInTheDocument()
    }
  });

});
