import logo from './logo.svg';
import './App.css';
import SignIn from './SignIn/SignIn'
import SIgnUp from './SignUp/SignUp';
import Dashboard from './mainPage/Dashboard';
import { Routes, Route } from 'react-router-dom'
import React,{ useState } from 'react';
export const ChannelContext = React.createContext();

function App() {
 
  const [channel, setChannel] = useState({}); 
 
 
  return (

    <>
      <ChannelContext.Provider value={[channel,setChannel]}>
      <Routes>
        <Route path='/signup' element={<SIgnUp />} />
        <Route exact path='signin' element={<SignIn />} />
        <Route exact path='dashboard' element={<Dashboard />} />
      </Routes>
      </ChannelContext.Provider>
    </>

  );
}

export default App;
