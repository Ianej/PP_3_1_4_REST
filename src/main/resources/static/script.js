let urlAdmin = 'http://localhost:8080/admin/';

document.onreadystatechange = () => {
    //alert('document ready');
    usersList();
};

async function usersList() {
    //alert('usersList()');
    let response = await fetch(urlAdmin + 'usersList')
    let resultUsersList = await response.json();

    let tbody = document.getElementById('tbody');
    tbody.parentElement.removeChild(tbody);
    tbody = document.createElement('tbody');
    tbody.setAttribute('id', 'tbody');
    document.getElementById('usersListTable').appendChild(tbody);
    for (let result of resultUsersList) {
        createTableRow(result);
    }
}


async function btnEdit(id) {
    //alert('btnEdit' + id);
    await fillModal('Edit', id);
}

async function btnDelete(id) {
    //alert('btnDelete' + id);
    await fillModal('Delete', id);
}

modalEdit.onsubmit = async (e) => {
    e.preventDefault();
    let response = await fetch(urlAdmin + 'edit?' + document.getElementById('idEdit').value, {
        method: 'put',
        body: new FormData(modalEdit)
    });
    let result = await response.json();

    let oldRow = document.getElementById('' + result.id);
    let newRow = document.createElement('tr');
    newRow.innerHTML = getNewTableRow(result);
    oldRow.parentNode.insertBefore(newRow, oldRow);
    oldRow.parentNode.removeChild(oldRow);
    newRow.setAttribute('id', result.id);

    document.getElementById('closeEdit').click();
}

modalDelete.onsubmit = async (e) => {
    e.preventDefault();
    let response = await fetch(urlAdmin + 'delete?id=' + document.getElementById('idDelete').value, {
        method: 'delete',
    });
    let result = await response.json();
    let deleteRow = document.getElementById('' + result.id);
    deleteRow.parentElement.removeChild(deleteRow);

    document.getElementById('closeDelete').click();
}

newUserForm.onsubmit = async (e) => {
    e.preventDefault();
    let response = await fetch(urlAdmin + 'newUser', {
        method: 'post',
        body: new FormData(newUserForm)
    });
    let result = await response.json();
    createTableRow(result);

    let pane = document.getElementById('usersListTab');
    pane.click();
};

async function fillModal(type, id) {
    let response = await fetch(urlAdmin + 'user?id=' + id);
    let resultUsersEdit = await response.json();
    document.getElementById('id' + type).value = resultUsersEdit.id;
    document.getElementById('name' + type).value = resultUsersEdit.name;
    document.getElementById('lastName' + type).value = resultUsersEdit.lastName;
    document.getElementById('age' + type).value = resultUsersEdit.age;
    document.getElementById('email' + type).value = resultUsersEdit.email;
    if(type === 'Edit') {
        document.getElementById('password' + type).value = resultUsersEdit.password;
    }
    for (let r in resultUsersEdit.roles) {
        document.getElementById(`roles${type}${resultUsersEdit.roles[r].id}`).selected = true;
    }
}

function createTableRow(result) {
    let tbody = document.getElementById('tbody');
    let tr = tbody.insertRow(-1);
    tr.setAttribute('id', result.id);
    tr.innerHTML = getNewTableRow(result);
    tbody.appendChild(tr);
}

function getNewTableRow(result) {
    let roles ='';
    for (let r in result.roles) {
        roles += result.roles[r].roleName + ' ';
    }
    let line = `<td>${result.id}</td>
                <td>${result.name}</td>
                <td>${result.lastName}</td>
                <td>${result.age}</td>
                <td>${result.email}</td>
                <td>${roles}</td>
                <td>
                    <a href="#" onClick="btnEdit(${result.id});" class="btn btn-info" data-toggle="modal" data-target="#Edit">Edit</a>
                </td>
                <td>
                    <a href="#" onClick="btnDelete(${result.id});" class="btn btn-danger" data-toggle="modal" data-target="#Delete">Delete</a>
                </td>
    `;
    return line;
}
