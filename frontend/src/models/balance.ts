import { BalanceMember } from "./balancemember";
import { Transaction } from "./transaction";

export interface Balance {
  balanceMembers: BalanceMember [],
    transactions: Transaction []
}

export const buildBalance = ({
    balanceMembers = [],
    transactions = []
  }: Partial<Balance>): Balance => ({
    balanceMembers,
    transactions
  });