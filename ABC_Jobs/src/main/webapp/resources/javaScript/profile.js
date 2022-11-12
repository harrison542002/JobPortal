let form = "";
let firstName = "";
let lastName = "";
let city = "";
let country = "";
let firstNameError;
let lastNameError;
let countryError;
let cityError;
let fileError;
let namePair;


//Start For Education Validate

let eduForm = "";
let school = "";
let qualification = "";
let schoolError = "";
let qualificationError = "";
let eduPair = {
	0: "school",
	1: "qualification"
}

//Start For Experience Validation

let expForm = "";
let excountry = "";
let company = "";
let start_date = "";
let end_date = "";
let position = "";
let excountryError = "";
let companyError = "";
let start_dataError = "";
let end_dateError = "";
let positionError = "";
let expPair = {
	0: "excountry",
	1: "company",
	2: "start_data",
	3: "end_date",
	4: "position"
}

window.onload = function() {


	form = document.getElementById("editForm");
	firstName = form.firstName;
	lastName = form.lastName;
	city = form.city;
	country = form.country;
	firstNameError = '';
	lastNameError = '';
	countryError = '';
	cityError = '';
	fileError = '';

	namePair = {
		0: "firstName",
		1: "lastName",
		2: "country",
		3: "city",
		4: "file"
	}


	//Start For Education Validate
	eduForm = document.getElementById("eduForm");
	school = eduForm.school;
	qualification = eduForm.qualification;
	schoolError = "";
	qualificationError = "";


	//Start For Experience Validation
	expForm = document.getElementById("expForm");
	excountry = expForm.country;
	company = expForm.company;
	start_date = expForm.start_data;
	end_date = expForm.end_date;
	position = expForm.position;
	excountryError = "";
	companyError = "";
	start_dataError = "";
	end_dateError = "";
	positionError = "";
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

//country
function countryValidator(value) {
	if (value === '' || value === null)
		countryError = 'Please select your country';
	else
		countryError = "";
}

//city
function cityValidator(value) {
	if (value === '' || value === null)
		cityError = 'Please fill in current job position';
	else
		cityError = '';
}

//check for error
function checkForDisplayError(errorArray, hashName) {

	errorArray.forEach((element, index) => {
		let elementName = hashName[index];
		if (element.length > 0) {
			let errorHtml = `<div class="alert alert-danger mt-2" role="alert" id = "${elementName}Error">
                ${element} </div>`;

			//Check if the error message is already appeared
			let error = document.getElementById(`${elementName}Error`);
			if (error) {
				error.remove();
			}

			//Append new error message
			document.getElementById(elementName).insertAdjacentHTML("afterend", errorHtml);
		}
		else {
			//check if the information is correct, then remove the error message
			let error = document.getElementById(`${elementName}Error`);
			if (error) {
				error.remove();
			}
		}
	})
}

function validation() {
	firstNameValidator(firstName.value);
	lastNameValidator(lastName.value);
	countryValidator(country.value);
	cityValidator(city.value);

	const errorArray = [firstNameError, lastNameError, countryError, cityError, fileError];
	if (firstNameError.length > 0 || lastNameError.length > 0 ||
		countryError.length > 0 || cityError.length > 0 || fileError.length > 0) {
		checkForDisplayError(errorArray, namePair);
		return false;
	}
	return true;
}

function preview() {
	frame.src = URL.createObjectURL(event.target.files[0]);
	const file = event.target.files[0];
	if (Number(file.size) > 60000) {
		fileError = "Please input image less than 50KB";
	} else {
		fileError = "";
	}
}



//school
function schoolValidator(value) {
	if (value === '' || value === null)
		schoolError = 'Please enter your school';
	else
		schoolError = "";
}

//qualification
function qualificationValidator(value) {
	if (value === '' || value === null)
		qualificationError = 'Please enter the qualification';
	else
		qualificationError = "";
}

function eduValidation() {
	schoolValidator(school.value)
	qualificationValidator(qualification.value)

	const errorArray = [schoolError, qualificationError];
	if (schoolError.length > 0 || qualificationError.length > 0) {
		checkForDisplayError(errorArray, eduPair);
		return false;
	}
	return true;
}
//End For Education Validation

//country
function countryExValidator(value) {
	if (value === '' || value === null)
		excountryError = 'Please enter country had been worked';
	else
		excountryError = "";
}

//company
function companyValidator(value) {
	if (value === '' || value === null)
		companyError = 'Please enter the company had been worked';
	else
		companyError = "";
}

//start_date
function start_dateValidator(value) {
	if (value === '' || value === null)
		start_dataError = 'Please enter the start date of occupation';
	else
		start_dataError = "";
}

//end_date
function endDateValidator(value) {
	if (value === '' || value === null)
		end_dateError = 'Please enter the end date of occupation';
	else
		end_dateError = "";
}

//position
function positionValidator(value) {
	if (value === '' || value === null)
		positionError = 'Please enter the position of experience';
	else
		positionError = "";
}


function expValidation() {
	companyValidator(company.value);
	countryExValidator(excountry.value);
	start_dateValidator(start_data.value);
	endDateValidator(end_date.value);
	positionValidator(position.value);

	const errorArray = [excountryError, companyError, start_dataError, end_dateError, positionError];
	if (countryError.length > 0 || companyError.length > 0 || start_dataError.length > 0 ||
		end_dateError.length > 0 || positionError.length > 0) {
		checkForDisplayError(errorArray, expPair);
		return false;
	}
	return true;
}
//End For Education




