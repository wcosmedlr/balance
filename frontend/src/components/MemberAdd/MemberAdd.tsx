import React, { useEffect } from "react";
import './MemberAdd.css';
import { AppContext } from "../../AppContext";
import { buildMember, Member } from "../../models/member";

interface MemberAddProps {
  onClick?: (handle: Member) => void;
}

export const MemberAdd: React.FC<MemberAddProps> = ({onClick}) => {

  const {addMember, members} = React.useContext(AppContext)
  const [name, setName] = React.useState<string>('');

  useEffect(() => {
    setName('Miembro ' + (members.length + 1))
  }, [members])

  return (
    <section>
      <div>
       <h1 className="accent-color text-primary-color">Agregar uno nuevo</h1>
       <article className="card">
      <form id="myform">
        <p>Nombre
        <input
          type="text"
          value={name}
          onChange={e => setName(e.target.value)}
        />
      </p>
      
      </form>
      {
        <button form="member-add-form" type="button" onClick={() => { 
          const member = buildMember({
            name: name
          })
          addMember(member);
          onClick && onClick(member); 
        }}>Agregar miembro</button>
      }
</article>
</div>
    </section>
  );
};

export default MemberAdd;
