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
      url: "https://be49-2409-890e-220-829c-245a-57b6-414f-c7cc.ngrok-free.app/GeneralServletDemo//loginServlet",
    })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
}