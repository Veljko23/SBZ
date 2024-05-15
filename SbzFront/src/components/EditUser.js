import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/table.css";
import axios from "axios";

const EditUser = () => {
  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");
  const role = localStorage.getItem("role");
  const [user, setUser] = useState({
    id: "",
    name: "",
    surname: "",
    username: "",
    email: "",
    password: "",
  });

  const navigate = useNavigate();
    
  useEffect(() => {
    fetchUser();
  }, []);

  const fetchUser = async () => {
    
    try {
      const response = await axios.get(`http://localhost:8080/api/users/${userId}`, {
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          Authorization: token,
        },
      });
  
      setUser(response.data);
    } catch (error) {
      console.error("Error fetching user:", error);
    }
  };
  
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setUser((prevUser) => ({ ...prevUser, [name]: value }));
  };

  const handleChangePassword = () => {
    navigate("/changePassword");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");

    fetch(`http://localhost:8080/api/users/${user.id}`, {
      method: "PUT",
      headers: { 
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization : token },
      body: JSON.stringify(user),
    })
      .then((response) => {
        if (response.status === 400) {
          return window.alert("update failed!");
        }
        else{
          window.history.back();
          return window.alert("Successfully update!")
        }
      })
      .catch((error) => {
        console.error(error);
        window.alert("An error occurred during update.");
      });
  };

  return (
    <div className="form-edit">
      <h1>Edit Profile</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input type="text" name="name" value={user.name} onChange={handleInputChange} />
        </label>
        <label>
          Surname:
          <input type="text" name="surname" value={user.surname} onChange={handleInputChange} />
        </label>
        <label>
          Username:
          <input type="text" name="username" value={user.username} readOnly  style={{ opacity: 0.5, pointerEvents: 'none' }} />
        </label>
        <label>
          Email:
          <input name="email" value={user.email} onChange={handleInputChange} required/>
        </label>

        <button type="submit">Save</button>
      </form>
      <button className="changePassword" onClick={handleChangePassword}>
        Change Password
      </button>
    </div>
  );
};

export default EditUser;
