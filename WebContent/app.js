$(document).ready(function() {
var myExampleClickHandler = function(event){ 
    console.log(this.label);
    $.ajax({
        url : "PatternController",
        type: "get",
        data: {pattern: this.label},
        dataType: "text",                   
        success: function (data){
        	console.log("OK", data);
        },
        error: function (data){
        	console.log("NO", data);
        }
	});
};
  var myDomOutline = DomOutline({ 
    onClick: myExampleClickHandler, 
    filter: false,
    stopOnClick: false,
    hideLabel: false
  });
  myDomOutline.start();
});