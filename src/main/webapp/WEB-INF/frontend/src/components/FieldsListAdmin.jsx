import React, { useState } from 'react';
import Game from './Game';
import { Table } from 'react-bootstrap';
import { Button } from 'react-bootstrap';
import '../styles/FieldList.css'
import AddField from './AddField';
import FieldService from '../services/FieldService';
import EditField from './EditField';

const FieldListAdmin = ({ posts }) => {

    const [modalShow, setModalShow] = useState(false);
    const [modalEditShow, setModalEditShow] = useState(false);
    const [editId, setEditId] = useState();

    
    function deleteField(id){
        console.log('delete button')
        deleteFieldFromDataBase(id);
       
    }
    function editField(id){
        console.log('edit button')
        setEditId(id)
        setModalEditShow(true);
       
    }

    async function deleteFieldFromDataBase(id) {
        await FieldService.deleteField(id);
        window.location.reload();
       
    }


    return (
        <div>
            <div className="tablehead">
                <a>Fields</a>
                <Button class="table__btn btn btn-primary" onClick={() => setModalShow(true)} >Add field</Button>
            </div>
            <AddField
                show={modalShow}
                onHide={() => setModalShow(false)}
                editId={editId}>
            </AddField>
            <EditField
                show={modalEditShow}
                onHide={() => setModalEditShow(false)}
                editId={editId}>
            </EditField>
            

            <Table striped bordered hover size="sm" >
                <thead>
                    <tr>
                        <th className="th">Label</th>
                        <th className="th">Type</th>
                        <th className="th">Required</th>
                        <th className="th">isActive</th>
                        <th className="th">Edit</th>
                        <th className="th">Delete</th>

                    </tr>
                </thead>
                <tbody>
                    {
                        posts.map((post) =>
                            <tr key={post.id}>
                                <td id="td">{post.label}</td>
                                <td id="td">{post.type}</td>
                                <td id="td">{post.required ? 'true' : 'false'}</td>
                                <td id="td">{post.active ? 'true' : 'false'}</td>
                                <td id="td">
                                    <button onClick={() => editField(post.id)}>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-clipboard-plus" viewBox="0 0 16 16">
                                            <path fill-rule="evenodd" d="M8 7a.5.5 0 0 1 .5.5V9H10a.5.5 0 0 1 0 1H8.5v1.5a.5.5 0 0 1-1 0V10H6a.5.5 0 0 1 0-1h1.5V7.5A.5.5 0 0 1 8 7z" />
                                            <path d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1v-1z" />
                                            <path d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5h3zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0h-3z"/>
                                        </svg>
                                    </button>
                                </td>
                                <td id="td">
                                    <button onClick={() => deleteField(post.id)}>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                            <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                                            <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                                        </svg>
                                    </button>

                                </td>
                            </tr>
                        )}
                </tbody>
            </Table>
        </div>
    );
};

export default FieldListAdmin;