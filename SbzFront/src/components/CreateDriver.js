import React, { useState, useEffect} from 'react';
import { useNavigate } from 'react-router-dom';
import "../css/table.css";

const CreateDriver = () => {

  const navigate = useNavigate();
  const role = localStorage.getItem("role");
  const token = localStorage.getItem("token");
  const [formData, setFormData] = useState({
    name: '',
    surname: '',
    email: '',
    address:'',
    number: '',
    picture: '',
    password: '',
    confirmPassword: '',
    documents: '',
    vehicle: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

 


  ////////////////////////////////////////


  const [vehicleOptions, setVehicleOptions] = useState([]);

  useEffect(() => {
    const fetchVehicleOptions = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/vehicles");
        if (response.ok) {
          const data = await response.json();
          setVehicleOptions(data);
        } else {
          console.log('Failed to fetch vehicle options');
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };

    fetchVehicleOptions();
  }, []);


  

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log(formData.vehicle);
    if (formData.password !== formData.confirmPassword) {
      console.log("Passwords don't match");
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/api/drivers/createDriver", {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Accept: "application/json",
          Authorization: token,
        },
        body: JSON.stringify({
          name: formData.name,
          surname: formData.surname,
          email: formData.email,
          address: formData.address,
          number: formData.number,
          picture: formData.picture,
          password: formData.password,
          confirmPassword: formData.confirmPassword,
          documents: formData.documents,
          vehicle: formData.vehicle,
        }),
      });

      if (response.ok) {
        const data = await response.json();
        console.log(data);
        window.alert("Register successfully!");

        if (role === "ROLE_ADMIN") {
          navigate("/adminHomePage");
        } else {
          navigate("/");
        }
      } else {
        console.log('Registration failed');
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
    <h1>Registration form</h1>
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="name">Name:</label>
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
        <label htmlFor="surname">Surname:</label>
        <input
          type="text"
          id="surname"
          name="surname"
          value={formData.surname}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="number">Number:</label>
        <input
          type="text"
          id="number"
          name="number"
          value={formData.number}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="address">Address:</label>
        <input
          type="text"
          id="address"
          name="address"
          value={formData.address}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="picture">Picture:</label>
        <input
          type="text"
          id="picture"
          name="picture"
          value={formData.picture}
          required
          onChange={handleChange}
        />
      </div>
      <div>
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label htmlFor="confirmPassword">Confirm Password:</label>
        <input
          type="password"
          id="confirmPassword"
          name="confirmPassword"
          value={formData.confirmPassword}
          onChange={handleChange}
          required
        />
      </div>
      <div>
          <label htmlFor="vehicle">Vehicle:</label>
          <select
            id="vehicle"
            name="vehicle"
            value={formData.vehicle}
            onChange={handleChange}
            required
          >
            <option value="">Select a Vehicle</option>
            {vehicleOptions.map((vehicle) => (
              <option key={vehicle.id} value={vehicle.id}>
                {vehicle.type.name}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label htmlFor="documents">Documents:</label>
          <input
            type="text"
            id="documents"
            name="documents"
            value={formData.documents}
            onChange={handleChange}
            required
          />
        </div>
      <button type="submit">Register</button>
    </form>
    <p></p>
    
    <button type="button" onClick={onLoginClickHandler}>Login </button>
    
    </div>
  );
};

export default CreateDriver;
