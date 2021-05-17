/**
 * Created by lex on 2017/7/27.
 */
$(document).ready(function () {
    function queryTaskStatus() {
        console.log('每3秒请求一次')
        // $.ajax({
        //     type: "GET",
        //     async: false,
        //     url: "",
        //     data: {session_id: "", url: "", cookie: "", uuid: ""},
        //     dataType: "json",
        //     success: function (data) {
        //         if (true) {
        //             //跳转页面
        //         }
        //     }
        // });
    }

    setInterval(queryTaskStatus, 3000);
});