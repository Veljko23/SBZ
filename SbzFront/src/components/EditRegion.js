import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/table.css";
import axios from "axios";

const EditRegion = () => {
  const token = localStorage.getItem("token");
  const regionId = localStorage.getItem("regionId");
  const role = localStorage.getItem("role");
  const [region, setRegion] = useState({
    id: "",
    naziv: "",
    nadmorskaVisina: "",
    prosecnaGodisnjaTemperatura: "",
    minTemperatura: "",
    maxTemperatura: "",
    kolicinaPadavina:""
  });

  const navigate = useNavigate();
    
  useEffect(() => {
    fetchRegion();
  }, []);

  const fetchRegion = async () => {
    
    try {
      const response = await axios.get(`http://localhost:8080/api/regions/${regionId}`, {
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          Authorization: token,
        },
      });
  
      setRegion(response.data);
    } catch (error) {
      console.error("Error fetching user:", error);
    }
  };
  
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setRegion((prevRegion) => ({ ...prevRegion, [name]: value }));
  };

  const handleChangeRegion = () => {
    navigate("/changePassword");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");

    fetch(`http://localhost:8080/api/regions/${region.id}`, {
      method: "PUT",
      headers: { 
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization : token },
      body: JSON.stringify(region),
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
      <h1>Edit Region</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Naziv:
          <input type="text" name="naziv" value={region.naziv} onChange={handleInputChange} />
        </label>
        <label>
          Nadmorska Visina:
          <input type="text" name="nadmorskaVisina" value={region.nadmorskaVisina} onChange={handleInputChange} />
        </label>
        <label>
        prosecnaGodisnjaTemperatura:
          <input type="text" name="prosecnaGodisnjaTemperatura" value={region.prosecnaGodisnjaTemperatura} onChange={handleInputChange} />
        </label>
        <label>
        minTemperatura:
          <input type="text" name="minTemperatura" value={region.minTemperatura} onChange={handleInputChange} required/>
        </label>
        <label>
        maxTemperatura:
          <input type="text" name="maxTemperatura" value={region.maxTemperatura} onChange={handleInputChange} required/>
        </label>
        <label>
        kolicinaPadavina:
          <input type="text" name="kolicinaPadavina" value={region.kolicinaPadavina} onChange={handleInputChange} required/>
        </label>

        <button type="submit">Save</button>
      </form>
    </div>
  );
};

export default EditRegion;
