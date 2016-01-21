$(document).ready(function() {
	var myExampleClickHandler = function(event){ 
		$('#log').html([
		'<b>Element:</b> ', 
		this.label, 
		this.textContent
		].join('<br>')); 
	  console.log(this);
	};
	var myDomOutline = DomOutline({ 
		onClick: myExampleClickHandler, 
		filter: false,
		stopOnClick: false,
		hideLabel: false
	});
	myDomOutline.start();

    $('#start').click(function(){
      myDomOutline.start();
      return false;
    })

    $('#stop').click(function(){
      myDomOutline.stop();
      return false;
    })
});