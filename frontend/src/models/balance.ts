import { Member, buildMember } from "./member";
import { MoneyUnit, buildMoneyUnit } from "./moneyunit";

export interface Balance extends MoneyUnit {}

export const buildBalance = buildMoneyUnit