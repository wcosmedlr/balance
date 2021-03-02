import React from 'react';
import ExpenseView from './components/ExpenseView'
import './App.css';
import AppContextProvider from './AppContext';
import ExpenseAdd from './components/ExpenseAdd';
import MemberAdd from './components/MemberAdd';
import BalanceView from './components/BalanceView';
import TransactionView from './components/TransactionView';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import MemberView from './components/MemberView';
import Navbar from './components/Navbar';
import { expenseRepositoryInstance } from './respositories/ExpenseRepository';
import { memberRepositoryInstance } from './respositories/MemberRepository';
import { balanceRepositoryInstance } from './respositories/BalanceRepository';

function App() {
  return (
    <AppContextProvider
      expenseRepository={expenseRepositoryInstance}
      memberRepository={memberRepositoryInstance}
      balanceRepository={balanceRepositoryInstance}
    >
      <Router>
        <div className="container">
          <header>
            <Navbar>
            </Navbar>
          </header>
          <main>
            <Switch>
              <Route path="/expenses">
                <ExpenseAdd></ExpenseAdd>
                <ExpenseView></ExpenseView>
              </Route>
              <Route path="/members">

                <MemberAdd></MemberAdd>
                <MemberView></MemberView>
              </Route>
              <Route path="/">
                <BalanceView></BalanceView>
                <TransactionView></TransactionView>
              </Route>
            </Switch>
          </main>
          <footer>
            <h5>
            <div>
              <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">
                <img alt="Licencia Creative Commons" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" />
              </a>
              </div>
              <div>
              Esta obra está bajo una &nbsp;
              <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">
                Licencia Creative Commons Atribución-NoComercial-CompartirIgual 4.0 Internacional
                </a>.
                </div>
                <div>
                Desarrollado por <a href="https://www.linkedin.com/in/webster-cosme-de-la-rosa/"
                  target="_blank"
                  rel="noreferrer">wcosmedlr</a>
              </div>
            </h5>
          </footer>
        </div>
      </Router>
    </AppContextProvider>
  );
}

export default App;
