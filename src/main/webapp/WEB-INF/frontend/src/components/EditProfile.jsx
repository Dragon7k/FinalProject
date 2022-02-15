import React, { useState } from "react";
import '../styles/EditProfile.css';
import { Button } from "react-bootstrap";
import UserService from "../services/UserService";

const EditProfile = () => {

    const [email, setEmail] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [phoneNumber, setNumber] = useState('');
    

    const changeInfo = (e) => {
      //  if (password === secondPassword) {
             const updateUser = {
                email,
                firstName,
                lastName,
                phoneNumber
            }
            console.log(updateUser);
            updateUserInDataBase(updateUser);
            localStorage.setItem('name',firstName);
    }

    async function updateUserInDataBase(user) {
        await UserService.updateUser(localStorage.getItem('id'),user);
        alert('update is sucsesfully')
    }

    return (
        <form className="formSignUp">
            
            <h2 className="form_title">Edit Profile</h2>

            <div class="formInner">
                <input class="text input--active" id="First Name" placeholder="First Name" 
                value={firstName}
                onChange={e => setFirstName(e.target.value)}
                />
                <input class="text input--active" id="Last Name" placeholder="Last Name" 
                  value={lastName}
                  onChange={e => setLastName(e.target.value)}/>
                <input class="text input--active" type="email" id="email" placeholder="E-mail" 
                  value={email}
                  onChange={e => setEmail(e.target.value)}/>
                <input class="text input--active" id="number" placeholder="Phone Number" 
                  value={phoneNumber}
                  onChange={e => setNumber(e.target.value)}/>
            </div>
            <Button onClick={changeInfo}className="formBtn">Save</Button>
           

        </form>
    );
};
export default EditProfile;