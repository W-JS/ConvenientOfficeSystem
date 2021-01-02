$(function () {
    $("#auditYBtn").click(AuditY);
    $("#auditNBtn").click(AuditN);
    $("#returnAuditNoticeBtn").click(ReturnAuditNotice);
});

// 审核通过
function AuditY() {
    var noticeno = $("#noticeno").val();
    $.post(
        CONTEXT_PATH + "/notice/auditNoticeY",
        {"noticeno": noticeno, "auditstatus": 1},
        function (data) {
            $("#hintBody").text("审核通过!");
            $("#hintModal").modal("show");
            setTimeout(function () {
                $("#hintModal").modal("hide");
                if (data == "0") {
                    window.location.href = CONTEXT_PATH + "/notice/auditNotice";
                }
            }, 2000);
        }
    );
}

// 审核不通过
function AuditN() {
    var noticeno = $("#noticeno").val();
    $.post(
        CONTEXT_PATH + "/notice/auditNoticeN",
        {"noticeno": noticeno, "state": 2},
        function (data) {
            $("#hintBody").text("审核不通过!");
            $("#hintModal").modal("show");
            setTimeout(function () {
                $("#hintModal").modal("hide");
                if (data == "0") {
                    window.location.href = CONTEXT_PATH + "/notice/auditNotice";
                }
            }, 2000);
        }
    );
}

// 退出审核公告详情页面，返回审核公告列表页面
function ReturnAuditNotice() {
    var current = $("#returnAuditNoticeBtn").val();
    location.href = CONTEXT_PATH + "/notice/auditNotice" + "?current=" + current;
}