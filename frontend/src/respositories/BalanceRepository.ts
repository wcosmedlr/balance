import { Balance, buildBalance } from '../models/balance';
import { SERVER } from '../Constants'

const REPOSITORY : string = 'balances'

export interface BalanceRepository {
    getBalance: () => Promise<Balance>;
}

export const balanceRepositoryInstance: BalanceRepository = {
    getBalance: () => fetch(SERVER + REPOSITORY)
        .then(response => response.json())
};

export const balanceMockRepository: BalanceRepository = {
    getBalance: () => Promise.resolve(buildBalance({}))
};

export const buildJestBalanceMockRepository = (get: Promise<Balance>) => ({
    getBalance: jest.fn(() => get)
} as BalanceRepository);

export const defaultJestAccountMockRepository = () => buildJestBalanceMockRepository(
    balanceMockRepository.getBalance()
)