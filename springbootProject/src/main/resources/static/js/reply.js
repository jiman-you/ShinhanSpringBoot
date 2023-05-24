//즉시 실행함수로 만듦 (function(){})()
//키와 메서드를 가진 JaVaScript Object를 return함
//RestFul방식으로 요청

//토큰읽어라
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");


//이거를 실행한 결과는 여기잇어
var externalFunc = (function(){
	return{myname:"jin",mycompany:"신한",
			work:function(){alert("외부함수");}
		};
})(); 

//동일한 역할군을 모아서 하나의 변수에 다 묶어서 유지보수 관리가 편하게 함
var replyManager = (function(){
    
    // 특정 board의 댓글 가져오기 ==> replies/100 다 가져와라
    //callback이라는 파라메터가 callback에 함수 형태로 들어가서 실행이 된다.
    var getAll = function(obj, callback){
        console.log("get All.....");
        $.getJSON("/app/replies/" + obj, callback); //obj -/app/replies/board의 번호--RestFul방식
    };
    
    //선언적 함수
    function beforeSend(xhr){
    	xhr.setRequestHeader(header, token);
    }
    
    //board의 댓글추가{"bno":11,title:"aa", writer:"bb"'} 
    //--- webreply형태로 받게되는데 그 값에 맞춰서 알아서 들어가는듯??? rno는 null로 들어간다음 시퀀스로 지정되고??
    var add = function(obj, callback){
        console.log("add.....");
        $.ajax({
	        beforeSend : beforeSend,
            type:"post",
            url:"/app/replies/"+obj.bno,
            data:JSON.stringify(obj),
            dataType:"json",
            contentType:"application/json",
            success:callback
        });
    };
    
    var update = function(obj, callback){
        $.ajax({
          beforeSend : beforeSend,
            type:"put",
            url:"/app/replies/"+obj.bno ,
            data:JSON.stringify(obj),
            dataType:"json",
            contentType:"application/json",
            success:callback
        });
    };
    var remove = function(obj, callback){
        $.ajax({
          beforeSend : beforeSend,
        type:"delete",
        url:"/app/replies/"+obj.bno + "/" + obj.rno,
        dataType:"json",
        contentType:"application/json",
        success:callback
        });
        
    };
    //함수.getAll 형태로 호출하면 키:값 이기 때문에 뒤에있는 getAll이 실행
    return { getAll : getAll,  add : add,  update : update, remove : remove };
})();