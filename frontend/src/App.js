import logo from './logo.svg';
import './App.css';
import SignIn from './SignIn/SignIn'
import SIgnUp from './SignUp/SignUp';
import Dashboard from './mainPage/Dashboard';
import { Routes, Route } from 'react-router-dom'
import { useState } from 'react';


function App() {
 
 
 
 
  return (

    <>
      <Routes>
    
        <Route path='/signup' element={<SIgnUp />} />
        <Route exact path='signin' element={<SignIn />} />
        <Route exact path='dashboard' element={<Dashboard />} />
     
      </Routes>
    </>

  );
}

export default App;
