function login() {
  let a = document.getElementById("account").value;
  let p = document.getElementById("password").value;
  console.log(a);
  console.log(p);
  axios
    .post(
      "https://be49-2409-890e-220-829c-245a-57b6-414f-c7cc.ngrok-free.app/GeneralServletDemo//loginServlet",
      { account: a, password: p }
    )
    .then((res) => {
      console.log(res);
      console.log(res.data);
    });
  }
