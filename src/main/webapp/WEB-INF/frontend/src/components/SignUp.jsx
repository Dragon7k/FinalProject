import React from "react";
import '../styles/SignUp.css';
import { Button } from "react-bootstrap";
import UserService from "../services/UserService";
import { useState } from 'react';
import { useHistory } from "react-router";
const SignUp = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [secondPassword, setSecondPassword] = useState('');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [login, setLogin] = useState('');
    const hist = useHistory();


    const addNewUser = () => {
        if (password === secondPassword) {
            const newUser = {
                email,
                password,
                firstName,
                lastName,
                login
            }
            console.log(newUser);
            addUserInDataBase(newUser);

        } else {
            alert("пароли не совпадают")
        }


        async function addUserInDataBase(user) {

            //await UserService.addUser(user);
            const requestJson = JSON.stringify(user);
            fetch("http://localhost:8080/mainServlet?command=SIGN_UP", {
                mode: 'cors',
                method: "POST",
                body: requestJson
            }).then(function (res) {
                if (res.ok) {
                    alert("ok")
                    hist.push("/login")
                } else if (res.status !== 200) {
                    alert("Oops! ");
                }
            }, function (e) {
                alert("Error submitting form!");
            });
        }
    }
    return (
        <form className="formSignUp">
            <h2 className="formLogo">LOGOTYPE</h2>
            <h3 className="form_title">Sign Up</h3>

            <div class="formInner">
                <input class="text input--active" id="login" placeholder="Login"
                    value={login}
                    onChange={e => setLogin(e.target.value)}
                />
                <input class="text input--active" type="email" id="email" placeholder="E-mail"
                    value={email}
                    onChange={e => setEmail(e.target.value)} />
                <input class="text input--active" type="password" id="password" placeholder="Password"
                    value={password}
                    onChange={e => setPassword(e.target.value)} />
                <input class="text input--active" type="password" id="confirm_password" placeholder="Confirm password"
                    value={secondPassword}
                    onChange={e => setSecondPassword(e.target.value)} />
                <input class="text input--active" id="First Name" placeholder="First Name"
                    value={firstName}
                    onChange={e => setFirstName(e.target.value)} />
                <input class="text input--active" id="Last Name" placeholder="Last Name"
                    value={lastName}
                    onChange={e => setLastName(e.target.value)} />

            </div>
            <Button onClick={addNewUser} className="formBtn">Sign Up</Button>


        </form>
    );
};
export default SignUp;