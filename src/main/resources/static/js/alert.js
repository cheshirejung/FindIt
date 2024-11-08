
// 회원가입 성공 알림창 : URL에서 쿼리 파라미터를 가져와서 'success'가 있으면 알림창 표시
window.addEventListener('DOMContentLoaded', () => {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('success') === 'true') {
        alert("정상적으로 회원가입이 완료됐습니다.");
    }
});