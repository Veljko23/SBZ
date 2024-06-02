import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/form.css";

const UserHomePage = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  const onFruitsListClickHandler = (event) => {
    return navigate("/fruits");
  };
  const onGetSuggestBasicClickHandler = (event) => {
    return navigate("/suggestBasic");
  };

  const onGetSuggestComplexClickHandler = (event) => {
    return navigate("/suggestComplex");
  };
  
  const onEditProfileClickHandler = (event) => {
    const userId = localStorage.getItem("userId");
    return navigate(`/edit-profile/${userId}`);
  };

  const logoutClickHandler = () => {
    localStorage.clear()
    navigate('/')
  }
  

  return (
    <div className="registration-form-container">
      <div className="registration-form-wrapper">
          <div className="button-group">
          <button type="button" onClick={onFruitsListClickHandler}>
              Fruits
            </button>

            <button type="button" onClick={onGetSuggestBasicClickHandler}>
              Suggest:Basic
            </button>

            <button type="button" onClick={onGetSuggestComplexClickHandler}>
              Suggest:Complex
            </button>

            <button type="button" onClick={onEditProfileClickHandler}>
              Edit profile
            </button>

            <button type="button" onClick={logoutClickHandler}>
              Logout
            </button>
          </div>
      </div>
    </div>
  );
};

export default UserHomePage;
