export interface Member {
  id?: number;
  name: string;
  surname: string;
}

export const buildMember = ({
  id = undefined,
  name = 'default name',
  surname = 'default surname'
}: Partial<Member>): Member => ({
  id,
  name,
  surname
});
