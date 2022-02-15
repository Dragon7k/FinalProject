import React from 'react';

import { Table } from 'react-bootstrap';

import '../styles/FieldList.css'

const UserList = ({ posts }) => {

    return (
        <div>

            <Table striped bordered hover size="sm"  >
                <thead>
                    <tr>
                        <th className="th">First name</th>
                        <th className="th">Last name</th>
                        <th className="th">Email</th>
                        <th className="th">login</th>
                        <th className="th">Role</th>
                        <th className="th">Status</th>
                        <th className="th">Archived</th>
                    </tr>
                </thead>
                <tbody>
                {
                        posts.map((post) =>
                            <tr key={post.id}>
                                <td id="td">{post.firstName}</td>
                                <td id="td">{post.lastName}</td>
                                <td id="td">{post.email}</td>
                                <td id="td">{post.login}</td>
                                <td id="td">{post.role}</td>
                                <td id="td">{post.userStatus?"BLOCKED":"UNBLOCKED"}</td>
                                <td id="td">{post.archived?"ARCHIVED":"NOT ARCHIVED"}</td>
                            </tr>
                        )}
                </tbody>
            </Table>
        </div>
    );
};

export default UserList;