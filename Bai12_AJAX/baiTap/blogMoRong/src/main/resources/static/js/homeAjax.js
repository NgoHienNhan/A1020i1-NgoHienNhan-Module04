$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/home",
        success: function (result) {
            $("#content").html(result)
            $(".viewCategory").click(function (event) {
                $.ajax({
                    type: "GET",
                    url: $(this).attr("href"),
                    success:function (result) {
                        $("#content").html(result)

                        let readLink = $("a:contains('Đọc bài')");
                        $(readLink).click(function (event) {
                            $.ajax({
                                url: $(event.target).attr("href"),
                                type: "GET",
                                success: function (result) {
                                    $("#content").html(result)
                                }
                            })
                            event.preventDefault();
                        })
                    }
                })
                event.preventDefault();
            })

            let readLink = $("a:contains('Đọc bài')");
            $(readLink).click(function (event) {
                $.ajax({
                    url: $(event.target).attr("href"),
                    type: "GET",
                    success: function (result) {
                        $("#content").html(result)
                    }
                })
                event.preventDefault();
            })

            $('.btnDelete').click(function (event) {
                let modalId = $(this).next().val();
                $('#'+modalId).modal('hide')
                $.ajax({
                    url: $(this).next().next().val(),
                    type: "GET",
                    success: function (result) {
                        $("#content").html(result)
                    }
                })
                event.preventDefault();
            })
        }
    });

    $("#btnHome").click(function () {
        $.ajax({
            type: "GET",
            url: "/home",
            success: function (result) {
                $("#content").html(result)
                $(".viewCategory").click(function (event) {
                    $.ajax({
                        type: "GET",
                        url: $(this).attr("href"),
                        success:function (result) {
                            $("#content").html(result)

                            let readLink = $("a:contains('Đọc bài')");
                            $(readLink).click(function (event) {
                                $.ajax({
                                    url: $(event.target).attr("href"),
                                    type: "GET",
                                    success: function (result) {
                                        $("#content").html(result)
                                    }
                                })
                                event.preventDefault();
                            })
                        }
                    })
                    event.preventDefault();
                })

                let readLink = $("a:contains('Đọc bài')");
                $(readLink).click(function (event) {
                    $.ajax({
                        url: $(event.target).attr("href"),
                        type: "GET",
                        success: function (result) {
                            $("#content").html(result)
                        }
                    })
                    event.preventDefault();
                })

                let deleteLink = $("a:contains('Xóa')");
                $(deleteLink).click(function (event) {
                    $.ajax({
                        url: $(event.target).attr("href"),
                        type: "GET",
                        success: function (result) {
                            $("#content").html(result)
                        }
                    })
                    event.preventDefault();
                })
            }
        })
    });
})