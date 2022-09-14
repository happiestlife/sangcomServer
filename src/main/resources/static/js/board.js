let time = new Date();
let month = time.getMonth()+1;
let day = time.getDate();

let comment = document.getElementById('comment');
let comment_favor = document.getElementById('commentFavoriteValue');
let comment_favor_Data = document.getElementsByClassName("commentFavorite");
let comment_add_Data = document.getElementsByClassName("commentAdd");
let vr = '1';

// document.getElementById('Date').innerHTML=today.getFullYear()+"년 "
//   +(today.getMonth()+1)+"월 "+(today.getDate())+"일 "+(Week[(today.getDay())])+"요일" ;

//   document.getElementById('Date2').innerHTML=today.getFullYear()+"년 "
//   +(today.getMonth()+1)+"월 " ;

function makecomment(input_text){
  var com ='<p class="commentList"> <span class="material-icons">cloud</span><b>익명/2022-'+month+'-'+day+'</b><br>'+input_text+'</p> <div id="comment_btn" class="justify-content-end d-flex">     <input type="button" class="material-icons commentBtn commentAdd" value="mode_comment">     <input type="button" class="material-icons commentBtn commentFavorite " value="favorite"      <span id="comment_favorite_value">1</span> </div>';
  return com;
}

function search() {
  var title = $('.searchInput').val();
  var type = $('#type').text();
  var URL = "http://localhost:8080/api/board/search?type=" + type + "&title=" + title;

  if(title == ""){
    alert("검색어를 입력해주세요!!");

    return false;
  }

  $.ajax({
    type : 'GET',
    url : URL,
    success : function(result){
      if(result.success){
        $('.board').remove();
        $('#searchKeyword').text(title);
        for(var i = 0; i < result.data.length; i++){
          var row = result.data[i];
          console.log(row)
          var rs = `<a href="/board/${row.board_id}" class="board">
                <article class="post">
                  <h5 class="postName">${row.title}</h5>
                  <p class="postContent">${row.body}</p>
  
                  <div class="symbols" style="right:10px">❤ </div>
                  <div class="symbols">${row.goodCount}</div>
                </article>
              </a>`
          console.log(rs);

          $('#boards').append(rs).show();
        }
      }
    },
    error : function(){
      alert("다시 요청 바랍니다.")
    }

  })
}

function enter() {
  if (window.event.keyCode == 13) {
    var target = document.getElementById('commentList');
    var text = document.getElementById('body').value;
    var input_comment = makecomment(text);

    if(text=='') {
      alert('댓글을 입력하세요.');
    }
    else {
      document.getElementById('commentText').value='';
      target.insertAdjacentHTML('beforeend',input_comment);
    }
  }
}

function createBoard() {
  var target = document.getElementById('boardCreate');
  target.insertAdjacentHTML('beforeend',"no");
}


function removeContent() {


  if(confirm("삭제하시겠습니까?")){
    alert("삭제되었습니다.");
    location.href = "main.html"
  }
  else {
    alert("취소되었습니다.");
  }
}