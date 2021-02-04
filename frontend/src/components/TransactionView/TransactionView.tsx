import React from "react";
import './TransactionView.css';
import { AppContext } from "../../AppContext";
import { compareBalancesByValueDesc, fixDecimals } from "../../Utils";

export enum TransactionText {
  emptyMessage = 'Establece más de dos miembros con un balance distinto de cero para visualizar las transacciones sugeridas aquí.'
}

interface TransactionViewProps {
};

const TransactionView: React.FC<TransactionViewProps> = () => {

  const { transactions } = React.useContext(AppContext);
  const [error] = React.useState<Error | null>(null);

  const hasTransactions = () => transactions && transactions.length > 0;

  return (
    <section>
      <div>
        <h1 className="accent-color text-primary-color">Transacciones</h1>
        {error && <p>{error.message}</p>}
        {hasTransactions()
          ? transactions.sort(compareBalancesByValueDesc).map((transaction, index) =>
            <article className="card" key={index}>
              <h2>
              <span>{transaction.owner.name}</span> &nbsp;
                <span className="link">&gt;</span> &nbsp;
                <span className={transaction.value >= 0 ? "positive" : "negative"}>
                <span>{fixDecimals(transaction.value)}</span>€</span> &nbsp;
                <span className="link">&gt;</span> &nbsp;
                <span>{transaction.benefactor.name}</span>
              </h2>
            </article>)
          : <article className="card"><h4>{TransactionText.emptyMessage}</h4></article>
        }
      </div>
    </section>
  );
};

export default TransactionView;

