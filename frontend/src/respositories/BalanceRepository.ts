import { Balance, buildBalance } from '../models/balance';
import { SERVER } from '../Constants'

const REPOSITORY : string = 'balances'

export interface AccountRepository {
    getBalance: () => Promise<Balance>;
}

export const accountRepositoryInstance: AccountRepository = {
    getBalance: () => fetch(SERVER + REPOSITORY)
        .then(response => response.json())
};

export const accountMockRepository: AccountRepository = {
    getBalance: () => Promise.resolve(buildBalance({}))
};

export const buildJestAccountMockRepository = (get: Promise<Balance>) => ({
    getBalance: jest.fn(() => get)
} as AccountRepository);

export const defaultJestAccountMockRepository = () => buildJestAccountMockRepository(
    accountMockRepository.getBalance()
)