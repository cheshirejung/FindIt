function confirmDelete(event, postId, commentId) {
    event.preventDefault();

    if (confirm("정말 삭제하시겠습니까?")) {
        fetch(`/post/${postId}/comment/${commentId}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert("삭제되었습니다.");
                    location.reload(); // 삭제 후 페이지를 새로고침하여 반영
                } else {
                    alert("삭제에 실패했습니다.");
                }
            })
            .catch(error => {
                console.error("삭제 중 오류가 발생했습니다:", error);
                alert("삭제에 실패했습니다.");
            });
    }
}

function toggleEditForm(commentId) {
    const editForm = document.getElementById('edit-form-' + commentId);
    editForm.style.display = (editForm.style.display === 'none' || editForm.style.display === '') ? 'block' : 'none';
}
