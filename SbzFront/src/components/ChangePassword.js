import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const ChangePassword = () => {
  const [passwords, setPasswords] = useState({
    oldPassword: "",
    newPassword: "",
    confirmPassword: "",
  });

  const navigate = useNavigate();

  const handlePasswordChange = (e) => {
    const { name, value } = e.target;
    setPasswords((prevPasswords) => ({ ...prevPasswords, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (passwords.newPassword !== passwords.confirmPassword) {
      return window.alert("New passwords do not match!");
    }

    const token = localStorage.getItem("token");

    const data = {
      oldPassword: passwords.oldPassword,
      newPassword: passwords.newPassword,
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/api/users/change-password",
        data,
        {
          headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
            Authorization: token,
          },
        }
      );

      if (response.status === 200) {
        window.alert("Password changed successfully!");
        navigate(window.history.back());
      } else {
        window.alert("Password change failed!");
      }
    } catch (error) {
      console.error(error);
      window.alert("An error occurred while changing the password.");
    }
  };

  return (
    <div className="form-edit">
      <h1>Change Password</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Old Password:
          <input
            type="password"
            name="oldPassword"
            value={passwords.oldPassword}
            onChange={handlePasswordChange}
            required
          />
        </label>
        <label>
          New Password:
          <input
            type="password"
            name="newPassword"
            value={passwords.newPassword}
            onChange={handlePasswordChange}
            required
          />
        </label>
        <label>
          Confirm New Password:
          <input
            type="password"
            name="confirmPassword"
            value={passwords.confirmPassword}
            onChange={handlePasswordChange}
            required
          />
        </label>
        <button type="submit">Change Password</button>
      </form>
    </div>
  );
};

export default ChangePassword;
