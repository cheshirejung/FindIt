document.getElementById("deleteUserForm").addEventListener('submit', function(event) {
    event.preventDefault(); // 폼의 기본 제출 동작 방지

    const form = event.target;
    const formData = new FormData(form);

    fetch('/user', {
        method: 'DELETE',
        body: formData
    })
        .then(response => {
            // 응답 상태 코드 확인
            if (response.ok) { // 200번대 응답
                console.log("삭제 성공");
                alert("성공적으로 삭제되었습니다!");
                location.href = '/user/login';
            } else if (response.status === 401) { // 비밀번호 불일치 (401 Unauthorized)
                console.log("비밀번호 불일치");
                alert('비밀번호가 맞지 않습니다!');
            } else {
                throw new Error("요청 실패"); // 그 외 에러 처리
            }
        })
        .catch(error => {
            console.error("에러 발생:", error);
            alert('문제가 발생했습니다. 다시 시도해주세요.');
        });
});

window.onload = function() {
    if (sessionStorage.getItem('modalOpen') === 'true') {
        openDeleteModal();
    }
}

function openDeleteModal() {
    document.getElementById('deleteModal').style.display = 'block';
    document.body.classList.add('modal-open');
    // sessionStorage.setItem('modalOpen', 'true');
}

function closeDeleteModal() {
    document.getElementById('deleteModal').style.display = 'none';
    document.body.classList.remove('modal-open');
    // sessionStorage.removeItem('modalOpen');
}

function confirmUpdate() {
    return confirm("정말 수정하시겠습니까?");
}
