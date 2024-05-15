import React from "react";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../css/table.css";
import axios from "axios";

const EditFruit = () => {
  const token = localStorage.getItem("token");
  const fruitId = localStorage.getItem("fruitId");
  const role = localStorage.getItem("role");
  const [fruit, setFruit] = useState({
    id: "",
    name: "",
    opis:"",
    nadmorskaVisina: "",
    minTemperatura: "",
    maxTemperatura: "",
    kolicinaPadavina:"",
    prosecnaGodisnjaTemperatura: "",
    phCategory:"",
    calcCategory:""
  });

  const navigate = useNavigate();
    
  useEffect(() => {
    fetchFruit();
  }, []);
  const [phEnum, setPhEnums] = useState([]);

  useEffect(() => {
    const fetchPhEnums = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/fruits/phValues");
        if (response.ok) {
          const data = await response.json();
          setPhEnums(data);
        } else {
          console.log('Failed to fetch vehicle options');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchPhEnums();
  }, []);

  const [calcEnum, setCalcEnums] = useState([]);

  useEffect(() => {
    const fetchCalcEnums = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/fruits/calcValues");
        if (response.ok) {
          const data = await response.json();
          setCalcEnums(data);
        } else {
          console.log('Failed to fetch vehicle options');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchCalcEnums();
  }, []);
  const fetchFruit = async () => {
    
    try {
      const response = await axios.get(`http://localhost:8080/api/fruits/${fruitId}`, {
        headers: {
          "Content-Type": "application/json",
          Accept: "application/json",
          Authorization: token,
        },
      });
  
      setFruit(response.data);
    } catch (error) {
      console.error("Error fetching user:", error);
    }
  };
  
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFruit((prevFruit) => ({ ...prevFruit, [name]: value }));
  };

  const handleChangeRegion = () => {
    navigate("/changePassword");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");

    fetch(`http://localhost:8080/api/fruits/${fruit.id}`, {
      method: "PUT",
      headers: { 
        "Content-Type": "application/json",
        Accept: "application/json",
        Authorization : token },
      body: JSON.stringify(fruit),
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
      <h1>Edit Fruit</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Naziv:
          <input type="text" name="name" value={fruit.name} onChange={handleInputChange} />
        </label>
        <label>
          Opis:
          <input type="text" name="opis" value={fruit.opis} onChange={handleInputChange} />
        </label>
        <label>
          Nadmorska Visina:
          <input type="text" name="nadmorskaVisina" value={fruit.nadmorskaVisina} onChange={handleInputChange} />
        </label>
        <label>
        minTemperatura:
          <input type="text" name="minTemperatura" value={fruit.minTemperatura} onChange={handleInputChange} required/>
        </label>
        <label>
        maxTemperatura:
          <input type="text" name="maxTemperatura" value={fruit.maxTemperatura} onChange={handleInputChange} required/>
        </label>
        <label>
        kolicinaPadavina:
          <input type="text" name="kolicinaPadavina" value={fruit.kolicinaPadavina} onChange={handleInputChange} required/>
        </label>
        <label>
        prosecnaGodisnjaTemperatura:
          <input type="text" name="prosecnaGodisnjaTemperatura" value={fruit.prosecnaGodisnjaTemperatura} onChange={handleInputChange} />
        </label>
        <label>
          Ph Kategorija:
          <select name="phCategory" value={fruit.phCategory} onChange={handleInputChange}>
            <option value="">Odaberi kategoriju</option>
            {phEnum.map((option) => (
              <option key={option} value={option}>
                {option}
              </option>
            ))}
          </select>
        </label>
        <label>
          Calc Kategorija:
          <select name="calcCategory" value={fruit.calcCategory} onChange={handleInputChange}>
            <option value="">Odaberi kategoriju</option>
            {calcEnum.map((calc) => (
              <option key={calc} value={calc}>
                {calc}
              </option>
            ))}
          </select>
        </label>
        <button type="submit">Save</button>
      </form>
    </div>
  );
};

export default EditFruit;
