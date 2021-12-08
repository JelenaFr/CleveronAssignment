'use strict'


function createNewThirdLevelPermission(url) {
  level3.innerHTML='';
    $.ajax({
    type: "POST",
    url: url,
    cache: false,
    contentType: JSON,
    processData: false
  })
}

function createNewSecondLevelPermission(url) {
  level2.innerHTML='';
  $.ajax({
    type: "POST",
    url: url,
    cache: false,
    contentType: JSON,
    processData: false
  })

}

function findAllThirdLevelPermissions(url) {
  let level3 = document.getElementById("level3");

  $.ajax({
    type: "GET",
    url: url,
    contentType: JSON
  }).done(function (response) {
      level3.innerHTML = ''
      response.forEach(op => level3.innerHTML += `<li> ${op["permissionName"]["name"]}</li>

     <button class="btn" onclick="deleteThirdLevelPermission('http://localhost:8080/delete3/' + ${op["id"]} +'/')" > <i class="fa fa-trash"></i></button>`);
    }
  )
}

function findAllSecondLevelPermissions(url) {
  let level2 = document.getElementById("level2");

  $.ajax({
    type: "GET",
    url: url,
    contentType: JSON
  }).done(function (response) {
      level2.innerHTML = '';
      level3.innerHTML='';
      console.log(response)
      response.forEach(op => level2.innerHTML += `<li> ${op["permissionName"]["name"]}</li>
                        <button   class="btn" onclick="findAllThirdLevelPermissions('http://localhost:8080/level3/' + ${op["id"]} +'/')" ><i class="fa fa-folder"></i></button>
                        <button   class="btn" onclick="deleteThisLevelPermission('http://localhost:8080/delete2/' + ${op["id"]} +'/')" ><i class="fa fa-trash"></i></button>
                        <button   class="btn" onclick="createNewThirdLevelPermission('http://localhost:8080/new3/' + ${op["id"]} +'/')"><i class="fa fa-plus"></i></button>`);
    }
  )
}

function findAllFirstLevelPermissions(url) {
   let all = document.getElementById("all");
   all.innerHTML = '';
   level3.innerHTML='';
  $.ajax({
    type: "GET",
    url: url,
    data: JSON
  }).done(function (response) {
      response.sort().forEach(op => all.innerHTML += `<li><div>${op["permissionName"]["name"]}</li>
                        <button   class="btn" onclick="findAllSecondLevelPermissions('http://localhost:8080/level2/' + ${op["id"]} +'/')" ><i class="fa fa-folder"></i></button>
                        <button   class="btn" onclick="deleteThisLevelPermission('http://localhost:8080/delete1/' + ${op["id"]} +'/')" ><i class="fa fa-trash"></i></button>
                        <button   class="btn" onclick="createNewSecondLevelPermission('http://localhost:8080/new2/' + ${op["id"]} +'/')"><i class="fa fa-plus"></i></button>`
      )
    }
  )
}

function deleteThirdLevelPermission(url) {
  $.ajax({
    url: url,
    type: "DELETE",
    dataType: "JSON",
    success: alert("You remove last level permission!")
  })

  let level3 = document.getElementById("level3");
  level3.innerHTML = ''

  findAllFirstLevelPermissions("http://localhost:8080/start/");


}
function deleteThisLevelPermission(url) {
  $.ajax({
    url: url,
    type: "DELETE",
    dataType: "JSON",
    success: alert("You remove this and all child permissions if exist!")
  })
  history.go();
  findAllFirstLevelPermissions("http://localhost:8080/start/");

}

$.when($.ready).then(function () {
  const
    all = document.getElementById("all"),
    creteNewFirstLevelButton = document.getElementById("btn-first"),
    creteNewSecondLevelButton = document.getElementById("btn-second-level-create"),
    showSecondLevelsButton = document.getElementById("btn-second-level-folder"),
    level2 = document.getElementById("level2"),
    level3 = document.getElementById("level3"),
    firstPanel = document.getElementById('firstPanel'),
    secondPanel = document.getElementById('secondPanel');

  createNewFirstLevelPermission(creteNewFirstLevelButton, "http://localhost:8080/new1/");

  function createNewFirstLevelPermission(button, url) {
    findAllFirstLevelPermissions("http://localhost:8080/start/");
    button.addEventListener("click", () => {
      $.ajax({
        type: "POST",
        url: url,
        cache: false,
        contentType: false,
        processData: false,
        data: {id: 0}
      })

    })
  }

});
