import React, { useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import "../css/table.css";

const CreateFruit = () => {

  const navigate = useNavigate();
  const role = localStorage.getItem("role");
  const token = localStorage.getItem("token");
  const [formData, setFormData] = useState({
    name: '',
    opis:'',
    nadmorskaVisina: '',
    minTemperatura:'',
    maxTemperatura: '',
    kolicinaPadavina: '',
    prosecnaGodisnjaTemperatura: '',
    phCategory:'',
    calcCategory:''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

 


  ////////////////////////////////////////
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

  const handleSubmit = async (e) => {
    e.preventDefault();


    try {
      const response = await fetch("http://localhost:8080/api/fruits/createFruit", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Accept: "application/json",
          Authorization: token,
        },
        body: JSON.stringify({
          name: formData.name,
          opis: formData.opis,
          nadmorskaVisina: formData.nadmorskaVisina,
          minTemperatura: formData.minTemperatura,
          maxTemperatura: formData.maxTemperatura,
          kolicinaPadavina: formData.kolicinaPadavina,
          prosecnaGodisnjaTemperatura: formData.prosecnaGodisnjaTemperatura,
          phCategory: formData.phCategory,
          calcCategory: formData.calcCategory
        }),
      });

      if (response.ok) {
        const data = await response.json();
        console.log(data);
        window.alert("Fruit created successfully!");

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
    <h1>New fruit</h1>
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="name">Naziv:</label>
        <input
          type="text"
          id="name"
          name="name"
          value={formData.name}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="opis">Opis:</label>
        <input
          type="text"
          id="opis"
          name="opis"
          value={formData.opis}
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
        <label>Ph Kategorija:</label>
        <select
  value={formData.phCategory}
  onChange={(e) => setFormData({ ...formData, phCategory: e.target.value })}
  required
>
  <option value="">Odaberi kategoriju</option>
  {Array.isArray(phEnum) && phEnum.map((penum) => (
    <option key={penum} value={penum}>
      {penum}
    </option>
  ))}
</select>
      </div>

      <div>
        <label>Calc Kategorija:</label>
        <select
  value={formData.calcCategory}
  onChange={(e) => setFormData({ ...formData, calcCategory: e.target.value })}
  required
>
  <option value="">Odaberi kategoriju</option>
  {Array.isArray(calcEnum) && calcEnum.map((cenum) => (
    <option key={cenum} value={cenum}>
      {cenum}
    </option>
  ))}
</select>
      </div>
      
      <button type="submit">Create</button>
    </form>
    <p></p>    
    </div>
  );
};

export default CreateFruit;
