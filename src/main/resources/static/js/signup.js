function checkEmail() {
    console.log("[checkEmail] 버튼 클릭됨");

    const email = document.getElementById("email").value;
    const emailCheckResult = document.getElementById("email-check-result");

    emailCheckResult.textContent = "";

    if (!validateEmailFormat(email)) {
        console.log("[checkEmail] 이메일 형식 오류");
        emailCheckResult.textContent = "올바른 이메일 형식이 아닙니다.";
        return;
    }

    console.log(`[checkEmail] fetch 요청 시작: /api/check-email?email=${email}`);

    fetch(`/api/check-email?email=${encodeURIComponent(email)}`)
        .then(response => {
            console.log("[checkEmail] fetch 응답 상태", response.status);
            return response.json();
        })
        .then(data => {
            console.log("[checkEmail] 응답 데이터", data);
            if (data.exists) {
                emailCheckResult.textContent = "이미 사용 중인 이메일입니다.";
                emailCheckResult.classList.remove("success");
                emailCheckResult.classList.add("error");
            } else {
                emailCheckResult.textContent = "사용 가능한 이메일입니다.";
                emailCheckResult.classList.remove("error");
                emailCheckResult.classList.add("success");
            }
        })
        .catch(err => {
            console.error("[checkEmail] 오류 발생", err);
            emailCheckResult.textContent = "중복 확인 중 오류 발생";
        });
}

function validateForm() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    let isValid = true;

    const emailError = document.getElementById("email-error");
    emailError.textContent = "";
    if (!validateEmailFormat(email)) {
        emailError.textContent = "올바른 이메일 형식이 아닙니다.";
        isValid = false;
    }

    const passwordError = document.getElementById("password-error");
    passwordError.textContent = "";
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[\W_]).{8,16}$/;
    if (!passwordRegex.test(password)) {
        passwordError.textContent = "8~16자의 영문 대/소문자, 숫자, 특수문자를 포함해야 합니다.";
        isValid = false;
    }

    const confirmError = document.getElementById("confirm-error");
    confirmError.textContent = "";
    if (password !== confirmPassword) {
        confirmError.textContent = "비밀번호가 일치하지 않습니다.";
        isValid = false;
    }

    return isValid;
}

function validateEmailFormat(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
}