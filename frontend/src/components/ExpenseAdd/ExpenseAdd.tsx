import React, { useEffect } from "react";
import './ExpenseAdd.css';
import { AppContext } from "../../AppContext";
import { buildExpense, Expense } from "../../models/expense";

export enum ExpenseText {
  emptyMessage = 'Añade un miembro para poder agregar un gasto.',
  expenseName = 'Gasto'
}

interface ExpenseAddProps {
  onClick?: (handle: Expense) => void;
}

export const ExpenseAdd: React.FC<ExpenseAddProps> = ({ onClick }) => {

  const { addExpense, members, expenses } = React.useContext(AppContext)
  const [description, setDescription] = React.useState<string>(ExpenseText.expenseName + ' ' + (expenses.length + 1));
  const [value, setValue] = React.useState<number>(45);
  const [memberSelected, setMemberSelected] = React.useState<number>();

  React.useEffect(() => {
    //set memberSelected default value
    if (!memberSelected && members.length > 0) {
      setMemberSelected(members[0].id)
    }
  }, [members, memberSelected]);

  useEffect(() => {
    setDescription(ExpenseText.expenseName + ' ' + (expenses.length + 1))
  }, [expenses])

  const hasMembers = () => members && members.length > 0;

  return (
    <section>
      <div>
        <h1 className="accent-color text-primary-color">Agregar uno nuevo</h1>
        {hasMembers() ? <div>
          <article className="card">
            <form id="expense-add-form">
              <p>Descripción
        <input
                  type="text"
                  value={description}
                  onChange={e => setDescription(e.target.value)}
                />
              </p>

              <p>Importe
        <input
                  type="number"
                  value={value}
                  onChange={e => setValue(Number(e.target.value))}
                />
              </p>

              <p>Miembro
        <select value={memberSelected}
                  onChange={e => setMemberSelected(Number(e.target.value))}
                >
                  {
                    members.map(member =>
                      <option
                        key={member.id}
                        value={member.id}>
                        {member.name}
                      </option>
                    )
                  }
                </select>
              </p>

            </form>
            {
              <button form="expense-add-form" type="button" onClick={() => {
                const expense = buildExpense({
                  description: description,
                  value: value,
                  owner: memberSelected ? members.filter(member => member.id === memberSelected).shift() : undefined
                })
                addExpense(expense);
                onClick && onClick(expense);
              }}>Agregar gasto</button>
            }
          </article>
        </div>
          : <article className="card"><h4>{ExpenseText.emptyMessage}</h4></article>
        }
      </div>

    </section>
  );
};

export default ExpenseAdd;