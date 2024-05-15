import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/table.css";

const ForgotPassword = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [message, setMessage] = useState("");

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    const response = await fetch(`http://localhost:8080/api/users/forgot-password?email=${email}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ email }),
    });

    if (response.status === 404) {
      setMessage("User not found.");
    } else if (response.ok) {
      setMessage("Password reset email sent. Check your inbox.");
    } else {
      setMessage("Something went wrong. Please try again later.");
    }
  };

  return (
    <div className="form-registration">
      <h1>Forgot Password</h1>
      <form onSubmit={handleSubmit}>
          <div>
            <h3>Please enter your email address</h3>
          </div>
          <div>
          <input type="email" value={email} onChange={handleEmailChange} required />
          </div>
        <button type="submit">Reset Password</button>
      </form>
      {message && <p>{message}</p>}
      <p></p>
      <button onClick={() => navigate("/login")}>Back to Login</button>
    </div>
  );
};

export default ForgotPassword;
