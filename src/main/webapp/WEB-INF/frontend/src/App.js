import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import Head from './components/Head';
import 'bootstrap/dist/css/bootstrap.min.css';
import './styles/App.css';
import Login from './components/Login';
import SignUp from './components/SignUp';
import EditProfile from './components/EditProfile';
import ChangePass from './components/ChangePass';
import MainPage from './pages/MainPage';



function App() {
  return (

    <BrowserRouter>
      <Head />
      <Route path="/change_pass">
        <ChangePass />
      </Route>
      <Route path="/edit_profile">
        <EditProfile />
      </Route>
      <Route path="/login">
        <Login />
      </Route>
      <Route path="/signUp">
        <SignUp />
      </Route>
      <Route path="/home_page">
        <MainPage />
      </Route>
    </BrowserRouter>
  );
}

export default App;
