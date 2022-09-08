function idConfirm() {
    var BASEURL = "http://localhost:8080/api/user/confirm/name";
    var body = {
        id : $('#id').val()
    }
    console.log(body);

    $.ajax({
        type : 'POST',
        url : BASEURL,
        data : JSON.stringify(body),
        datatype: "JSON",
        contentType: "application/json; charset=utf-8",
        success : function(data) {
            if(data.success == true) {
                console.log('success')
                $('#checkId').text("").text(true).hide();
            }
            else{
                $('#checkId').text("중복된 아이디 입니다. 다른 아이디를 입력해주세요.").show()
                    .css('color', 'red').css('font-weight', 'bold');
            }
        },
        error : function() {
        }
    });
}

function studentIdConfirm() {
    var userYear = $('#userYear').val();
    // $("#셀렉트박스ID option:selected").val();
    var userNum = $('#schoolnumber').val();
    var userGrade = $('#schoolgrade option:selected').val();
    var userClass = $('#schoolclass option:selected').val();

    console.log(userClass + " " + userNum)
    console.log(userClass / 10)
    console.log(userNum / 10)

    //2022 + 1 + 01 + 01   (입학년도 + 학년 + 반 + 번호 반이랑 번호에는 0붙여서 입력)
    if(userClass / 10 < 1)
        userClass = "0" + userClass;
    if(userNum / 10 < 1)
        userNum = "0" + userNum;

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
            if(data.success == true) {
                if(data.success == true) {
                    $('#checkAuth').text(true);
                }
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

function checkCondition(){
    var studentId = $('#checkAuth').text();
    var id = $('#checkId').text();

    if(studentId == 'true' && id == 'true')
        return true;
    else{
        alert('아이디 중복 확인과 학번 인증을 모두 완료해주세요!!')
    }
}