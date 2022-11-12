const form = document.getElementById("proForm");
const firstName = form.firstName;
const lastName = form.lastName;
const company = form.company;
const city = form.city;
const country = form.country;
const occupation = form.occupation;
const file = form.file;
let firstNameError = '';
let lastNameError = '';
let companyError = '';
let occupationError = '';
let countryError = '';
let cityError = '';
let fileError = '';

let namePair = {
    0 : "firstName",
    1 : "lastName",
    2 : "country",
    3 : "city",
    4 : "company",
    5 : "file"
}

//firstName validation
function firstNameValidator(value) {
    if (value === '' || value === null)
        firstNameError = 'First Name is required';
    else
        firstNameError = '';
}

//lastName validation
function lastNameValidator(value) {
    if (value === '' || value === null)
        lastNameError = 'Last Name is required';
    else
        lastNameError = '';
}

//company
function companyValidator(value){
	if (value === '' || value === null)
        companyError = 'Please fill in company name';
    else
        companyError = '';
}

//country
function countryValidator(value){
	if (value === '' || value === null)
        countryError = 'Please select your country';
    else
        countryError = "";
}

//city
function cityValidator(value){
	if (value === '' || value === null)
        cityError = 'Please fill in current job position';
    else
        cityError = '';
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

function preview() {
    frame.src=URL.createObjectURL(event.target.files[0]);
}

function checksize(size){
        if(Number(size) > 60000){
           fileError = "Please input image less than 50KB";
        } else{
           fileError = "";
        }
}

function validation() {
        firstNameValidator(firstName.value);
        lastNameValidator(lastName.value);
        countryValidator(country.value);
        cityValidator(city.value);
        companyValidator(company.value);
        
        if(!file.files[0]){
			fileError = "Please Set Your Profile Image";
			
		} else{
			checksize(file.files[0].size);
		}
        

        const errorArray = [ firstNameError, lastNameError, countryError,cityError, companyError, fileError];
        if (firstNameError.length > 0 || lastNameError.length > 0 || 
            countryError.length > 0 || cityError.length > 0 || companyError.length > 0 ||
            fileError.length > 0
            ) {
            checkForDisplayError(errorArray);
            return false;
        }
        
		return true;
}



