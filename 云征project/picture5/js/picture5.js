function del() {
    let a = document.getElementById("error");
    let b = document.getElementById("confirm");
    a.remove();
    b.remove();
}

function reg() {
    let a = document.getElementById("name2").value;
    let p = document.getElementById("password2").value;
    let c = document.getElementById("password4").value;
    let e = document.getElementById("email2").value;
    let v = document.getElementById("vcode2").value;
    console.log(a, p, c, e, v);
    axios({
      method: "post",
      url: " https://4310-2409-890e-220-829c-245a-57b6-414f-c7cc.ngrok-free.app/GeneralServletDemo//loginServlet",
      data: {
        account: a,
        password: p,
        confirmPassword: c,
        email: e,
        vcode: v,
      },
    })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
}