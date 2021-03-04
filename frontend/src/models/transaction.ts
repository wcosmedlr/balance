import { Member, buildMember } from "./member";
import { MoneyUnit } from "./moneyunit";

export interface Transaction extends MoneyUnit {
  benefactor: Member;
  value: number;
}

export const buildTransaction = ({
  owner = buildMember({}),
  value = 0,
  benefactor = buildMember({})
}: Partial<Transaction>): Transaction => ({
  owner,
  value,
  benefactor
});