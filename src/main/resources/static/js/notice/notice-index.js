$(function () {
    $("#returnHomeBtn").click(ReturnHome);
});

// 退出主页公告详情页面，返回主页公告列表页面
function ReturnHome() {
    var current = $("#returnHomeBtn").val();
    location.href = CONTEXT_PATH + "/user/index" + "?current=" + current;
}