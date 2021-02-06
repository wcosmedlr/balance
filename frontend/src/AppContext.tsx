import { createContext, useEffect, useRef, useState } from "react";
import { Balance } from "./models/balance";
import { Expense } from './models/expense';
import { Member } from "./models/member";
import { Transaction } from "./models/transaction";
import { expenseMockRepository, ExpenseRepository } from "./respositories/ExpenseRepository";
import { MemberRepository, memberMockRepository } from "./respositories/MemberRepository";
import { balanceMockRepository, BalanceRepository} from "./respositories/BalanceRepository";
import { TransactionRepository, transactionMockRepository } from "./respositories/TransactionRepository";
import { act } from "react-dom/test-utils";


type AppContextType = {
    addExpense: (expense: Expense) => void;
    expenses: Expense[];
    addMember: (member: Member) => void;
    members: Member[];
    balances: Balance[];
    transactions: Transaction[];
};

export const AppContext = createContext<AppContextType>({
    addExpense: (expense: Expense) => {},
    expenses: [],
    addMember: (member: Member) => {},
    members: [],
    balances: [],
    transactions: [] 
});

interface AppContextProps {
  expenseRepository?: ExpenseRepository;
  memberRepository?: MemberRepository;
  balanceRepository?: BalanceRepository;
  transactionRepository?: TransactionRepository;
  initialExpenses?: Expense[]
  initialMembers?: Member[]
  initialBalances?: Balance[]
  initialTransactions?: Transaction[]
}

const AppContextProvider: React.FC<AppContextProps> = ({children,
  expenseRepository = expenseMockRepository,
  memberRepository = memberMockRepository,
  balanceRepository = balanceMockRepository,
  transactionRepository = transactionMockRepository,
  initialExpenses = [],
  initialMembers = [],
  initialBalances = [],
  initialTransactions = []
}) => {
    const [expenses, setExpenses] = useState<Expense[]>(initialExpenses);
    const [members, setMembers] = useState<Member[]>(initialMembers);
    const [balances, setBalances] = useState<Balance[]>(initialBalances);
    const [transactions, setTransactions] = useState<Transaction[]>(initialTransactions);
  
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
      const updateBalance = async ()=>{
        const newBalances = await balanceRepository.getBalances();
        act(() => {
          setBalances(newBalances)
        })
      }
  
      const updateTransactions = async () =>{
        const newTransactions = await transactionRepository.getTransactions();
        act(() => {
          setTransactions(newTransactions)
        })
      }

      updateBalance()
      updateTransactions()
    }, [expenses, members, balanceRepository, transactionRepository])

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
      <AppContext.Provider value={{ expenses, addExpense, members, addMember, balances, transactions }}>
        {children}
      </AppContext.Provider>
    );
  };
  
  export default AppContextProvider;