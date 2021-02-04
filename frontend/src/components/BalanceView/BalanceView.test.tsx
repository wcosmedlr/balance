import { render } from "@testing-library/react";
import * as React from "react";
import AppContextProvider from "../../AppContext";
import { Balance, buildBalance } from "../../models/balance";
import { buildMember } from "../../models/member";
import { BalanceRepository, buildJestBalanceMockRepository} from "../../respositories/BalanceRepository";
import BalanceView, { BalanceText } from "./BalanceView";

describe("BalanceView", () => {

  it('shows a message when expenses list is empty', async () => {
    const view = render(<AppContextProvider>
      <BalanceView/>
    </AppContextProvider>);

    expect(await view.findByText(BalanceText.emptyMessage)).toBeInTheDocument();
  });

  it('shows a list of balances', async () => {
    const balances: Balance[] = [
      buildBalance({owner: buildMember({id:0, name: 'Member 1'}), value: 50}),
      buildBalance({owner: buildMember({id:1, name: 'Member 2'}), value: 80})
    ];
    const balanceRepository: BalanceRepository = buildJestBalanceMockRepository(
      Promise.resolve(balances)
    );
    
    const view = render(<AppContextProvider balanceRepository={balanceRepository}
    initialBalances={balances}>
      <BalanceView/>
    </AppContextProvider>);

    //for loop to sequential processing: it avoids view parallel finds errors
    for (const balance of balances){
      expect(await view.findByText(balance.owner.name)).toBeInTheDocument()
      expect(await view.findByText(balance.value + "€")).toBeInTheDocument()
    }
  });

});
