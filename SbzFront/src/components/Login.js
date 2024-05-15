import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/form.css";

const Login = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState({
    username: "",
    password: "",
  });

  const handleChange = (event) => {
    setUser({
      ...user,
      [event.target.name]: event.target.value,
    });
  };

  const onLoginClickHandler = async (event) => {
    event.preventDefault();

    const response = await fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(user),
    });
    if (response.status === 400 || response.status === 401) {
      return window.alert("Wrong username or password!");
    }
    
    
    const data = await response.json();
    console.log("API RESPONSE:", data);
    const tokenFromData = data.accessToken;
    const token = `Bearer ${tokenFromData}`;
    localStorage.setItem("token", token);

    try {
      const response1 = await fetch(
        "http://localhost:8080/api/users/getLoggedUser",
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
            Authorization: token,
          },
        }
      );
      const data1 = await response1.json();
      const role = data1.message;
      const id = data1.id;

      
      if (role === "ROLE_USER") {
        localStorage.setItem("userId", id); 
        localStorage.setItem("role", role);
        navigate("/userHomePage");
      } else if (role === "ROLE_ADMIN") {
        localStorage.setItem("userId", id);
        localStorage.setItem("role", role);
        navigate("/adminHomePage"); 
      }
    } catch (error) {
      console.log("Error", error);
    }
  };

  const onRegistrateClickHandler = (event) => {
    localStorage.setItem("role", "role");
    return navigate("/registration");
  };

  
  const onForgotPasswordClickHandler = (event) => {
    return navigate("/forgot-password");
  };
  return (
    <div className="login-form-container">
      <div className="login-form-wrapper">
        <form className="login-form" onSubmit={onLoginClickHandler}>
          <div className="input-group">
            <label htmlFor="Username">Username</label>
            <input onChange={handleChange} name="username" type="text" required />
          </div>
          <div className="input-group">
            <label htmlFor="Password" className="item">
              Password
            </label>
            <input
              onChange={handleChange}
              name="password"
              type="password"
              required
            />
          </div>
          <div className="button-group">
            <button className="item" type="submit">
              Login
            </button>
            <button type="button" onClick={onRegistrateClickHandler}>
              Don't have account?
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
