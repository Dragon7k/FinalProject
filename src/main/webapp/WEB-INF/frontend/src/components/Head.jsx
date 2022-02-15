import React from 'react';
import '../styles/header.css'

import Select from './Select';
const Head = () => {

    return (
        <div className="title">
            <a className="logo">LOGOTYPE</a>

            <ul className="ul">
                <li className="li"><a href="/fields">Fields</a></li>
                <li className="li"><a href="/responses">Responses</a></li>
                <Select/>
            </ul>
        </div>
    );
};

export default Head;