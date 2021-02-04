import { Member, buildMember } from "./member";

export interface Balance {
  owner: Member;
  value: number;
}

export const buildBalance = ({
  owner = buildMember({}),
  value = 0
}: Partial<Balance>): Balance => ({
  owner,
  value,
});