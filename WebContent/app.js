$(document).ready(function() {
	var myExampleClickHandler = function(event){ 
		console.log(this.label);
		var retVal = prompt("Nome del campo: ","");
		if (retVal != undefined && retVal != "") {
			$.ajax({
				url : "PatternController",
				type: "get",
				data: {name:retVal, newPattern: this.label},
				dataType: "text",                   
				success: function (data){
					console.log("OK", data);
				},
				error: function (data){
					console.log("NO", data);
				}
			});}
	};
	var myDomOutline = DomOutline({ 
		onClick: myExampleClickHandler, 
		filter: false,
		stopOnClick: false,
		hideLabel: false
	});
	myDomOutline.start();
});