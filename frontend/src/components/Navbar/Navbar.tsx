import React, { Component } from "react";
import './Navbar.css';
import { NavLink } from "react-router-dom";

type NavbarProps = {
  //
};

class Navbar extends Component<NavbarProps, any> {
  public render() {
    return <div>
      <nav>
        <ul className="dark-primary-color">
          <li >
            <NavLink exact={true}
              activeClassName='is-active default-primary-color'
              to="/"
              className="text-primary-color">
              Inicio
            </NavLink>
          </li>
          <li>
            <NavLink 
            activeClassName='is-active default-primary-color' 
            to="/expenses" 
            className="text-primary-color">
              Gastos
              </NavLink>
          </li>
          <li>
            <NavLink 
            activeClassName='is-active default-primary-color' 
            to="/members" 
            className="text-primary-color">
              Miembros
              </NavLink>
          </li>
        </ul>
      </nav>
    </div>;
  }
}

export default Navbar;
