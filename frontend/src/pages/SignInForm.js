import React, { Component, useState } from "react";
import { Link } from "react-router-dom";
import { BsFacebook, BsInstagram, BsGithub } from "react-icons/bs";
import './sign.style.css';
import axios from "axios";

const  SignInForm = () =>
{
 const [emailId,setEmail]= useState('');
 const [passwordId,setPassword]= useState('');
 

const handleSubmit=(event) =>{
    event.preventDefault();
    console.log("The form was submitted with the following data:");
    const user={
      email: emailId,
      password: passwordId
    }
    console.log(user);
    axios
      .post("http://localhost:8989/chat/login", user)
      .then((response) => {
        console.log(response);
      });
  }
    return (
      <div className="formCenter">
        <form className="formFields" onSubmit={handleSubmit}>
          <div className="formField">
            <label className="formFieldLabel" htmlFor="email">
              E-Mail Address
            </label>
            <input
              type="text"
              id="email"
              className="formFieldInput"
              placeholder="Enter your email"
              name="email"
              value={emailId}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>

          <div className="formField">
            <label className="formFieldLabel" htmlFor="password">
              Password
            </label>
            <input
              type="password"
              id="password"
              className="formFieldInput"
              placeholder="Enter your password"
              name="password"
              value={passwordId}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>

          <div className="formField">
            <button className="formFieldButton" type='submit'>Sign In</button>{" "}
            <Link to="/" className="formFieldLink">
              Create an account
            </Link>
          </div>

          <div className="socialMediaButtons">
            <div className="facebookButton">
              <a
                style={{ color: "white", margin: "2px", padding: "5px" }}
                href="https://www.instagram.com/techvalens"
              >
                <BsInstagram />
              </a>
              <a
                style={{ color: "white", margin: "2px", padding: "5px" }}
                href="https://www.facebook.com/techvalens"
              >
                <BsFacebook />
              </a>

              <a
                style={{ color: "white", margin: "2px", padding: "5px" }}
                href="https://www.github.com/techvalens"
              >
                <BsGithub />
              </a>
            </div>
          </div>
        </form>
      </div>
    );

}

export default SignInForm;
