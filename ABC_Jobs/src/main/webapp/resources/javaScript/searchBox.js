const searchform = document.getElementById("searchBox");
const query = searchform.search;

function checkQueryLength(){
	
	let value = query.value;
	if(value.length > 0){
		return true;
	}
	return false;
	
}