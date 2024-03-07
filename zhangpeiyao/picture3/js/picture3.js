function login() {
  let a = document.getElementById("account").value;
  let p = document.getElementById("password").value;
  console.log(a);
  console.log(p);
  axios
    .post(
      "https://a3ef-2409-8a0c-269-2240-4443-e69a-d6cc-16c.ngrok-free.app/GeneralServletDemo/loginServlet",
      { Announcement: a, password: p }
    )
    .then((res) => {
      console.log(res);
      console.log(res.data);
    });
  }
