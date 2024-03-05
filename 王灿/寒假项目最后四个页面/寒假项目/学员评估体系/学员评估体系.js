var currentPage = 1
let pages = document.querySelectorAll('.page')
document.querySelector('#forward').addEventListener('click', function () {
    if (currentPage < pages.length) {
        currentPage++
        updatePages()
    }
})
document.querySelector('#back'). addEventListener('click', function () {
    if (currentPage > 1) {
        currentPage--
        updatePages()
    }
})
function updatePages() {
    pages.forEach(function (page) {
        page.classList.remove('active')
    })
    pages[currentPage-1].classList.add('active')
}
updatePages()
let confirm= document.querySelector('#confirm')
let saveSuccess=document.querySelector('.saveSuccess')
confirm.addEventListener('click', function () {
    saveSuccess.style.display='block'
})
document.querySelector("#power").addEventListener(
    'click',
    function () {
        location.href="../权限授予/权限授予.html"
    }
)
document.querySelector("#register").addEventListener(
    'click',
    function () {
        location.href="../学员注册管理/学员注册管理.html"
    }
)
document.querySelector("#request").addEventListener(
    'click',
    function () {
        location.href="../学员请假管理/学员请假管理.html"
    }
)
