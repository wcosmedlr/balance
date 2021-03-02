import React from "react";
import './BalanceView.css';
import { AppContext } from "../../AppContext";
import { fixDecimals } from "../../Utils";
import { compareMoneyUnitByValueDesc } from "../../models/moneyunit";

export enum BalanceText {
  emptyMessage = 'Añade un miembro para ver su balance.'
}

interface BalanceViewProps {
};

const BalanceView: React.FC<BalanceViewProps> = () => {

  const { balance } = React.useContext(AppContext);
  const [error] = React.useState<Error | null>(null);

  const hasBalances = () => balance.balanceMembers && balance.balanceMembers.length > 0;

  return (
    <section>
      <div>
        <h1 className="accent-color text-primary-color">Balances</h1>
        {error && <p>{error.message}</p>}
        {hasBalances()
          ? balance.balanceMembers.sort(compareMoneyUnitByValueDesc)
            .map((balanceMember, index) =>
              <article className="card" key={index}>
                <h2>{balanceMember.owner.name} &nbsp;
                    <span className={balanceMember.value >= 0 ? "positive" : "negative"}>
                            {fixDecimals(balanceMember.value)}€
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
