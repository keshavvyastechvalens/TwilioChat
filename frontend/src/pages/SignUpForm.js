import React, { Component, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const SignUpForm = () => {
  
    const[emailIdH,setEmailId] =useState("");
    const[passwordIdH,setPasswordId] =useState("");
    const[ firstNameH,setfirstName] =useState("");
    const[lastNameH,setlastName] =useState("");
    const[contactNoH,setContactNo] =useState("");
    const[hasAgreedH,sethasAgreed] =useState(false);
    const [userNameH, setUserName] = useState('');

    

  const handleSubmit=(event) =>{
    event.preventDefault();
    console.log("The form was submitted with the following data:");
    const user = {
      firstName: firstNameH,
      lastName: lastNameH,
      emailId: emailIdH,
      contactNo: contactNoH,
      hasAgreed: hasAgreedH,
      password: passwordIdH,
    };
    console.log(user);
    axios
      .post("http://localhost:8989/chat/registeruser", user)
      .then((response) => {
        console.log(response);
      });
  }

    return (
      <div className="formCenter">
        <form onSubmit={handleSubmit} className="formFields">
          <div className="formField">
            <label className="formFieldLabel" htmlFor="firstName">
              First Name
            </label>
            <input
              type="text"
              id="firstName"
              className="formFieldInput"
              placeholder="Enter your first name"
              name="firstName"
              value={firstNameH}
              onChange={(e) => setfirstName(e.target.value)}
              required
            />
          </div>

          <div className="formField">
            <label className="formFieldLabel" htmlFor="lastName">
              Last Name
            </label>
            <input
              type="text"
              id="lastName"
              className="formFieldInput"
              placeholder="Enter your last name"
              name="lastName"
              value={lastNameH}
              onChange={(e) => setlastName(e.target.value)}
              required
            />
          </div>

          <div className="formField">
            <label className="formFieldLabel" htmlFor="userName">
              Username
            </label>
            <input
              type="text"
              id="userName"
              className="formFieldInput"
              placeholder="Enter your username"
              name="userName"
              value={userNameH}
              onChange={(e) => setUserName(e.target.value)}
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
              value={passwordIdH}
              onChange={(e) => setPasswordId(e.target.value)}
              required
            />
          </div>
          <div className="formField">
            <label className="formFieldLabel" htmlFor="emailId">
              E-Mail Address
            </label>
            <input
              type="emailId"
              id="emailId"
              className="formFieldInput"
              placeholder="Enter your emailId"
              name="emailId"
              value={emailIdH}
              onChange={(e) => setEmailId(e.target.value)}
              required
            />
          </div>

          <div className="formField">
            <label className="formFieldLabel" htmlFor="contactNo">
              Phone
            </label>
            <input
              type="text"
              id="contactNo"
              className="formFieldInput"
              placeholder="Enter your number"
              name="contactNo"
              value={contactNoH}
              onChange={(e) => setContactNo(e.target.value)}
              required
            />
          </div>

          <div className="formField">
            <label className="formFieldCheckboxLabel">
              <input
                className="formFieldCheckbox"
                type="checkbox"
                name="hasAgreed"
                value={hasAgreedH}
                onChange={(e) => sethasAgreed(e.target.value)}
                required
              />{" "}
              I agree all statements in{" "}
              <a href="null" className="formFieldTermsLink">
                terms of service
              </a>
            </label>
          </div>

          <div className="formField">
            <button className="formFieldButton">Sign Up</button>{" "}
            <Link to="/sign-in" className="formFieldLink">
              I'm already member
            </Link>
          </div>
        </form>
      </div>
    );
  
}
export default SignUpForm;
