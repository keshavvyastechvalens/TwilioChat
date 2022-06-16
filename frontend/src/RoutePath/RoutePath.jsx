import React from 'react'
import { Routes, Route, Link } from "react-router-dom";
import SignUpForm from "../pages/SignUpForm"
import SignInForm from "../pages/SignInForm";
import "../App.css";
import Dashboard from '../pages/mainPage/Dashboard';

function RoutePath() {
  return (
    <div>
      <div className="App">
        {/* <div className="appAside" />
        <div className="appForm">
          <div className="pageSwitcher">
            <Link
              to="/sign-in"
              exact="pageSwitcherItem-active"
              className="pageSwitcherItem"
            >
              Sign In
            </Link>
            <Link
              to="/"
              exact="pageSwitcherItem-active"
              className="pageSwitcherItem"
            >
              Sign Up
            </Link>
          </div>

          <div className="formTitle">
            <Link
              to="/sign-in"
              exact="formTitleLink-active"
              className="formTitleLink"
            >
              Sign In
            </Link>{" "}
            or{" "}
            <Link to="/" exact="formTitleLink-active" className="formTitleLink">
              Sign Up
            </Link>
          </div>

           
        </div> */}
        <Dashboard/>
      </div>
    </div>
  );
}

export default RoutePath