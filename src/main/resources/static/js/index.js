
// Java file for main page

console.log("hola");
console.log("hola");
console.log("hola");


function shortdescr(event){
    let descPath = event.target.closest( 'div' ).querySelector( "#description" ).querySelector("c:out").value;
    let descName = descPath.substring(0,30);
    modalImageName.setAttribute( "value", descName );
}


let info = document.querySelectorAll(".info");

for (let i = 0; i < info.length; i++){
    info[i].addEventListener(shortdescr);
}