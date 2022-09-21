
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

$(".todoAddBtn").on('click', function addList() {
    var textObject = $('.todoText');
    var contents = textObject.val();
    // 입력창에 접근

    if(contents === "" || contents === null) // 입력창에 값이 없으면
    {
        alert("일정을 입력해주세요."); // 경고창 출력

        textObject.focus();
        // 입력창에 포커스 (활성화)

        return false;
        // 함수 종료
    }

    let date = new Date();
    let URL = "http://localhost:8080/api/school/todo";
    let token = $('.token').text();
    let todo = {
            body : contents,
            year : date.getFullYear(),
            month : date.getMonth() + 1,
            day : date.getDate()
        }

    $.ajax({
        type: 'POST',
        url: URL,
        datatype : "json",
        data : JSON.stringify(todo),
        headers: {
            "Authorization": 'Bearer ' + token,
            "Content-Type" : "application/json"
        },
        success: function (result) {
            if (result.success) {
                var tr = document.createElement("tr"); // 추가할 테이블 <tr> 생성
                var input = document.createElement("input"); // 테이블 <tr> 안에 들어갈 체크박스의 <input> 생성

                // 여기서 생성된 <tr> 안에는
                // <td>체크박스</td>
                // <td>텍스트<td>
                // 이렇게 두 가지의 요소가 들어가야 함

                // 체크박스 만들기
                input.setAttribute("type","checkbox"); // <input type="checkbox">
                input.setAttribute("class","btn-chk"); // <input type="checkbox" class="btn-chk">
                input.setAttribute("key", result.todoId);

                var td01=document.createElement("td"); // 첫 번째 <td> 생성 (체크박스를 담음)
                td01.appendChild(input); // 첫 번째 <td> 안에 <input> 추가

                var td02 = document.createElement("td"); // 두 번째 <td> 생성 (텍스트를 담음)
                td02.innerHTML = contents; // 두 번째 <td> 안에 입력창의 텍스트를 저장

                tr.appendChild(td01);
                tr.appendChild(td02); // 생성된 <tr> 안에 체크박스 td와 텍스트 td를 넣음

                $(".todoListBody").append(tr); // tbody의 #listBody에 접근하여 tr을 자식요소로 추가

                textObject.val(""); // 입력창의 내용이 추가되었으므로 입력창 지우기

                textObject.focus(); // 입력창 포커스 (활성화)
            }
        },
        error: function () {
            alert("다시 요청 바랍니다.")
        }

    })
})

$('#todoDelCheck').on('click', function deleteAll(){

    let token = $('.token').text();
    let checkedTodoList = $('.todo input:checked');

    for(let i = 0; i < checkedTodoList.length; i++){
        let todoId = checkedTodoList[i].getAttribute("key");
        let URL = "http://localhost:8080/api/school/todo/" + todoId;

        $.ajax({
                type: 'DELETE',
                url: URL,
                datatype : "json",
                headers: {
                    "Authorization": 'Bearer ' + token,
                    "Content-Type" : "application/json"
                },
                success: function (result) {
                    console.log(`delete id : ${todoId}`)
                },
                error: function () {
                    alert("다시 요청 바랍니다.")
                }
        })
    }

    var list = document.getElementsByClassName("todoListBody")[0]; // listBody에 접근
    var chkbox=document.querySelectorAll(".todoListBody .btn-chk"); // listBody 하위의 체크박스 모두 선택
    for (var i in chkbox) { // i에 체크박스 인덱스 들어옴
        if (chkbox[i].checked) { // 체크박스가 체크되었으면
            list.removeChild(chkbox[i].parentNode.parentNode); //체크박스 i번째의 부모(<td>)의 부모(<tr>) 제거
        }
    }
})

$('#todoDelAll').on('click', function deleteAll(){

    let token = $('.token').text();
    let allTodoList = $('.btn-chk');
    console.log(allTodoList);

    for(let i = 0; i < allTodoList.length; i++){
        let todoId = allTodoList[i].getAttribute("key");
        let URL = "http://localhost:8080/api/school/todo/" + todoId;

        $.ajax({
            type: 'DELETE',
            url: URL,
            datatype : "json",
            headers: {
                "Authorization": 'Bearer ' + token,
                "Content-Type" : "application/json"
            },
            success: function (result) {
                console.log(`delete id : ${todoId}`)
            },
            error: function () {
                alert("다시 요청 바랍니다.")
            }
        })
    }

    $('.todoListBody').empty();
})