var editSche;
var mainPage;

function getEditPage(){

    if(editSche != null){
        editSche.close();
    }

    mainPage = window.self;
    console.log(mainPage)

    editSche = window.open("http://localhost:8080/timeTable/edit","","width=420px height=500px");
    editSche.focus();
}
