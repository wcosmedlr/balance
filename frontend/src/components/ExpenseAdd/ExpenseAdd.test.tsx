import * as React from "react";
import { render, fireEvent, screen } from '@testing-library/react';
import ExpenseAdd, { ExpenseText } from "./ExpenseAdd";
import { buildJestExpenseMockRepository, ExpenseRepository } from "../../respositories/ExpenseRepository";
import AppContextProvider from "../../AppContext";
import { buildJestMemberMockRepository, MemberRepository } from "../../respositories/MemberRepository";
import { buildMember, Member } from "../../models/member";

describe("ExpenseAdd", () => {

  it('shows a message when members list is empty', async () => {
    render(<AppContextProvider>
      <ExpenseAdd />
    </AppContextProvider>);

    expect(await screen.findByText(ExpenseText.emptyMessage)).toBeInTheDocument();
  });

  it('adds an expense', async () => {
    const members: Member[] = [
      buildMember({ id: 0, name: "Member 1" })
    ];

    const expenseRepository: ExpenseRepository = buildJestExpenseMockRepository(
      Promise.resolve([]), Promise.resolve(0)
    );
    const memberRepository: MemberRepository = buildJestMemberMockRepository(
      Promise.resolve(members), Promise.resolve(0)
    );

    render(<AppContextProvider
      expenseRepository={expenseRepository}
      memberRepository={memberRepository}
      initialMembers={members}>
      <ExpenseAdd />
    </AppContextProvider>);

    const button = screen.getByRole('button');
    fireEvent.click(button);

    expect(expenseRepository.addExpense).toHaveBeenCalledTimes(1)
  });

});

