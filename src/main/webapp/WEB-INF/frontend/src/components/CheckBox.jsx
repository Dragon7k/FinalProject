import React from 'react';
const CheckBox = (value) => {

    return (
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" />
            <label class ="form-check-label" for="flexCheckChecked">
            {value}
            </label>
        </div>
    );
};

export default CheckBox;