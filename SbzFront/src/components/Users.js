import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/table.css";

const Users = (props) => {
  const token = localStorage.getItem("token");
  const [users, setUsers] = useState([]);
 
  const navigate = useNavigate();

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {
    
    const response = await fetch(
      "http://localhost:8080/api/users",
      {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          Authorization: token,
        },
      }
    );
    if (response.status === 400 || response.status === 401) {
      return window.alert("invalid fetch!");
    }
    const data = await response.json();
   
    setUsers(data);
  };

  const toggleUserRole = async (userId) => {
    try {
      const response = await fetch(`http://localhost:8080/api/users/activate/${userId}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
          Authorization: token,
        },
      });
      if (!response.ok) {
        throw new Error("Failed to toggle user role");
      }
      // Update user role in the state
      setUsers((prevUsers) =>
        prevUsers.map((user) =>
          user.id === userId
            ? {
                ...user,
                role: user.role === "ROLE_USER" ? "ROLE_ADMIN" : "ROLE_USER",
              }
            : user
        )
      );
    } catch (error) {
      console.error(error.message);
    }
  };

  const handleUserClick = (user) => {
    localStorage.setItem("userId", user.id);
    navigate("/selectedUserHistory");
  };
  const userRole = localStorage.getItem("role");

  return (
    <div>
      <div>
        <div className="game-history-table-container">
          <table className="game-history-table">
            <thead>
              <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Username</th>
                <th>Email</th>

              </tr>
            </thead>
            <tbody>
              {users.map((user) => (
                <tr key={user.id}>
                  <td>{user.name}</td>
                  <td>{user.surname}</td>
                  <td>{user.username}</td>
                  <td>{user.email}</td>
                  {userRole === 'ROLE_ADMIN' && (
                <td>
                  {user.role === 'ROLE_USER' ? (
                    <button className="dugme make-admin" onClick={() => toggleUserRole(user.id)}>
                      Make as Admin
                    </button>
                  ) : (
                    <button className="dugme make-user" onClick={() => toggleUserRole(user.id)}>
                      Make as User
                    </button>
                  )}
                </td>
              )} 
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Users;