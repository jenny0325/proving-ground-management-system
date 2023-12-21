function sign_up() {
  let email = $("#email").val()
  let password = $("#password").val()
  let password2 = $("#password2").val()
  let name = $("#name").val()
  let phone = $("#phone").val()
  let company = $("#company").val()
  let team = $("#team").val()
  let position = $("#position").val()

  let info = {
    email : email,
    password : password,
    name : name,
    phone : phone,
    company : company,
    team : team,
    position : position
  }

  if (!is_password(password)) {
    $("#password").text("비밀번호의 형식을 확인해주세요. 영문과 숫자 필수 포함, 특수문자(!@#$%^&*) 사용가능 8-20자").removeClass("is-safe").addClass("is-danger").focus()
    return
  }
  if (password2 !== password) {
    $("#password2").text("비밀번호가 일치하지 않습니다.").removeClass("is-safe").addClass("is-danger").focus()
    return;
  } else {
    $("#password2").text("비밀번호가 일치합니다.").removeClass("is-danger").addClass("is-success")
  }

  $.ajax({
    type: 'POST',
    url: `/signup`,
    contentType: "application/json",
    data: JSON.stringify(info),
    success: function (response) {
      alert("회원가입이 완료되었습니다!!");
      $('#my-modal').toggleClass("is-active");
      reAction();
    }
  })

}

function is_nickname(asValue) {
  var regExp = /^(?=.*[a-zA-Z])[-a-zA-Z0-9_.]{2,10}$/;
  return regExp.test(asValue);
}

function is_password(asValue) {
  var regExp = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z!@#$%^&*]{8,20}$/;
  return regExp.test(asValue);
}