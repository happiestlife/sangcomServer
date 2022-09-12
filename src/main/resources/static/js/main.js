
function main() {

(function () {
   'use strict';
   
  	$('a.page-scroll').click(function() {
        if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
          var target = $(this.hash);
          target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
          if (target.length) {
            $('html,body').animate({
              scrollTop: target.offset().top - 50
            }, 900);
            return false;
          }
        }
      });


    $('body').scrollspy({ 
        target: '.navbar-default',
        offset: 80
    });

	// Hide nav on click
  $(".navbar-nav li a").click(function (event) {
    // check if window is small enough so dropdown is created
    var toggle = $(".navbar-toggle").is(":visible");
    if (toggle) {
      $(".navbar-collapse").collapse('hide');
    }
  });
	
	
    // Nivo Lightbox 
    $('.portfolio-item a').nivoLightbox({
            effect: 'slideDown',  
            keyboardNav: true,                            
        });
		
}());


}
main();


function changeColor() {
  var color = ["#FC5C7D", "#6A82FB", "#38ef7d", "#fffbd5", "#b20a2c", "#CAC531"];

  var num = Math.floor(Math.random() * color.length);
  var bodyTag = document.getElementsByTagName("td");
  bodyTag[0].style.backgroundColor = color[num];
}

function idConfirm() {
  var BASEURL = "http://localhost:8080/api/user/confirm/name";
  var body = {
      id : $('#inputID').val()
  }
  console.log(body);

  $.ajax({
      type : 'POST',
      url : BASEURL,
      data : JSON.stringify(body),
      datatype: "JSON",
      contentType: "application/json; charset=utf-8",
      success : function(data) {
        alert("성공")
          if(data.success == true) {
              // $('#idConfirm').text(true).hide();
              // console.log($('#idConfirm').val())
              console.log(JSON.stringify(data));
          }
          else{
              $('#checkId').text("중복된 아이디 입니다. 다른 아이디를 입력해주세요.").show()
                  .css('color', 'red').css('font-weight', 'bold');
          }
      },
      error : function() {
        alert("실패");
      }
  });
}

function studentIdConfirm() {
  var userYear = $('#userYear').val();
  // $("#셀렉트박스ID option:selected").val();
  var userNum = $('#schoolnumber').val();
  var userGrade = $('#schoolgrade option:selected').val();
  var userClass = $('#schoolclass option:selected').val();
  userClass="0"+userClass; //2022 + 1 + 01 + 01   (입학년도 + 학년 + 반 + 번호 반이랑 번호에는 0붙여서 입력)
  var studentID = userYear+userGrade+userClass+userNum;
  var BASEURL = "http://localhost:8080/api/user/auth/student/check";
  var body = {
      name : $('#userName').val(),
      studentId : studentID
  }
  console.log(body);

  $.ajax({
      type : 'POST',
      url : BASEURL,
      data : JSON.stringify(body),
      datatype: "JSON",
      contentType: "application/json; charset=utf-8",
      success : function(data) {
        alert("성공")
          if(data.success == true) {
              // $('#idConfirm').text(true).hide();
              // console.log($('#idConfirm').val())
              console.log(JSON.stringify(data));
          }
          else{
              alert('학번인증에 실패하였습니다.');
          }
      },
      error : function() {
        alert("실패");
      }
  });
}