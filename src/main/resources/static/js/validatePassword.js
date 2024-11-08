// 비밀번호 입력과 비밀번호 확인 란이 같은지 검사
function validatePassword() {
    const password = document.getElementById("password").value;
    const rePassword = document.getElementById("rePassword").value;

    if (password !== rePassword) {
        alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
        return false; // 폼 제출을 막음
    }
    return true; // 폼 제출 허용
}