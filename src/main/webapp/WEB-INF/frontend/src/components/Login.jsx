import React, { useState, useEffect } from "react";
import '../styles/Login.css';
import { Button } from "react-bootstrap";
import UserService from "../services/UserService";
import { useHistory } from "react-router";
const Login = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [remember, setRemember] = useState(false);
    const history = useHistory();


    const checkUser = (e) => {
        getUser();
    }

    function handleClick() {
        history.push('/fields');
        window.location.reload();
    }


    async function getUser() {
        await UserService.getUserByEmail(email).then(res => {
            password === res.data.password ? allOk() : alert('wrong login or password')
            localStorage.setItem('id', res.data.id);
            localStorage.setItem('name', res.data.firstName);
        }).catch(
            alert("user doesnt exist")
        );

    }

    const allOk = (e) => {
        if (remember === true) {
            rememberUser();
        }
        alert('allOK');
        handleClick();

    }

    useEffect(() => {
        if (localStorage.length > 0) {
            setEmail(localStorage.getItem('email'));
            setPassword(localStorage.getItem('password'));
        }
    });

    const rememberUser = (e) => {
        localStorage.setItem('email', email);
        localStorage.setItem('password', password);
    }

    return (

        <form className="formLogin">
            <h2 className="formLogo">LOGOTYPE</h2>
            <h3 className="form_title">Log in</h3>

            <div class="formInner">
                <input class="text input--active" type="email" id="email" placeholder="E-mail"
                    value={email}
                    onChange={e => setEmail(e.target.value)} />

                <input class="text input--active" type="password" id="password" placeholder="Password"
                    value={password}
                    onChange={e => setPassword(e.target.value)} />
            </div>

            <div className="formLinks">

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" onClick={() => setRemember(!remember)} />
                    <label class="form-check-label" for="flexCheckChecked">
                        Remember me
                    </label>
                </div>

                <a className="formLink" href="/">Forgot your Password</a>
            </div>

            <Button onClick={checkUser} className="formBtn" >Log in</Button>

            <div className="formLinks2">
                <span className="formSpan">Dont't have an account</span>
                <a className="formLink1" href="/signUp">Sign up</a>
            </div>

        </form>

    );
};
export default Login;