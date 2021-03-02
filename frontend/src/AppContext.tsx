import { createContext, useEffect, useRef, useState } from "react";
import { Expense } from './models/expense';
import { Member } from "./models/member";
import { expenseMockRepository, ExpenseRepository } from "./respositories/ExpenseRepository";
import { MemberRepository, memberMockRepository } from "./respositories/MemberRepository";
import { AccountRepository, accountMockRepository } from "./respositories/BalanceRepository";
import { act } from "react-dom/test-utils";
import { Balance, buildBalance } from "./models/balance";


type AppContextType = {
    addExpense: (expense: Expense) => void;
    expenses: Expense[];
    addMember: (member: Member) => void;
    members: Member[];
    balance: Balance;
};

export const AppContext = createContext<AppContextType>({
    addExpense: (expense: Expense) => {},
    expenses: [],
    addMember: (member: Member) => {},
    members: [],
    balance: buildBalance({})
});

interface AppContextProps {
  expenseRepository?: ExpenseRepository;
  memberRepository?: MemberRepository;
  accountRepository?: AccountRepository;
  initialExpenses?: Expense[]
  initialMembers?: Member[]
  initialBalance?: Balance
}

const AppContextProvider: React.FC<AppContextProps> = ({children,
  expenseRepository = expenseMockRepository,
  memberRepository = memberMockRepository,
  accountRepository = accountMockRepository,
  initialExpenses = [],
  initialMembers = [],
  initialBalance = buildBalance({})
}) => {
    const [expenses, setExpenses] = useState<Expense[]>(initialExpenses);
    const [members, setMembers] = useState<Member[]>(initialMembers);
    const [balance, setBalance] = useState<Balance>(initialBalance);
  
    useEffect(() => {
      const initializeExpenses = async ()=>{
        const newExpenses = await expenseRepository.getExpensesOrderByTimeStamp();
        act(() => {
          setExpenses(newExpenses)
        })
      }

      const initializeMembers = async ()=>{
        const newMembers = await memberRepository.getMembers();
        act(() => {
          setMembers(newMembers)
        })
      }

      initializeExpenses()
      initializeMembers()
    },[expenseRepository, memberRepository])

    const executions = useRef(0);
    useEffect(()=> {
      if (executions.current < 2) {
        executions.current++;
        return;
      }
  
      const updateAccount = async () =>{
        const newBalance = await accountRepository.getBalance();
        act(() => {
          setBalance(newBalance)
        })
      }

      updateAccount()
    }, [expenses, members, accountRepository])

    const addExpense = (expense: Expense) => {
      expenseRepository.addExpense(expense).then(
          (id) => {
            if(id>0){
              expense.id = id
              var newExpenses = expenses.slice();
              newExpenses.unshift(expense)
              setExpenses(newExpenses)
            }
          }
        )
    }

    const addMember = (member: Member) => {
      memberRepository.addMember(member).then(
        (id) => {
          if(id>0){
            member.id = id
            setMembers(members.concat(member))
          }
        }
      )
  }
    
    return (
      <AppContext.Provider value={{ expenses, addExpense, members, addMember, balance }}>
        {children}
      </AppContext.Provider>
    );
  };
  
  export default AppContextProvider;