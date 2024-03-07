function del (){
    let a = document.getElementById("hint");
    let b = document.getElementById("btn");
    a.remove();
    b.remove();
}

function login() {
    let a = document.getElementById("account").value;
    let r = document.getElementById("reset").value;
    let n = document.getElementById("new").value;
    let c = document.getElementById("confirm").value;
    console.log(a);
    console.log(r);
    console.log(n);
    console.log(c);
    axios({
      method: "post",
      url: "https://a3ef-2409-8a0c-269-2240-4443-e69a-d6cc-16c.ngrok-free.app/GeneralServletDemo/ForgetPasswordServlet",
    })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
}