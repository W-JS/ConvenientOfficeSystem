$(function () {
    // 设置时间
    $("#time").jeDate({
        format: "YYYY-MM-DD hh:mm:ss"
    });
    $("#atime").jeDate({
        format: "YYYY-MM-DD hh:mm:ss"
    });
    // 实时显示主题字符数
    $('#topic').on("keyup", function() {
        let topicLength = $('#topic').val().length;
        $('#topicNum').text(topicLength); //在键盘按下时，实时的显示字数
        if(topicLength > 50) {
            $('#topicNum').text(50); //长度大于50时0处显示的也只是50
            $('#topic').val($('#topic').val().substring(0, 50)); //长度大于50时截取前50个字符
        }
    });

    // 实时显示正文字符数
    // $('#content').on("keydown", function() {
    //     let contentLength = tinymce.activeEditor.getContent().length;
    //     $('#contentNum').text(contentLength); //在键盘按下时，实时的显示字数
    //     if(contentLength > 5000) {
    //         $('#contentNum').text(5000); //长度大于5000时0处显示的也只是5000
    //         $('#content').val($('#content').val().substring(0, 5000)); //长度大于5000时截取前5000个字符
    //     }
    // });

    $("#timingPublishBtn").click(TimingPublish);
    $("#publishBtn").click(Publish);
    $("#saveBtn").click(Save);
    $("#abandonBtn").click(Abandon);
});

// 获取系统当前时间 格式：yyyy-MM-dd HH:mm:ss
function getNowFormatDate() {
    var now = new Date();
    var year = now.getFullYear();   //年
    var month = now.getMonth() + 1; //月
    var day = now.getDate();        //日
    var hh = now.getHours();        //时
    var mm = now.getMinutes();      //分
    var ss = now.getSeconds();      //秒
    var clock = year + "-";

    if (month < 10)
        clock += "0";
    clock += month + "-";
    if (day < 10)
        clock += "0";
    clock += day + " ";
    if (hh < 10)
        clock += "0";
    clock += hh + ":";
    if (mm < 10) clock += '0';
    clock += mm + ":";
    if (ss < 10) clock += '0';
    clock += ss;
    return (clock);
}

// 将时间转换成时间戳
function getTimestamp(timestr) {
    var time = timestr.replace(/-/g, '/');
    var date = new Date(time);
    return date.getTime();
}

// 新建一个公告，并定时发布
function TimingPublish() {
    let time = $("#time").val();
    var topic = $("#topic").val();
    var content = tinymce.activeEditor.getContent();

    console.log("主题：" + topic + "正文：" + content);
    console.log("主题大小：" + topic.length + "正文大小：" + content.length);

    if (time == "") {
        alert("请选择定时发布的时间！");
        return false;
    }
    if (topic == null || topic == "" || topic.length > 50) {
        alert("请输入需要发布内容的主题，且长度不超过50！！！");
        return false;
    }
    if (content == null || content == "" || content.length > 5000) {
        alert("请输入需要发布的内容，且长度不超过5000！！！");
        return false;
    }

    // let timeDifference = getTimestamp(time) - getTimestamp(getNowFormatDate());
    // let shortTimeDifference = timeDifference/(1000*60*5);
    // console.log(shortTimeDifference);
    // if (shortTimeDifference > 1) {
    //     console.log(timeDifference + "   当前时间：" + getNowFormatDate() + " < 发布时间：" + time);
    // } else {
    //     console.log(timeDifference + "   当前时间：" + getNowFormatDate() + " > 发布时间：" + time);
    //     alert("请将发布时间设置到5分钟后！");
    //     return false;
    // }

    $.post(
        CONTEXT_PATH + "/notice/timingDraftNotice",
        {"topic": topic, "content": content, "timeRange": timeRange, "state": 3, "auditstatus": 0, "time": time},
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

// 新建一个公告，并发布
function Publish() {
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
        CONTEXT_PATH + "/notice/draftNotice",
        {"topic": topic, "content": content, "timeRange": timeRange, "state": 1, "auditstatus": 0},
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

// 新建一个公告，并保存
function Save() {
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
        CONTEXT_PATH + "/notice/draftNotice",
        {"topic": topic, "content": content, "timeRange": timeRange, "state": 2, "auditstatus": 0},
        function (data) {
            $("#hintBody").text("保存成功!");
            $("#hintModal").modal("show");
            setTimeout(function () {
                $("#hintModal").modal("hide");
                if (data == "0") {
                    window.location.href = CONTEXT_PATH + "/notice/draftsNotice";
                }
            }, 2000);
        }
    );
}

// 放弃发布或保存公告，并返回首页
function Abandon() {
    location.href = CONTEXT_PATH + "/notice/noticeIndex";
}