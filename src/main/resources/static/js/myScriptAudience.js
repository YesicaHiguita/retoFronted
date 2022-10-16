$("document").ready(function (){
    getAudiences();
    getCategories();
});

function getCategories(){
    $.ajax({
        url : "api/Category/all",
        type : 'GET',
        dataType : 'json',
        success : function(p) {
            $("#categoryAudience").empty();
            for(i=0;i<p.length;i++){
                let k="<option value='"+p[i].id+"'>"+p[i].name+"</option>";
                $("#categoryAudience").append(k);
                //$("#categoryAudience").append($("<option>", {
                    //value: p[i].id,
                    //text: p[i].name



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

function getAudiences(){
    $.ajax({
        url : "api/Audience/all",
        type : 'GET',
        dataType : 'json',
        success : function(p) {
            $("#resultado").empty();
            for(i=0;i<p.length;i++){

                $("#resultado").append(p[i].name+" "+p[i].description+" "+p[i].owner+" "+p[i].capacity+" ");
                if(p[i].category!=null){
                    $("#resultado").append(p[i].category.name+" ");
                }

                $("#resultado").append(" <button onclick='getAudienceById("+p[i].id+")'>Editar</button>");
                $("#resultado").append(" <button onclick='deleteAudience("+p[i].id+")'>Borrar!</button>");
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
    $("#idAudience").val("");
    $("#nameAudience").val("");
    $("#capacityAudience").val("");
    $("#descriptionAudience").val("");
    $("#ownerAudience").val("");
}

function getDataAudience(){
    let data={
        id:  $("#idAudience").val(),
        name: $("#nameAudience").val(),
        capacity: $("#capacityAudience").val(),
        description:$("#descriptionAudience").val(),
        owner:$("#ownerAudience").val(),
        category:{ id:$("#categoryAudience option:selected").val()}
    }
    console.log(data)
    return data;
}
function saveAudience(){
    let myData=getDataAudience();
    myData.id=null;
    let dataToSend=JSON.stringify(myData);
    $.ajax({
        url : "api/Audience/save",
        data : dataToSend,
        type : 'POST',
        dataType : 'json',
        contentType:'application/json',
        success : function(dg) {
            console.log(dg);
            cleanData();
            getAudiences();
        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        },
        complete : function(xhr, status) {
            //  alert('Petición realizada');
        }
    });
}
function getAudienceById(idAudience){

    $.ajax({
        url : "api/Audience/"+idAudience,
        type : 'GET',
        dataType : 'json',
        success : function(item) {
            console.log(item)
            $("#idAudience").val(item.id);
            $("#nameAudience").val(item.name);
            $("#capacityAudience").val(item.capacity);
            $("#ownerAudience").val(item.owner);
            $("#descriptionAudience").val(item.description);
            $("#categoryAudience").val(item.category.id).change();

        },
        error : function(xhr, status) {

            alert('ha sucedido un problema');
        },
        complete : function(xhr, status) {
            //  alert('Petición realizada');

        }
    });
}

function deleteAudience(idAudience){
    let data={
        id:idAudience
    };
    let dataToSend=JSON.stringify(data);

    $.ajax({
        url : "api/Audience/"+data.id,
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
            getAudiences();
        }
    });

}

function updateAudience(){
    let myData=getDataAudience();
    let dataToSend=JSON.stringify(myData);

    $.ajax({
        url : "api/Audience/update",
        data : dataToSend,
        type : 'PUT',
//	    dataType : 'json',
        contentType:'application/json',
        success : function(dg) {
            console.log(dg)
            cleanData();
            getAudiences();

        },
        error : function(xhr, status) {
            alert('ha sucedido un problema');
        },
        complete : function(xhr, status) {
            //  alert('Petición realizada');
        }
    });
}
