import { buildMember, Member } from '../models/member';
import { SERVER } from '../Constants'

const REPOSITORY : string = 'members'

export interface MemberRepository {
    getMembers: () => Promise<Member[]>;
    addMember: (member: Member) => Promise<number>;
}

export const memberRepositoryInstance: MemberRepository = {
    getMembers: () => fetch(SERVER + REPOSITORY)
    .then(response => response.json()),
    addMember: (member) => fetch(SERVER + REPOSITORY, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json'},
            body: JSON.stringify(member)
        })
        .then(response => response.json())
};

export const memberMockRepository: MemberRepository = {
    getMembers: () => Promise.resolve([]),
    addMember: (member) => Promise.resolve(1)
};

export const buildJestMemberMockRepository = (get: Promise<Member[]>, add: Promise<number>) => ({
    getMembers: jest.fn(() => get),
    addMember: jest.fn(() => add)
} as MemberRepository);

export const defaultJestMemberMockRepository = () => buildJestMemberMockRepository(
    memberMockRepository.getMembers(),
    memberMockRepository.addMember(buildMember({}))
)