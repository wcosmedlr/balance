import { Balance} from "./balance";
import { buildMember } from "./member";

export interface Expense extends Balance {
  id?: number;
  description: string;
  timeStamp: Date;
}

export const buildExpense = ({
  id = undefined,
  description = '10',
  timeStamp = new Date(),
  owner = buildMember({}),
  value = 0
}: Partial<Expense>): Expense => ({
  id,
  description,
  timeStamp,
  owner,
  value,
});