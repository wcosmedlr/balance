import React from "react";
import './MemberView.css';
import { AppContext } from "../../AppContext";

export enum MemberText {
  emptyMessage = 'Agrega un miembro para poder visualizarlo aquí.'
}

interface MemberViewProps {
};

const MemberView: React.FC<MemberViewProps> = () => {
  
  const {members} = React.useContext(AppContext);
  const [error] = React.useState<Error|null>(null);

  const hasMembers = () => members && members.length > 0;

  return (
    <section>
      <div>
      <h1 className="accent-color text-primary-color">Listado</h1>
      { error && <p>{error.message}</p> }
      { hasMembers()
        ? members.map((member, index) =>
          <article className="card" key={member.id}>
            <h2>{member.name}</h2>
          </article>)
        : <article className="card"><h4>{MemberText.emptyMessage}</h4></article>
      }
      </div>
    </section>
  );
};

export default MemberView;
