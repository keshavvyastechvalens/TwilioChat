import React, { Component } from "react";
import { Link } from "react-router-dom";

class SignUpForm extends Component {
  constructor() {
    super();

    this.state = {
      emailId: "",
      password: "",
      firstName: "",
      lastName: "",
      contactNo: "",
      userName: "",
      hasAgreed: false,
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    let target = event.target;
    let value = target.type === "checkbox" ? target.checked : target.value;
    let name = target.name;

    this.setState({
      [name]: value
    });
  }

  handleSubmit(e) {
    e.preventDefault();
    console.log("The form was submitted with the following data:");
    console.log(this.state);
    this.setState([])
  }



  render() {
    return (
      <div className="formCenter">
        <form onSubmit={this.handleSubmit} className="formFields">
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
              value={this.state.firstName}
              onChange={this.handleChange}
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
              value={this.state.lastName}
              onChange={this.handleChange}
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
              value={this.state.userName}
              onChange={this.handleChange}
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
              value={this.state.password}
              onChange={this.handleChange}
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
              value={this.state.emailId}
              onChange={this.handleChange}
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
              value={this.state.contactNo}
              onChange={this.handleChange}
              required
            />
          </div>

          <div className="formField">
            <label className="formFieldCheckboxLabel">
              <input
                className="formFieldCheckbox"
                type="checkbox"
                name="hasAgreed"
                value={this.state.hasAgreed}
                onChange={this.handleChange}
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
}
export default SignUpForm;
