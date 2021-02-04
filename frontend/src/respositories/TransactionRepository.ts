import { Transaction } from '../models/transaction';
import { SERVER } from '../Constants'

const REPOSITORY : string = 'transactions'

export interface TransactionRepository {
    getTransactions: () => Promise<Transaction[]>;
}

export const transactionRepositoryInstance: TransactionRepository = {
    getTransactions: () => fetch(SERVER + REPOSITORY)
        .then(response => response.json())
};

export const transactionMockRepository: TransactionRepository = {
    getTransactions: () => Promise.resolve([])
};

export const buildJestTransactionMockRepository = (get: Promise<Transaction[]>) => ({
    getTransactions: jest.fn(() => get)
} as TransactionRepository);

export const defaultJestTransactionMockRepository = () => buildJestTransactionMockRepository(
    transactionMockRepository.getTransactions()
)