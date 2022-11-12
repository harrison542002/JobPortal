$(document).ready(function() {

	const icons = document.querySelectorAll('.saved');


	icons.forEach(icon => {
		icon.addEventListener("hover", function tooltip(event) {
			icon.tooltip("toggle");
		})
	})

	let url = "/ABC_Jobs/deleteJob";

	$(".saved").on("click", function(event) {

		let job_id = event.currentTarget.dataset.jid;
		let user_id = event.currentTarget.dataset.uid;

		var answer = confirm("Are u sure to delete this job?")
		if (answer) {
			$.post(url,
				{
					"uid": user_id,
					"jid": job_id
				},
				function(response) {
					window.location.href = "/ABC_Jobs/myJobs";
					console.log(response);
					alert("job has been deleted");
				}
			)
		}
	})
})