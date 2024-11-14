// URL에서 'error'와 'exception' 파라미터를 가져오는 함수
function getUrlParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
}

// 페이지 로드 시 'error' 파라미터와 'exception' 파라미터가 존재하면 alert 띄우기
window.onload = function() {
    const error = getUrlParameter('error');
    const exception = getUrlParameter('exception');

    if (error === 'true' && exception) {
        // URL 파라미터에 'error=true'와 'exception'이 있으면
        // 디코딩된 메시지를 alert으로 띄움
        const decodedMessage = decodeURIComponent(exception);
        alert(decodedMessage);
    }
};