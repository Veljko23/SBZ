import './App.css';
import React from "react";
import {
  Route,
  Routes,
} from "react-router-dom";
import { useState } from "react";
import Login from './components/Login';
import UserHomePage from './components/UserHomePage';
import AdminHomePage from './components/AdminHomePage';
import Registration from './components/Registration';
import Regions from './components/Regions';
import CreateRegion from './components/CreateRegion';
import EditRegion from './components/EditRegion';
import Fruits from './components/Fruits';
import CreateFruit from './components/CreateFruit';
import EditFruit from './components/EditFruit';
import SuggestFruitBasic from './components/SuggestFruitBasic';
import SuggestFruitComplex from './components/SuggestFruitComplex';
import Users from './components/Users';
import EditUser from './components/EditUser';
import ChangePassword from './components/ChangePassword';
import ForgotPassword from './components/ForgotPassword';
function App() {
  return (
    <Routes >
        
        <Route index path="/" element={<Login />} />
        <Route path="/registration" element={<Registration />} />
        <Route path='/adminHomePage' element={<AdminHomePage /> } />
        <Route path='/userHomePage' element={<UserHomePage /> } />
        <Route path="/regions" element={<Regions />} />
        <Route path='/createRegion' element={<CreateRegion /> } />
        <Route path='/editRegion/:id' element={<EditRegion /> } />
        <Route path="/fruits" element={<Fruits />} />
        <Route path='/createFruit' element={<CreateFruit /> } />
        <Route path='/editFruit/:id' element={<EditFruit /> } />
        <Route path='/suggestBasic' element={< SuggestFruitBasic/> } />
        <Route path='/suggestComplex' element={< SuggestFruitComplex/> } />

        <Route path='/users' element={<Users /> } />
        <Route path='/edit-profile/:id' element={<EditUser /> } />
        <Route path='/edit-profile' element={<EditUser /> } />
        <Route path='/changePassword' element={<ChangePassword /> } />
        <Route path='/forgot-password' element={<ForgotPassword /> } />
        
    </Routes>
  );
}
export default App;
