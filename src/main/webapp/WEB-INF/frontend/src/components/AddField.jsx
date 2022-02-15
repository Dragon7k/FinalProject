
import React, { useState } from "react";
import { Modal, Button } from 'react-bootstrap'
import '../styles/AddField.css';
import MySelect from './MySelect'
import FieldService from "../services/FieldService";

function AddField(props) {

  const [label, setlabel] = useState('');
  const [type, setType] = useState('');
  const [option, setOption] = useState('');
  var [required, setRequired] = useState(false);
  const [active, setActive] = useState(false);

  const addNewField = (e) => {
    const newField = {
      label,
      type,
      option,
      required,
      active
    }
    console.log(newField);
    addFieldInDataBase(newField);

  }
  async function addFieldInDataBase(newField) {
    await FieldService.addField(newField);
    window.location.reload();

  }

  
  return (
    <Modal {...props} className="modal"  >
      <Modal.Header >
        <Modal.Title id="contained-modal-title-vcenter">
          Add Field
        </Modal.Title>
      </Modal.Header>
      <Modal.Body className="addFieldContent">

        <div className="addField1">
          <a classname="">Label</a>
          <input class="text input--active" id="label"
            value={label}
            onChange={e => setlabel(e.target.value)} />
        </div>
        <div className="addField1">
          <a classname="">Type</a>
          <MySelect
            value={type}
            onChange={type => setType(type)}
            defaultValue=""
            options={[
              { value: 'COMBOBOX', name: 'Combobox' },
              { value: 'SINGLE_LINE_TEXT', name: 'Single line text' },
              { value: 'RADIO_BUTTON', name: 'Radio button' },
              { value: 'CHECKBOX', name: 'Checkbox' },
              { value: 'DATE', name: 'Date' },
            ]}
          >
          </MySelect>
        </div>

        <div className="addField">
          <a classname="">Option</a>
          <textarea class="text input--active"
            value={option}
            onChange={e => setOption(e.target.value)} />
        </div>
        <div className="checkboxes">
          <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" onChange={() => setRequired(!required)} />
            <label class="form-check-label" for="flexCheckChecked">
              Required
            </label>
          </div>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked" onChange={() => setActive(!active)} />
            <label class="form-check-label" for="flexCheckChecked">
              is Active
            </label>
          </div>
        </div>

      </Modal.Body>
      <Modal.Footer>

        <div className="modalFooter">
          <Button className="footerBtn" onClick={props.onHide}>cancel</Button>
          <Button className="footerBtn1" onClick={addNewField}>Save</Button>
        </div>
      </Modal.Footer>
    </Modal>
  );
}
export default AddField;
