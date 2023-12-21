function sign_in() {
  let email = $("#email").val()
  let password= $("#password").val()

  let info = {
    email: email,
    password: password
  }

  $.ajax({
    type: "POST",
    url: `/auth/login`,
    contentType: "application/json",
    data: JSON.stringify(info),
    success: function (response) {
      localStorage.setItem("accessToken", response['accessToken']);
      localStorage.setItem("refreshToken", response['refreshToken']);
      localStorage.setItem("accessTokenExpiresIn", response['accessTokenExpiresIn']);
      localStorage.setItem("userIdx", response['userIdx']);

      location.href = '/';
    },
    error: function (response) {
      alert("아이디 혹은 비밀번호를 확인해주세요.")
    }
  });
}