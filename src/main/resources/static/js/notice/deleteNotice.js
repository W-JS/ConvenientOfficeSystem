// 删除公告时点击本行选中复选框
// 当文档结构完全加载完毕再去执行函数中的代码
$(function () {
    // 为li元素注册click时间处理函数
    $("#items li").click(function () {
        // 判断当前li下的复选框是否被选中
        if ($(this).find(":checkbox").prop("checked")) {
            // 如果被选中，那么就取消选中
            $(this).find(":checkbox").prop("checked", false);
        } else {
            // 如果没有被选中，那么就将其选中
            $(this).find(":checkbox").prop("checked", true);
        }
        //阻止点击复选框的冒泡效果，否则点击当前复选框选中或者取消选中之后，事件又会冒泡的li元素，触发注册在li元素上的click事件处理函数，导致无法成功选中或者取消选中
        $("#items li input").click(function (ev) {
            ev.stopPropagation();
        })
    })
});

$(function () {
    $("#allSelect").click(AllSelect);
    $("#otherSelect").click(OtherSelect);
    $("#deleteBtn").click(Delete);
});

// 复选框全选
function AllSelect() {
    var allSelect = document.getElementById("allSelect");
    var select = document.getElementsByName("select");
    if (allSelect.checked == false) {
        for (var i = 0; i < select.length; i++) {
            select[i].checked = false;
        }
    } else {
        for (var i = 0; i < select.length; i++) {
            select[i].checked = true;
        }
    }
}

// 复选框反选
function OtherSelect() {
    var select = document.getElementsByName("select");
    for (var i = 0; i < select.length; i++) {
        if (select[i].checked == false)
            select[i].checked = true;
        else
            select[i].checked = false;
    }
}

// 删除一个或多个公告
function Delete() {
    var Checkbox = false;
    var position = $("#deleteBtn").val();
    var current = $("#deleteNotice").val();
    $("input[name='select']").each(function () {
        if (this.checked == true) {
            Checkbox = true;
        }
    });
    if (Checkbox) {
        var t = confirm("您确认要删除选中的内容吗？");
        if (t == false) {
            return false;
        } else {
            var checkedID = "";
            $("input[name='select']").each(function () {
                if (this.checked == true) {
                    checkedID += this.value + ",";
                }
            });
            console.log(checkedID);
            $.post(
                CONTEXT_PATH + "/notice/delete",
                {"state": 0, "auditstatus": 0, "checkedID": checkedID},
                function (data) {
                    if (data == "OK") {
                        if (position == "draft") {
                            location.href = CONTEXT_PATH + "/notice/deleteDraftNotice" + "?current=" + current;
                        } else {
                            location.href = CONTEXT_PATH + "/notice/deleteSaveNotice" + "?current=" + current;
                        }
                    }
                }
            );
        }
    } else {
        alert("请选择您要删除的内容!");
        return false;
    }
}