$(document).ready(function() {


	let url = "/ABC_Jobs/savedJob";

	$(".saved").on("click", function(event) {

		let job_id = event.currentTarget.dataset.jid;
		let user_id = event.currentTarget.dataset.uid;
		$.post(url,
			{
				"uid": user_id,
				"jid": job_id
			},
			function(response) {
				console.log(response);
			}
		)
		alert("job has been saved");
	})
})