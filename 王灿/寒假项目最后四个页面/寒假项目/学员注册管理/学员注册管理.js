var currentPage = 1
var pages = document.querySelectorAll('.page')
document.querySelector('#forward').addEventListener('click', function () {
    if (currentPage < pages.length) {
    currentPage++; // 前进一步
    updatePages();
    }
})
document.querySelector('#back').addEventListener('click', function () {
    if (currentPage > 1) {
      currentPage--; // 后退一步
    updatePages();
    }

})
function updatePages() {
    // 隐藏所有菜单
    pages.forEach(function(page) {
    page.classList.remove('active');
    });

    // 显示当前菜单
    pages[currentPage - 1].classList.add('active');
}

// 初始时显示第一个菜单
updatePages();
let save = document.querySelector('#save')
let saveSuccess=document.querySelector('.saveSuccess')
save.addEventListener('click', function () {
    saveSuccess.style.display='block'
})
document.querySelector("#power").addEventListener(
    'click',
    function () {
        location.href="../权限授予/权限授予.html"
    }
)
document.querySelector("#assess").addEventListener(
    'click',
    function () {
        location.href="../学员评估体系/学员评估体系.html"
    }
)
document.querySelector("#request").addEventListener(
    'click',
    function () {
        location.href="../学员请假管理/学员请假管理.html"
    }
)

