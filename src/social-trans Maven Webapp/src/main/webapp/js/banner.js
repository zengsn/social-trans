/**
 * 
 */
$(function() {
    var index = 0
    var timer = null
    $(".next").click(function() {

        var len = $(".box .scrool ul li").length
        index++
        if (index > len - 1) {
            index = 0
        }
        tab()
    })
    $(".nav a").click(function() {
        clearInterval(timer)
        index = $(this).index()
        tab()
        fnTab()
    })

    function tab() {
        var w = $(".box .scrool ul li").width()
        $(".nav a").eq(index).addClass("active").siblings().removeClass("active")
        $(".scrool").animate({
            "marginLeft": -w * index
        })
    }
    //定时器
    function fnTab() {
        timer = setInterval(function() {
            index++
            var len = $(".box .scrool ul li").length
            if (index > len - 1) {
                index = 0
            }
            tab()
        }, 3000)
    }
    fnTab()


})