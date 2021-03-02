import { Member, buildMember } from "./member";
import { MoneyUnit, buildMoneyUnit } from "./moneyunit";

export interface BalanceMember extends MoneyUnit {}

export const buildBalanceMember = buildMoneyUnit