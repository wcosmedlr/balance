import React from "react";
import './BalanceView.css';
import { AppContext } from "../../AppContext";
import { compareBalancesByValueDesc, fixDecimals } from "../../Utils";

export enum BalanceText {
  emptyMessage = 'Añade un miembro para ver su balance.'
}

interface BalanceViewProps {
};

const BalanceView: React.FC<BalanceViewProps> = () => {

  const { balances } = React.useContext(AppContext);
  const [error] = React.useState<Error | null>(null);

  const hasBalances = () => balances && balances.length > 0;

  return (
    <section>
      <div>
        <h1 className="accent-color text-primary-color">Balances</h1>
        {error && <p>{error.message}</p>}
        {hasBalances()
          ? balances.sort(compareBalancesByValueDesc)
            .map((balance, index) =>
              <article className="card" key={index}>
                <h2>{balance.owner.name} &nbsp;
                    <span className={balance.value >= 0 ? "positive" : "negative"}>
                            {fixDecimals(balance.value)}€
                    </span>
                </h2>
              </article>)
          : <article className="card"><h4>{BalanceText.emptyMessage}</h4></article>
        }
      </div>
    </section>
  );
};

export default BalanceView;
