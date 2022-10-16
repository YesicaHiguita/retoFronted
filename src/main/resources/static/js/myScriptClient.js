$("document").ready(function (){
    getCategories();
});



function getCategories(){
    $.ajax({
        url : "/api/Category/all",
        type : 'GET',
        dataType : 'json',
        success : function(p) {
            $("#resultado").empty();
            for(i=0;i<p.length;i++){

                $("#resultado").append(p[i].name+" "+p[i].description+" ");

                $("#resultado").append(" <button onclick='getCategoryById("+p[i].id+")'>Editar</button>");
                $("#resultado").append(" <button onclick='deleteCategory("+p[i].id+")'>Borrar!</button>");
                $("#resultado").append("<br>");

            }
        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        },
        complete : function(xhr, status) {
            //  alert('Petición realizada');
        }
    });
}
function cleanData(){
    $("#nameCategory").val("");
    $("#descriptionCategory").val("");
}

function getDataCategory(){
    let data={
        id:  $("#idCategory").val(),
        name: $("#nameCategory").val(),
        description: $("#descriptionCategory").val(),
    }
    return data;
}
function saveCategory(){
    let myData=getDataCategory();
    myData.id=null;
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url : "/api/Category/save",
        data : dataToSend,
        type : 'POST',
        dataType : 'json',
        contentType:'application/json',
        success : function(dg) {
            console.log(dg);
            cleanData();
            getCategories();
        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        },
        complete : function(xhr, status) {
            //  alert('Petición realizada');
        }
    });
}
function getCategoryById(idCategory){

    $.ajax({
        url : "/api/Category/"+idCategory,
        type : 'GET',
        dataType : 'json',
        success : function(item) {
            $("#idCategory").val(item.id);
            $("#descriptionCategory").val(item.description);
            $("#nameCategory").val(item.name);

        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        },
        complete : function(xhr, status) {
            //  alert('Petición realizada');

        }
    });
}

function updateCategory() {
    let myData = {
        name: $("#nameCategory").val(),
        description: $("#descriptionCategory").val(),
    };

    let dataToSend = JSON.stringify(myData);

    $.ajax({
        url: "/api/Category/save",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        dataType: "JSON",
        success: function (respuesta) {
            $("#resultado").empty();
            $("#nameCategory").val("");
            $("#descriptionCategory").val("");
            getCategories();
            alert("Se ha actualizado");
        },
    });
}

function deleteCategory(idCategory){
    let data={
        id:idCategory
    };
    let dataToSend=JSON.stringify(data);
    console.log(data.id)
    $.ajax({
        url : "http://127.0.0.1:8099/api/Category/"+data.id,
        type : 'DELETE',
        dataType : 'json',
        contentType:'application/json',
        success : function(borrar) {
            cleanData();
        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        },
        complete: function(){
            getCategories();
        }
    });

}

function updateCategory(){
    let myData=getDataCategory();
    let dataToSend=JSON.stringify(myData);

    $.ajax({
        url : "http://127.0.0.1:8099/api/Category/update",
        data : dataToSend,
        type : 'PUT',
//	    dataType : 'json',
        contentType:'application/json',
        success : function(dg) {
            cleanData();
            getCategories();

        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        },
        complete : function(xhr, status) {
            //  alert('Petición realizada');
        }
    });
}