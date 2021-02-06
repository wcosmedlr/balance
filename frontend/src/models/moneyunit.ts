import { Member, buildMember } from "./member";

export interface MoneyUnit {
  owner: Member;
  value: number;
}

export const buildMoneyUnit = ({
  owner = buildMember({}),
  value = 0
}: Partial<MoneyUnit>): MoneyUnit => ({
  owner,
  value,
});