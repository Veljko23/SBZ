import React from "react";
import { useNavigate } from "react-router-dom";
import "../css/form.css";

const AdminHomePage = () => {
  const navigate = useNavigate();
  const onUsersListClickHandler = (event) => {
    return navigate("/users");
  };
  const onRegionsListClickHandler = (event) => {
    return navigate("/regions");
  };
  const onFruitsListClickHandler = (event) => {
    return navigate("/fruits");
  };
  const onEditProfileClickHandler = (event) => {
    const userId = localStorage.getItem("userId");
    return navigate(`/edit-profile/${userId}`);
  };
  const onCreateRegionClickHandler = (event) => {
    const userId = localStorage.getItem("userId");
    const role = localStorage.getItem("role");
    return navigate("/createRegion");
  };

  const onCreateFruitClickHandler = (event) => {
    return navigate("/createFruit");
  };

  const logoutClickHandler = () => {
    localStorage.clear()
    navigate('/')
  }

  const userRole = localStorage.getItem("role");
  

  return (
    <div className="registration-form-container">
      <div className="registration-form-wrapper">
          <div className="button-group">
            <button className="item" type="submit" onClick={onUsersListClickHandler}>
              Users
            </button>
            <button type="button" onClick={onRegionsListClickHandler}>
              Regions
            </button>
            <button type="button" onClick={onFruitsListClickHandler}>
              Fruits
            </button>
            <button type="button" onClick={onEditProfileClickHandler}>
              Edit profile
            </button>
            <button type="button" onClick={onCreateRegionClickHandler}>
              Create new region
            </button>
            <button type="button" onClick={onCreateFruitClickHandler}>
              Create new fruit
            </button>
            <button type="button" onClick={logoutClickHandler}>
              Logout
            </button>
          </div>
      </div>
    </div>
  );
};

export default AdminHomePage;
