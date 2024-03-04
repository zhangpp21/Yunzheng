function confirm() {
    let n = document.getElementById("name2").value;
    let c = document.getElementById("class2").value;
    let ba = document.getElementById("batch2").value;
    let p = document.getElementById("position2").value;
    let bi = document.getElementById("bio2").value;
    let e = document.getElementById("experience2").value;
    console.log(n, c, ba, p, bi, e);
    axios({
        method: 'post',
        url: '',
        data: {
            name: n,
            class: c,
            batch: ba,
            position: p,
            bio: bi,
            experience: e,
        }
    })
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });
}