$(function () {
    // 实时显示主题字符数
    let topicLength = $('#topic').val().length;
    $('#topicNum').text(topicLength);
    $('#topic').on("keyup", function () {
        topicLength = $('#topic').val().length;
        $('#topicNum').text(topicLength); //在键盘按下时，实时的显示字数
        if (topicLength > 50) {
            $('#topicNum').text(50); //长度大于50时0处显示的也只是50
            $('#topic').val($('#topic').val().substring(0, 50)); //长度大于50时截取前50个字符
        }
    });

    let day = $("#day").val();
    let time = "";
    if (day == 7) {
        time = "一周";
        timeRange = "oneWeek";
    } else if (day == 28 || day == 29 || day == 30 || day == 31) {
        time = "一个月";
        timeRange = "oneMonth";
    } else if (day == 365 || day == 366) {
        time = "一年";
        timeRange = "oneYear";
    } else {
        time = "三天";
        timeRange = "threeDays";
    }

    $("#defaultTimeBtn").text(time);
});

$(function () {
    $("#updatePublishBtn").click(UpdatePublish);
    $("#updateSaveBtn").click(UpdateSave);
    $("#updateAbandonBtn").click(UpdateAbandon);
});

// 修改已保存的一个公告，并发布
function UpdatePublish() {
    var noticeno = $("#noticeno").val();
    var topic = $("#topic").val();
    var content = tinymce.activeEditor.getContent();
    if (topic == null || topic == "" || topic.length > 50) {
        alert("请输入需要发布内容的主题，且长度不超过50！！！");
        return false;
    }
    if (content == null || content == "" || content.length > 5000) {
        alert("请输入需要发布的内容，且长度不超过5000！！！");
        return false;
    }
    $.post(
        CONTEXT_PATH + "/notice/updateNotice",
        {"topic": topic, "content": content, "timeRange": timeRange, "state": 1, "auditstatus": 0, "noticeno": noticeno},
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

// 修改已保存的一个公告，并保存
function UpdateSave() {
    var noticeno = $("#noticeno").val();
    var topic = $("#topic").val();
    var content = tinymce.activeEditor.getContent();
    var current = $("#updateSaveBtn").val();
    if (topic == null || topic == "" || topic.length > 50) {
        alert("请输入需要发布内容的主题，且长度不超过50！！！");
        return false;
    }
    if (content == null || content == "" || content.length > 5000) {
        alert("请输入需要发布的内容，且长度不超过5000！！！");
        return false;
    }
    $.post(
        CONTEXT_PATH + "/notice/updateNotice",
        {"topic": topic, "content": content, "timeRange": timeRange, "state": 2, "auditstatus": 0, "noticeno": noticeno},
        function (data) {
            $("#hintBody").text("保存成功!");
            $("#hintModal").modal("show");
            setTimeout(function () {
                $("#hintModal").modal("hide");
                if (data == "0") {
                    location.href = CONTEXT_PATH + "/notice/draftsNotice" + "?current=" + current;
                }
            }, 2000);
        }
    );
}

// 未修改公告，并返回草稿箱页面
function UpdateAbandon() {
    var current = $("#updateAbandonBtn").val();
    location.href = CONTEXT_PATH + "/notice/draftsNotice" + "?current=" + current;
}