let timeRange = "threeDays";

$(function () {
    $("#threeDaysLabel").click(ThreeDays);
    $("#oneWeekLabel").click(OneWeek);
    $("#oneMonthLabel").click(OneMonth);
    $("#oneYearLabel").click(OneYear);
});

// 公告有效期为三天
function ThreeDays() {
    timeRange = $("#threeDays").val();
    $("#defaultTimeBtn").text($("#threeDaysLabel").text());
}

// 公告有效期为一周
function OneWeek() {
    timeRange = $("#oneWeek").val();
    $("#defaultTimeBtn").text($("#oneWeekLabel").text());
}

// 公告有效期为一个月
function OneMonth() {
    timeRange = $("#oneMonth").val();
    $("#defaultTimeBtn").text($("#oneMonthLabel").text());
}

// 公告有效期为一年
function OneYear() {
    timeRange = $("#oneYear").val();
    $("#defaultTimeBtn").text($("#oneYearLabel").text());
}