window.onload = function(){
    $('#errorMsg').modal("show");
}

    const form = document.getElementById("regForm");
    const email = form.email;
    const password = form.password;
    let emailError = '';
    let passwordError = '';

    let namePair = {
        0 : "email",
        1 : "password"
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

    //password validation
    function passwordValidator(value) {
        if (value === '' || value === null)//Required
            passwordError = "Please Enter Password";

        else if (value.length < 8)//Short unsafety password
            passwordError = "Password should be at least 8 characters";

        else
            passwordError = '';
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
            passwordValidator(password.value);

            const errorArray = [emailError, passwordError];
            if (emailError.length > 0 || passwordError.length > 0 ) {
                checkForDisplayError(errorArray);
                return false;
            }
    		return true;
    }



