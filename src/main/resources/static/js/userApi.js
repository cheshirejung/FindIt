
function openDeleteModal() {
    document.getElementById('deleteModal').style.display = 'block';  // 모달 띄우기
    document.body.classList.add('modal-open');  // 배경 클릭 비활성화
}

function closeDeleteModal() {
    document.getElementById('deleteModal').style.display = 'none';  // 모달 닫기
    document.body.classList.remove('modal-open');  // 배경 클릭 활성화
}
