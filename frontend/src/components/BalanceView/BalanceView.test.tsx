import { render } from "@testing-library/react";
import * as React from "react";
import AppContextProvider from "../../AppContext";
import { buildBalance } from "../../models/balance";
import { BalanceMember, buildBalanceMember } from "../../models/balancemember";
import { buildMember } from "../../models/member";
import { AccountRepository, buildJestAccountMockRepository } from "../../respositories/BalanceRepository";
import BalanceView, { BalanceText } from "./BalanceView";

describe("BalanceView", () => {

  it('shows a message when expenses list is empty', async () => {
    const view = render(<AppContextProvider>
      <BalanceView/>
    </AppContextProvider>);

    expect(await view.findByText(BalanceText.emptyMessage)).toBeInTheDocument();
  });

  it('shows a list of balances', async () => {
    const balanceMembers: BalanceMember[] = [
      buildBalanceMember({owner: buildMember({id:0, name: 'Member 1'}), value: 50}),
      buildBalanceMember({owner: buildMember({id:1, name: 'Member 2'}), value: 80})
    ];
    const balance = buildBalance({balanceMembers})
    const accountRepository: AccountRepository = buildJestAccountMockRepository(
      Promise.resolve(balance)
    );
    
    const view = render(<AppContextProvider accountRepository={accountRepository}
    initialBalance={balance}>
      <BalanceView/>
    </AppContextProvider>);

    //for loop to sequential processing: it avoids view parallel finds errors
    for (const BalanceMember of balanceMembers){
      expect(await view.findByText(BalanceMember.owner.name)).toBeInTheDocument()
      expect(await view.findByText(BalanceMember.value + "€")).toBeInTheDocument()
    }
  });

});
