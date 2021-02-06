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

export const compareMoneyUnitByValueDesc = (balance1: MoneyUnit, balance2: MoneyUnit) => balance2.value - balance1.value
