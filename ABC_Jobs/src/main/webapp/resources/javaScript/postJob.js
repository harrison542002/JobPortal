$(document).ready(function() {
	$('#successMsg').modal("show");
})

const form = document.getElementById("postForm");
const job_title = form.job_title;
const requirements = form.requirements;
const salary = form.salary;
const benefits = form.benefits;
const company_Information = form.company_Information;
const company_name = form.company_name;
const contact_info = form.contact_info;
const additional_information = form.additional_information;
let job_titleError = '';
let requirementsError = '';
let salaryError = '';
let benefitsError = '';
let company_InformationError = '';
let company_nameError = '';
let contact_infoError = '';
const namePair = {
	0: "job_title",
	1: "requirements",
	2: "salary",
	3: "benefits",
	4: "company_Information",
	5: "company_name",
	6: "contact_info"
}

function titleValidator(val) {
	if (val.length <= 0 || val == null) {
		job_titleError = "Please Enter Job Title";
	} else {
		job_titleError = "";
	}
}

function reqValidator(val) {
	if (val.length <= 0 || val == null) {
		requirementsError = "Please Enter Job Requirements";
	} else {
		requirementsError = "";
	}
}

function salaryValidator(val) {
	if (val.length <= 0 || val == null) {
		requirementsError = "Please Enter Job Requirements";
	} else {
		requirementsError = "";
	}
}

function beniValidator(val) {
	if (val.length <= 0 || val == null) {
		benefitsError = "Please Enter Job Benefits";
	} else {
		benefitsError = "";
	}
}

function companyInfoValidator(val) {
	if (val.length <= 0 || val == null) {
		company_InformationError = "Please Enter Company Info";
	} else {
		company_InformationError = "";
	}
}

function comNameValidator(val) {
	if (val.length <= 0 || val == null) {
		company_nameError = "Please Enter Company Name";
	} else {
		company_nameError = "";
	}
}

function contactInfoValidator(val) {
	if (val.length <= 0 || val == null) {
		contact_infoError = "Please Enter Contact Information";
	} else {
		contact_infoError = "";
	}
}

function validation() {
	titleValidator(job_title.value);
	reqValidator(requirements.value);
	salaryValidator(salary.value);
	beniValidator(benefits.value);
	companyInfoValidator(company_Information.value);
	comNameValidator(company_name.value);
	contactInfoValidator(contact_info.value);
	
	const errorArray = [job_titleError, requirementsError, salaryError, 
	benefitsError, company_InformationError, company_nameError,
		contact_infoError];
	let error = job_titleError + requirementsError + salaryError + 
	benefitsError + company_InformationError + company_nameError +
		contact_infoError;
	console.log(error);
	if (error.length > 0) {
		checkForDisplayError(errorArray);
		return false;
	}
	return true;
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