import React, { useState, useEffect } from 'react';
import '../styles/select.css'
import { Dropdown, DropdownButton } from 'react-bootstrap'
const Select = () => {

  const [title, setTitle] = useState('Login');

  const clearLocaleStorage = (e) => {
    localStorage.clear();
  }
  useEffect(() => {
      if (localStorage.length > 0) {
          setTitle(localStorage.getItem('name'));
          console.log(title);
      }
  });

  return (

    <DropdownButton classname="dropdownButton" variant="none" id="dropdown-basic-button" title={title}>
      <Dropdown.Item href="/edit_profile">Edit Profile</Dropdown.Item>
      <Dropdown.Item href="/change_pass">Change Password</Dropdown.Item>
      <Dropdown.Item onClick={clearLocaleStorage} href="/login">Log Out</Dropdown.Item>
    </DropdownButton>

  );
};

export default Select;