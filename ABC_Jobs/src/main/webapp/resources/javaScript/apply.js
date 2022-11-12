$(document).ready(function() {


	let url = "/ABC_Jobs/apply";

	$(".apply").on("click", function(event) {

		let job_id = event.currentTarget.dataset.jid;
		let user_id = event.currentTarget.dataset.uid;

		var answer = confirm("Are u sure to u want to apply this job?")
		if (answer) {
			$.post(url,
				{
					"uid": user_id,
					"jid": job_id
				},
				function(response) {
					console.log(response);
					if(response == "success"){
						alert("Job has been applied successfully");
					}
					
					if(response == "already exist"){
						alert("Job has been already applied, please wait for response from company");
					}
				}
			)
		}
	})

})