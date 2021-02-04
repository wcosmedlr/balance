import { Balance } from "./balance";
import { Member, buildMember } from "./member";

export interface Transaction extends Balance {
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