import React, { useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import "../css/table.css";

const CreateRegion = () => {

  const navigate = useNavigate();
  const role = localStorage.getItem("role");
  const token = localStorage.getItem("token");
  const [formData, setFormData] = useState({
    naziv: '',
    nadmorskaVisina: '',
    prosecnaGodisnjaTemperatura: '',
    minTemperatura:'',
    maxTemperatura: '',
    kolicinaPadavina: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

 


  ////////////////////////////////////////


  

  const handleSubmit = async (e) => {
    e.preventDefault();


    try {
      const response = await fetch("http://localhost:8080/api/regions/createRegion", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Accept: "application/json",
          Authorization: token,
        },
        body: JSON.stringify({
          naziv: formData.naziv,
          nadmorskaVisina: formData.nadmorskaVisina,
          prosecnaGodisnjaTemperatura: formData.prosecnaGodisnjaTemperatura,
          minTemperatura: formData.minTemperatura,
          maxTemperatura: formData.maxTemperatura,
          kolicinaPadavina: formData.kolicinaPadavina
        }),
      });

      if (response.ok) {
        const data = await response.json();
        console.log(data);
        window.alert("Region created successfully!");

        if (role === "ROLE_ADMIN") {
          navigate("/adminHomePage");
        } else {
          navigate("/");
        }
      } else {
        console.log('Error during creating');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  ////////////////////////////////////////

  const onLoginClickHandler = (event) => {
    return navigate("/");
  };

  return (
    <div className="form-registration">
    <h1>New region</h1>
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="nazvi">Naziv:</label>
        <input
          type="text"
          id="naziv"
          name="naziv"
          value={formData.naziv}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="nadmorskaVisina">Nadmorska Visina:</label>
        <input
          type="text"
          id="nadmorskaVisina"
          name="nadmorskaVisina"
          value={formData.nadmorskaVisina}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="prosecnaGodisnjaTemperatura">prosecnaGodisnjaTemperatura:</label>
        <input
          type="text"
          id="prosecnaGodisnjaTemperatura"
          name="prosecnaGodisnjaTemperatura"
          value={formData.prosecnaGodisnjaTemperatura}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="minTemperatura">minTemperatura:</label>
        <input
          type="text"
          id="minTemperatura"
          name="minTemperatura"
          value={formData.minTemperatura}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="maxTemperatura">maxTemperatura:</label>
        <input
          type="text"
          id="maxTemperatura"
          name="maxTemperatura"
          value={formData.maxTemperatura}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="picture">kolicinaPadavina:</label>
        <input
          type="text"
          id="kolicinaPadavina"
          name="kolicinaPadavina"
          value={formData.kolicinaPadavina}
          required
          onChange={handleChange}
        />
      </div>
      
      <button type="submit">Create</button>
    </form>
    <p></p>    
    </div>
  );
};

export default CreateRegion;
