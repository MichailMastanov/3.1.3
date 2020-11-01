

              // создание

    async function createNewUser(){
    const username = $('#nameUserInput').val()
    const password = $('#passwordCreate').val()
    let array = $('#disabledSelect').val()
    await   fetch("/admin/createNewUs", {
    method : 'POST',
    headers: {
    'Content-Type': 'application/json;charset=utf-8'
},
    body: JSON.stringify({
    name: username,
    passwordArr: password,
    rolesAll: array
})
})
    .then(response => response.json())
    .then(result => {
    $('#userAllList').empty()
    for(let user of result){
    let roles = user.authorities
    let roleAll = "";
    for (let role of roles){
    roleAll = roleAll + role.authority + " "
}
    $('#userAllList').append(`<tr id="infoUsers"></tr>
                    <td id="idUser">${user.id}</td>
                    <td id="idUsername">${user.username}</td>
                    <td id="idPassword">${user.password}</td>
                    <td id="idRole">${roleAll}</td>
                    <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" id="${user.id}" onclick="editData(this)">Edit</button></td>
                    <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal"  id="${user.id}" onclick="deleteData(this)">Delete</button></td>`)
}
})
}


      // удаление

    function deleteData(butClick){
        let id = butClick.id
        fetch(`/admin/deleteById/${id}`, {
            method : 'DELETE'
        })
            .then(response => response.json())
            .then(result => {
                $('#modalAll').empty()
                let roles = result.authorities
                let roleAll = "";
                for (let role of roles){
                    roleAll = roleAll + role.authority + " "
                }
                $('#modalAll').append(`
                <form>
                    <fieldset disabled>
                        <div class="form-group">
                            <label for="idDel">Id</label>
                            <input type="text" value="${result.id}" class="form-control" id="idDel" placeholder="id">
                        </div>
                        <div class="form-group">
                            <label for="userameDel">Username</label>
                            <input type="text" value="${result.username}" class="form-control" id="userameDel" placeholder="username">
                        </div>
                        <div class="form-group">
                            <label for="passwordDel">Password</label>
                            <input type="text" value="${result.password}" class="form-control" id="passwordDel" placeholder="password">
                        </div>
                        <div class="form-group">
                            <label for="roleDel">Role</label>
                            <input type="text" value="${roleAll}" class="form-control" id="roleDel" placeholder="role">
                        </div>
                    </fieldset>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal" id="${result.id}" onclick="deleteResult(this)">Delete</button>
                    </div>
                </form>
                `)
            })
    }


              function deleteResult(delClick){
                  let id = delClick.id
                  fetch(`/admin/deleteResult/${id}`, {
                      method : 'DELETE'
                  })
                      .then(response => response.json())
                      .then(result => {
                          $('#userAllList').empty()
                          for(let user of result){
                              let roles = user.authorities
                              let roleAll = "";
                              for (let role of roles){
                                  roleAll = roleAll + role.authority + " "
                              }
                              $('#userAllList').append(`<tr id="infoUsers"></tr>
                    <td id="idUser">${user.id}</td>
                    <td id="idUsername">${user.username}</td>
                    <td id="idPassword">${user.password}</td>
                    <td id="idRole">${roleAll}</td>
                    <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" id="${user.id}" onclick="editData(this)">Edit</button></td>
                    <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal"  id="${user.id}" onclick="deleteData(this)">Delete</button></td>`)
                          }
                      })
              }





              $(document).ready(function (){
                  $('#admin').on('click', function (){
                      $('#usersAll').css('display' , 'block');
                      $('#userOne').css('display' , 'none');
                  })
              })



                  $(document).ready(function (){
                  $('#user').on('click', function (){
                      $('#userOne').css('display' , 'block');
                      $('#usersAll').css('display' , 'none');
                  })
              })
