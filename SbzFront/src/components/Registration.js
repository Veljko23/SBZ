import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import "../css/table.css";

const Registration = () => {

  const navigate = useNavigate();
  const role = localStorage.getItem("role");
  const [formData, setFormData] = useState({
    name: '',
    surname: '',
    username: '',
    email:'',
    password: '',
    confirmPassword: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    
    if (formData.password !== formData.confirmPassword) {
      console.log("Passwords don't match");
      window.alert("Passwords don't match!")
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/api/users/register", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Accept: "application/json",
        },
        body: JSON.stringify({
          name: formData.name,
          surname: formData.surname,
          username: formData.username,
          email: formData.email,
          password: formData.password
        }),
      });

      if (response.ok) {
        const data = await response.json();
        console.log(data);
        window.alert("Register successfully!");
  
        // Check user role and navigate accordingly
        if (role === "ROLE_ADMIN") {
          navigate("/adminHomePage");
        } else {
          navigate("/");
        }
      } else {
        console.log('Registration failed');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const onLoginClickHandler = (event) => {
    return navigate("/");
  };

  return (
    <div className="form-registration">
    <h1>Registration form</h1>
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          id="name"
          name="name"
          value={formData.name}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="surname">Surname:</label>
        <input
          type="text"
          id="surname"
          name="surname"
          value={formData.surname}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="username">Username:</label>
        <input
          type="text"
          id="username"
          name="username"
          value={formData.username}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          required
        />
      </div>
      
      <div>
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="confirmPassword">Confirm Password:</label>
        <input
          type="password"
          id="confirmPassword"
          name="confirmPassword"
          value={formData.confirmPassword}
          onChange={handleChange}
          required
        />
      </div>
      <button type="submit">Register</button>
    </form>
    <p></p>
    
    <button type="button" onClick={onLoginClickHandler}>Login </button>
    
    </div>
  );
};

export default Registration;
