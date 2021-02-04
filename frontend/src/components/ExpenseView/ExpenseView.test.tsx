import * as React from "react";
import { render } from '@testing-library/react';
import { Expense, buildExpense } from "../../models/expense";
import { buildJestExpenseMockRepository, ExpenseRepository } from "../../respositories/ExpenseRepository";
import ExpenseView, { ExpenseText } from "./ExpenseView";
import AppContextProvider, { AppContext } from "../../AppContext";

describe("Expense", () => {
  
  it('shows a message when expenses list is empty', async () => {
    const view = render(<AppContextProvider>
      <ExpenseView/>
    </AppContextProvider>);

    expect(await view.findByText(ExpenseText.emptyMessage)).toBeInTheDocument();
  });

   it('shows a list of expenses', async () => {
    const expenses: Expense[] = [
      buildExpense({id:0, description: "Expense 1"}),
      buildExpense({id:1,description: "Expense 2"})
    ];

    const expenseRepository: ExpenseRepository = buildJestExpenseMockRepository(
      Promise.resolve(expenses), Promise.resolve(0)
    );
    
    const view = render(<AppContextProvider expenseRepository={expenseRepository}>
      <ExpenseView/>
    </AppContextProvider>);

    //for loop to sequential processing: it avoids view parallel finds errors
    for (const expense of expenses)
      expect(await view.findByText(expense.description)).toBeInTheDocument()
  });

});
