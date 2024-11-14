function updateProfile(event) {
    event.preventDefault();

    // 비밀번호 확인
    const password = document.getElementById('password').value;
    const rePassword = document.getElementById('re_password').value;

    if (password !== rePassword) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }

    // 변경된 데이터만 포함하는 객체 생성
    const formData = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value
    };

    // 비밀번호가 입력된 경우에만 포함
    if (password) {
        formData.password = password;
    }

    fetch('/api/user/profile', {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    })
        .then(response => {
            if (response.ok) {
                alert('프로필이 업데이트되었습니다.');
                location.reload(); // 페이지 새로고침
            } else {
                alert('프로필 업데이트에 실패했습니다.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert('오류가 발생했습니다.');
        });
}

function openDeleteModal() {
    document.getElementById('deleteModal').style.display = 'block';  // 모달 띄우기
    document.body.classList.add('modal-open');  // 배경 클릭 비활성화
}

function closeDeleteModal() {
    document.getElementById('deleteModal').style.display = 'none';  // 모달 닫기
    document.body.classList.remove('modal-open');  // 배경 클릭 활성화
}
