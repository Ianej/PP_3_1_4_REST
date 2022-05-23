alert('Z')
async function button(message) {
    //alert('Z1');
    //alert('Z2');
    //alert('Z3');
    /*let response = await fetch(urlAdmin + 'usersList')
    let j = await response.json();
    for (let r of j) {
        alert(r.id + r.name);
    }
    await (response.headers.get('Content-Type')) //просмотр заголовка
    for (let [key, value] of response.headers) {
        alert(`${key} = ${value}`);
    }*/
    //ручной запрос
    /*let user = {
        name: 'Alesha',
        lastName: 'Bondarev',
        age: 1,
        email: 'z@z.ru',
        password:'',
        roles: 2,

    };
    let response = await fetch(urlAdmin + 'newUser', {
        method: 'post',
        headers: {'Content-Type': 'application/json;charset=utf-8'},
        body: JSON.stringify(user)
    });
    let result = await response.json();
    alert(result);
    alert(result.message);
    for (let r of Object.keys(result) ) {
        alert(r + result[r]);
    }*/
    //отправка формы
    /*let response = await fetch(urlAdmin + 'newUser', {
        method: 'post',
        body: new FormData(newUserForm)
    });
    let result = await response.json();
    alert(result);
    alert(result.message);
    for (let r of Object.keys(result) ) {
        alert(r + result[r]);
    }*/
    //нажатие кнопки к форме
    //let tbody = document.createElement('tbody');
    //document.getElementById('usersListTable').innerHTML = tbody;//appendChild(tbody);
    //document.getElementById('tbody').innerHTML = document.getElementById('tbody').appendChild(tbody);//appendChild(tbody);

    /*//создание строки
    let tbody = document.getElementById('tbody');
    let tr = tbody.insertRow(-1);
    //let tr = document.createElement('tr');
    tr.setAttribute('id', 100);
    for (let i = 0; i < 8; i++) {
        let td = document.createElement('td');
        td.setAttribute('id', `${i}`);
        td.textContent = "script";
        tr.appendChild(td);
    }
    tbody.appendChild(tr);
    alert('del');
    //удаление строки
    tr.parentElement.removeChild(tr);*/

    /*//переключение вкладки
    let pane =document.getElementById('newUserTab');
    //pane.setAttribute('class', 'tab-pane active');
    //pane.classList.add('active');
    //pane.tab('show');
    pane.click();*/

}