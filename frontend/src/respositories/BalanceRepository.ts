import { Balance } from '../models/balance';
import { SERVER } from '../Constants'

const REPOSITORY : string = 'balances'

export interface BalanceRepository {
    getBalances: () => Promise<Balance[]>;
}

export const balanceRepositoryInstance: BalanceRepository = {
    getBalances: () => fetch(SERVER + REPOSITORY)
        .then(response => response.json())
};

export const balanceMockRepository: BalanceRepository = {
    getBalances: () => Promise.resolve([])
};

export const buildJestBalanceMockRepository = (get: Promise<Balance[]>) => ({
    getBalances: jest.fn(() => get),
} as BalanceRepository);

export const defaultJestBalanceMockRepository = () => buildJestBalanceMockRepository(
    balanceMockRepository.getBalances()
)