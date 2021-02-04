import React, { useEffect } from "react";
import './ExpenseView.css';
import { AppContext } from "../../AppContext";
import { fixDecimals, timeSince } from "../../Utils";

export enum ExpenseText {
  emptyMessage = 'Añade un gasto para visualizarlo aquí.'
}

interface ExpenseViewProps {
};

const ExpenseView: React.FC<ExpenseViewProps> = () => {

  const { expenses } = React.useContext(AppContext);
  const [error] = React.useState<Error | null>(null);

  const [, setTime] = React.useState<number>();

  //force refresh component every second: update timeSince function data
  useEffect(() => {
    const interval = setInterval(() => setTime(Date.now()), 1000);
    return () => {
      clearInterval(interval);
    };
  }, [])

  const hasExpenses = () => expenses && expenses.length > 0;

  return (
    <section>
      <div>
        <h1 className="accent-color text-primary-color">Listado</h1>
        {error && <p>{error.message}</p>}
        {hasExpenses()
          ? expenses.map((expense) =>
            <article className="card" key={expense.id}>
              <div className="expense-container">
                <div>
                  <h2>{expense.owner.name}</h2>
                </div>
                <div>
                  <p>{fixDecimals(expense.value)}€</p>
                </div>
                <div>
                  <h3>{expense.description}</h3>
                </div>
                <div>
                  <p>hace {timeSince(new Date(expense.timeStamp))}</p>
                </div>
              </div>
            </article>)
          : <article className="card"><h4>{ExpenseText.emptyMessage}</h4></article>
        }
      </div>
    </section>
  );
};

export default ExpenseView;
