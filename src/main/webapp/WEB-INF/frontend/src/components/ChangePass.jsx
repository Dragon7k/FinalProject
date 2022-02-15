import React, { useState } from "react";
import '../styles/EditProfile.css';
import { Button } from "react-bootstrap";
import UserService from "../services/UserService";

const ChangePass = () => {


    const [oldPassword, setOldPassword] = useState('');
    const [password, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');


    const changeInfo = (e) => {

        const updateUserPass = {
            password
        }
        console.log(updateUserPass);
        validationPasswords(updateUserPass);
    }

    async function validationPasswords(updateUserPass) {
        const response = await UserService.getUserOldPass(localStorage.getItem('id'));
        if (oldPassword === response.data && password === confirmNewPassword) {
            console.log(password);
            updateUserPassInDataBase(updateUserPass)
        }else{
            alert('passwords is not equals')
        }
    }

    async function updateUserPassInDataBase(userPass) {
        await UserService.updateUserPass(localStorage.getItem('id'), userPass);
        alert('update is sucsesfully')
    }

    return (
        <form className="formSignUp">

            <h2 className="form_title">Change Password</h2>

            <div class="formInner">
                <input class="text input--active" type="password" id="Old Password" placeholder="Old password"
                    value={oldPassword}
                    onChange={e => setOldPassword(e.target.value)}
                />
                <input class="text input--active" type="password" id="New Password" placeholder="New password"
                    value={password}
                    onChange={e => setNewPassword(e.target.value)} />
                <input class="text input--active" type="password" id="Confirm New Password" placeholder="Confirm password"
                    value={confirmNewPassword}
                    onChange={e => setConfirmNewPassword(e.target.value)} />

            </div>
            <Button onClick={changeInfo} className="formBtn">Save</Button>


        </form>
    );
};
export default ChangePass;