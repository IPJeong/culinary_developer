/**
 * validation check
 */

var msg_id = "아이디를 입력하세요.";
var msg_pwd = "비밀번호를 입력하세요.";
var msg_repwd = "비밀번호 확인을 입력하세요.";
var msg_repwdChk = "비밀번호가 다릅니다.";
var msg_name = "이름을 입력하세요.";
var msg_birth = "주민번호를 입력하세요.";
var msg_email = "이메일을 입력하세요.";
var msg_emailChk = "이메일형식에 맞지 않습니다.";
var msg_confirmId = "중복확인을 해주세요.";
var insertError = "회원가입에 실패했습니다.\n잠시후 다시 시도하세요.";
var passwdError = "비밀번호가 일치하지 않습니다.\n다시 시도하세요.";
var deleteError = "회원탈퇴에 실패했습니다.\n 잠시후 다시 시도하세요.";
var deleteError = "회원탈퇴에 실패했습니다.\n 잠시후 다시 시도하세요.";
var replyError = "답글이 있는 경우 삭제할 수 없습니다. \n 답글을 먼저 삭제해 주세요.";

function errorAlert(msg) {
	alert(msg);
	window.history.back();
}

function mainFocus() {
	document.mainform.id.focus();
}

//메인화면 버튼 클릭시
function mainCheck() {
	if(!document.mainform.id.value) {
		alert();
		document.mainform.id.focus();
		return false;
	} else if(!document.mainform.passwd.value) {
		alert(msg_pwd);
		document.mainform.passwd.focus();
		return false;
	}
}

function inputFocus() {
	document.inputform.id.focus();
}

// 회원가입 버튼 클릭시
function inputCheck() {
	if(!document.inputform.id.value) {
		alert(msg_id);
		document.inputform.id.focus();
		return false;
	} else if(!document.inputform.passwd.value) {
		alert(msg_pwd);
		document.inputform.passwd.focus();
		return false;
	} else if(!document.inputform.repasswd.value) {
		alert(msg_repwd);
		document.inputform.repasswd.focus();
		return false;
	} else if(document.inputform.passwd.value != document.inputform.repasswd.value ) {
		alert(msg_repwdChk);
		document.inputform.passwd.focus();
		return false;
	} else if(!document.inputform.name.value) {
		alert(msg_name);
		document.inputform.name.focus();
		return false;
	} else if(!document.inputform.jumin1.value) {
		alert(msg_repwdChk);
		document.inputform.jumin1.focus();
		return false;
	} else if(!document.inputform.email1.value) {
		alert(msg_email);
		document.inputform.email1.focus();
		return false;
		//이메일 직접입력일 경우 "@"가 포함되지 않으면 경고
		//중복확인을 하지 않은 경우
		//체크전체조건 : <input type="hidden" name="hiddenId" value="0">
	} else if(document.inputform.hiddenId.value == "0") {
		alert(msg_confirmId);
		document.inputform.dupChk.focus();
		return false;
	}
}

//회원가입 중복확인 페이지
function confirmId() {
	if(!document.inputform.id.value){
		alert("아이디를 입력해주세요.");
		document.inputform.id.focus();
		return false;
	}
	
	//window.open("파일명", "윈도우명", "창속성")
	//url="주소값?속성값=" + 속성 value;
	var url = "confirmId?id="+document.inputform.id.value;
	window.open(url, "confirm", "menubar=no, width=500, height=400");
	
}

function nextJumin1() {
	if(document.inputform.jumin1.value.length>=6){
		document.inputform.jumin2.focus();
	}
}

function nextJumin2() {
	if(document.inputform.jumin2.value.length>=7){
		document.inputform.hp1.focus();
	}
}

function nextHp1() {
	if(document.inputform.hp1.value.length>=3){
		document.inputform.hp2.focus();
	}
}

function nextHp2() {
	if(document.inputform.hp2.value.length>=4){
		document.inputform.hp3.focus();
	}
}

function nextHp3() {
	if(document.inputform.hp3.value.length>=4){
		document.inputform.email1.focus();
	}
}

function confirmIdFocus() {
	document.confirmform.id.focus();
}

function confirmIdCheck() {
	if(!document.confirmform.id.value){
		alert("아이디를 입력해주세요.");
		document.confirmform.id.focus();
		return false;
	}
}

// opener : window 객체의 open() 메서드로 열린 새창(=중복확인창)에서, 열어준 부모창(=회원가입창)에 접근할 때 사용 
// self.close(); 메세지 없이 현재창을 닫을 때 사용
function setId(id) {
	opener.document.inputform.id.value = id;
	opener.document.inputform.hiddenId.value = "1";
	self.close();
}

function passwdFocus() {
	document.passwdform.passwd.focus();
}

function passwdCheck() {
	if(!document.passwdform.passwd.value){
		alert(msg_pwd);
		document.passwdform.passwd.focus();
		return false;
	}
}

function modifyFocus() {
	
}

function modifyCheck() {
	// 비밀번호 값이 없을 때
	if(!document.modifyform.passwd.value) {
		alert(msg_pwd);
		document.modifyform.passwd.focus();
		return false;
		
	} else if(!document.modifyform.repasswd.value) {
		alert(msg_repwd);
		document.modifyform.repasswd.focus();
		return false;
		
	} else if(document.modifyform.passwd.value != document.modifyform.repasswd.value ) {
		alert(msg_repwdChk);
		document.modifyform.passwd.focus();
		return false;
		
	} else if(!document.modifyform.email1.value) {
		alert(msg_email);
		document.modifyform.email1.focus();
		return false;
	
	} else if(!document.modifyform.email2.value) {
		alert(msg_email);
		document.modifyform.email2.focus();
		return false;
	}
	
}


