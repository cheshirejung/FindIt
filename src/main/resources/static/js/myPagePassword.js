// myPagePassword.js
function validateMyPagePassword() {
    const password = document.getElementById("password").value;
    const rePassword = document.getElementById("rePassword").value;

    // 비밀번호 필드가 둘 다 비어있으면 통과 (비밀번호 변경을 하지 않는 경우)
    if (password === "" && rePassword === "") {
        return true;
    }

    // validatePassword() 함수 호출 (기존 비밀번호 검증 로직 사용)
    return validatePassword();
}