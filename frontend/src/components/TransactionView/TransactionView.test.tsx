import { render } from "@testing-library/react";
import * as React from "react";
import AppContextProvider from "../../AppContext";
import { buildBalance } from "../../models/balance";
import { buildMember } from "../../models/member";
import { buildTransaction, Transaction } from "../../models/transaction";
import { BalanceRepository, buildJestBalanceMockRepository } from "../../respositories/BalanceRepository";
import TransactionView, { TransactionText } from "./TransactionView";

describe("TransactionView", () => {

  it('shows a message when members list is empty', async () => {
    const view = render(<AppContextProvider>
      <TransactionView />
    </AppContextProvider>);

    expect(await view.findByText(TransactionText.emptyMessage)).toBeInTheDocument();
  });

  it('shows a list of members', async () => {
    const transactions: Transaction[] = [
      buildTransaction({
        owner: buildMember({ id: 1, name: 'Member 1' }),
        value: 50,
        benefactor: buildMember({id: 2, name: 'Member 2'})
      }),
      buildTransaction({
        owner: buildMember({ id: 3, name: 'Member 3' }),
        value: 80,
        benefactor: buildMember({id: 4, name: 'Member 4'})
      })
    ];
    const account = buildBalance({transactions})
    const balanceRepository: BalanceRepository = buildJestBalanceMockRepository(
      Promise.resolve(account)
    );

    const view = render(<AppContextProvider balanceRepository={balanceRepository}
      initialBalance={account}>
      <TransactionView />
    </AppContextProvider>);

    //for loop to sequential processing: it avoids view parallel finds errors
    for (const transaction of transactions) {
      expect(await view.findByText(transaction.owner.name)).toBeInTheDocument()
      expect(await view.findByText(transaction.benefactor.name)).toBeInTheDocument()
      expect(await view.findByText(transaction.value)).toBeInTheDocument()
    }
  });

});
