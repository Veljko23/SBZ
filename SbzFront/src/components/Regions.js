import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/table.css";

const Regions = (props) => {
  const token = localStorage.getItem("token");
  const [regions, setRegions] = useState([]);
 
  const navigate = useNavigate();

  useEffect(() => {
    fetchRegions();
  }, []);

  const fetchRegions = async () => {
    
    const response = await fetch(
      "http://localhost:8080/api/regions",
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
   
    setRegions(data);
  };

  const handleEdit = (id) => {
    localStorage.setItem("regionId", id);
    navigate(`/editRegion/${id}`);
  };
  
  const userRole = localStorage.getItem("role");

  return (
    <div>
      <div>
        <div className="game-history-table-container">
          <table className="game-history-table">
            <thead>
              <tr>
                <th>Naziv</th>
                <th>Nadmorska visina</th>
                <th>Prosecna godisnja temperatura</th>
                <th>Min. temperatura</th>
                <th>Maks. temperatura</th>
                <th>Kolicina padavina</th>
              </tr>
            </thead>
            <tbody>
              {regions.map((region) => (
                <tr key={region.id}>
                  <td>{region.naziv}</td>
                  <td>{region.nadmorskaVisina}</td>
                  <td>{region.prosecnaGodisnjaTemperatura}</td>
                  <td>{region.minTemperatura}</td>
                  <td>{region.maxTemperatura}</td>
                  <td>{region.kolicinaPadavina}</td>
                  <td>
        <button onClick={() => handleEdit(region.id)}>Izmeni</button>
      </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default Regions;