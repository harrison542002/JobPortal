window.onload = function() {
    $('#successMsg').modal("show");
    $('#errorMsg').modal("show");
}

function deleteTrigger(p_id, l_id, u_id){

    var answer = confirm("Are u sure to delete this user?")
	if (answer){
		alert("User Has Been Deleted")
		window.location.href =  "/ABC_Jobs/deleteUser?" + "u_id=" + u_id +
        "&p_id=" + p_id + "&l_id=" + l_id;
	}
}