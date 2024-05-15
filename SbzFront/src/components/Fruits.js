import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/table.css";

const Fruits = (props) => {
  const token = localStorage.getItem("token");
  const [fruits, setFruits] = useState([]);
  const [searchResults, setSearchResults] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [role, setRole] = useState(localStorage.getItem("role"));
  const [showModal, setShowModal] = useState(false);
  const [modalContent, setModalContent] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    fetchFruits();
  }, []);

  const fetchFruits = async () => {
    const response = await fetch("http://localhost:8080/api/fruits", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization: token,
      },
    });

    if (response.status === 400 || response.status === 401) {
      return window.alert("invalid fetch!");
    }

    const data = await response.json();
    setFruits(data);
  };

  const handleEdit = (id) => {
    localStorage.setItem("fruitId", id);
    navigate(`/editFruit/${id}`);
  };

  const handleDescriptionClick = (opis) => {
    setModalContent(opis);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false);
    setModalContent("");
  };

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleSearch = async () => {
    const response = await fetch(`http://localhost:8080/api/fruits/search/${searchQuery}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization: token,
      },
    });

    if (response.status === 400 || response.status === 401) {
      return window.alert("invalid fetch!");
    }

    const data = await response.json();
    setSearchResults(data);
  };

  const renderTableRows = (items) => {
    return items.map((fruit) => (
      <tr key={fruit.id}>
        <td>{fruit.name}</td>
        <td>
          {fruit.opis.length > 15 ? (
            <span
              onClick={() => handleDescriptionClick(fruit.opis)}
              style={{ cursor: "pointer" }}
            >
              {fruit.opis.substring(0, 15)}...
            </span>
          ) : (
            fruit.opis
          )}
        </td>
        <td>{fruit.nadmorskaVisina}</td>
        <td>{fruit.minTemperatura}</td>
        <td>{fruit.maxTemperatura}</td>
        <td>{fruit.kolicinaPadavina}</td>
        <td>{fruit.prosecnaGodisnjaTemperatura}</td>
        <td>{fruit.phCategory}</td>
        <td>{fruit.calcCategory}</td>
        {role === "ROLE_ADMIN" && (
          <td>
            <button onClick={() => handleEdit(fruit.id)}>Izmeni</button>
          </td>
        )}
      </tr>
    ));
  };

  return (
    <div>
      <div className="game-history-table-container">
        <table className="game-history-table">
          <thead>
            <tr>
              <th>Naziv</th>
              <th>Opis</th>
              <th>Nadmorska visina</th>
              <th>Min.temp.</th>
              <th>Maks.temp.</th>
              <th>Kolicina padavina</th>
              <th>Pros. godisnja temp.</th>
              <th>Ph kategorija</th>
              <th>Calc kategorija</th>
              {role === "ROLE_ADMIN" && <th>Akcije</th>}
            </tr>
          </thead>
          <tbody>
            {renderTableRows(fruits)}
          </tbody>
        </table>
      </div>
      
      <div className="search-container">
        <input
          type="text"
          value={searchQuery}
          onChange={handleSearchChange}
          placeholder="Pretraži voće..."
        />
        <button onClick={handleSearch}>Pretraži</button>
      </div>
    
      {searchResults.length > 0 ? (
        <div className="game-history-table-container">
          <table className="game-history-table">
            <thead>
              <tr>
                <th>Naziv</th>
                <th>Opis</th>
                <th>Nadmorska visina</th>
                <th>Min.temp.</th>
                <th>Maks.temp.</th>
                <th>Kolicina padavina</th>
                <th>Pros. godisnja temp.</th>
                <th>Ph kategorija</th>
                <th>Calc kategorija</th>
                {role === "ROLE_ADMIN"}
              </tr>
            </thead>
            <tbody>
              {renderTableRows(searchResults)}
            </tbody>
          </table>
        </div>
      ) : (
        searchQuery && <p>Nema rezultata za traženi pojam</p>

      )}

      {showModal && (
        <div
          style={{
            position: "fixed",
            top: 0,
            left: 0,
            width: "100%",
            height: "100%",
            backgroundColor: "rgba(0,0,0,0.5)",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
          onClick={closeModal}
        >
          <div
            style={{
              backgroundColor: "white",
              padding: "20px",
              borderRadius: "8px",
              maxWidth: "500px",
              width: "100%",
              position: "relative",
            }}
            onClick={(e) => e.stopPropagation()}
          >
            <button
              style={{
                position: "absolute",
                top: "10px",
                right: "10px",
                background: "none",
                border: "none",
                fontSize: "1.5rem",
                cursor: "pointer",
              }}
              onClick={closeModal}
            >
              &times;
            </button>
            <div>{modalContent}</div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Fruits;
