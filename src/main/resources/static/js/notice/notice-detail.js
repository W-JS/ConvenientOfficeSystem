$(function () {
    $("#returnIndexBtn").click(ReturnIndex);
});

// 退出首页公告详情页面，返回首页公告列表页面
function ReturnIndex() {
    var current = $("#returnIndexBtn").val();
    location.href = CONTEXT_PATH + "/notice/noticeIndex" + "?current=" + current;
}