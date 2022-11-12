const form = document.getElementById("rForm");
const password = form.password;
const confirmed = form.confirmed;
let passwordError;
let confirmedError;

let namePair = {
    0 : "password",
    1 : "confirmed"
}

//password validation
function passwordValidator(value) {
    if (value === '' || value === null)//Required
        passwordError = "Please Enter Password";
    
    else if(value.length < 8){
		passwordError = "Please Enter At least 8 Characters";
	}
    else
        passwordError = '';
}

//confirmation validation
function confirmedPassword(enteredPwd, value){
	
	if(value == enteredPwd){
		confirmedError = "Password does not match";
	} 
	
	else 
		confirmedError = '';
	
}

//check for error
function checkForDisplayError(errorArray) {

    errorArray.forEach((element, index) => {
        let elementName = namePair[index];
        if (element.length > 0) {
            let errorHtml = `<div class="alert alert-danger mt-2" role="alert" id = "${elementName}Error"> 
                ${element} </div>`;
            
            //Check if the error message is already appeared
            let error = document.getElementById(`${elementName}Error`);
            if(error){
                error.remove();
            }

            //Append new error message
            document.getElementById(elementName).insertAdjacentHTML("afterend", errorHtml);
        }
        else{
            //check if the information is correct, then remove the error message
            let error = document.getElementById(`${elementName}Error`);
            if(error){
                error.remove();
            }
        }
    })
}

function validation() {

        passwordValidator(password.value);
        confirmedPassword(password.value, confirmed.value);

        const errorArray = [password, confirmed];
        if (confirmed.length > 0 || confirmed.length > 0 ) {
            checkForDisplayError(errorArray);
            return false;
        }
        
		return true;
}