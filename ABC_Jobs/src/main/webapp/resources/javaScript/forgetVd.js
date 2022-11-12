const form = document.getElementById("forgetForm");
const email = form.email;
let emailError = '';

let namePair = {
    0 : "email",
}

//email regex
let regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

//email validation
function emailValidator(value) {
    if (value === '' || value === null)//Required
        emailError = "Please Enter Your Email.";

    else if (!regex.test(value))
        emailError = "Please Enter Valid Email";

    else
        emailError = '';
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

        emailValidator(email.value);
        const errorArray = [emailError];
        if (emailError.length > 0) {
            checkForDisplayError(errorArray);
            return false;
        }
        
		return true;
}


