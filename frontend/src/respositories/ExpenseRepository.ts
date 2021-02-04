import { buildExpense, Expense } from '../models/expense';
import { SERVER } from '../Constants'

const REPOSITORY : string = 'expenses'

export interface ExpenseRepository {
    getExpensesOrderByTimeStamp: () => Promise<Expense[]>;
    addExpense: (expense: Expense) => Promise<number>;
}

export const expenseRepositoryInstance: ExpenseRepository = {
    getExpensesOrderByTimeStamp: () => fetch(SERVER + REPOSITORY, )
        .then(response => response.json()),
    addExpense: (expense) => fetch(SERVER + REPOSITORY, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(expense)
        })
        .then(response => response.json())
};

export const expenseMockRepository: ExpenseRepository = {
    getExpensesOrderByTimeStamp: () => Promise.resolve([]),
    addExpense: (expense) => Promise.resolve(1)
};

export const buildJestExpenseMockRepository = (get: Promise<Expense[]>, add: Promise<number>) => ({
    getExpensesOrderByTimeStamp: jest.fn(() => get),
    addExpense: jest.fn(() => add)
} as ExpenseRepository);

export const defaultJestExpenseJestMockRepository = () => buildJestExpenseMockRepository(
    expenseMockRepository.getExpensesOrderByTimeStamp(),
    expenseMockRepository.addExpense(buildExpense({}))
)