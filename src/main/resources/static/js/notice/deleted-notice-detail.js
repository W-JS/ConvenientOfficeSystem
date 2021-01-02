$(function () {
    $("#rePublishBtn").click(RePublish);
    $("#deletedBtn").click(Deleted);
});

// 将已删除的公告重新发布
function RePublish() {
    var noticeno = $("#rePublish").val();
    $.post(
        CONTEXT_PATH + "/notice/rePublish",
        {"state": 1, "noticeno": noticeno},
        function (data) {
            $("#hintBody").text("发布成功!");
            $("#hintModal").modal("show");
            setTimeout(function () {
                $("#hintModal").modal("hide");
                if (data == "0") {
                    window.location.href = CONTEXT_PATH + "/notice/noticeIndex";
                }
            }, 2000);
        }
    );
}

// 退出已删除公告详情页面，返回已删除公告页面
function Deleted() {
    var current = $("#deletedBtn").val();
    location.href = CONTEXT_PATH + "/notice/deletedNotice" + "?current=" + current;
}