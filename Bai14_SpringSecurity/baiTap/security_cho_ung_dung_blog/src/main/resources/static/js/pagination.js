let number = 1;
let ulrAPI = "http://localhost:8080/blog?page="
$(document).ready(function(){
    $('[data-toggle="popover"]').popover({
        placement : 'bottom',
        trigger : 'hover'
    });
    $("#nextPage").click(function () {
        $.ajax({
            url: ulrAPI + number,
            // data: {
            //     page: number,
            // },
            type: "GET",
            dataType: "json",
            success: function (response) {
                console.log(response)
                if (response.last === true){
                    $("#nextPage").hide();
                }
                $.each(response.content, function (i, blog) {
                    let output = "<div class='progress' style='height: 1px;' >\n" +
                        "<div class='progress-bar' role='progressbar' style='width: 100%; background-color: #eee' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100'></div>\n" +
                        "</div>\n"+
                        "<div class=\"row shadow rounded-lg p-3 card mb-4 mt-4\">\n" +
                        "                    <div class='col-12 d-flex p-0'>\n" +
                        "                        <img src='" + blog.category.img + "' alt='image' class='mr-3' style='height: 180px; width: 180px;'>\n" +
                        "                        <div class='w-100'>\n" +
                        "                            <ul class='list-group h-100'>\n" +
                        "                                <li class='d-flex'>\n" +
                        "                                    <a href='/" + blog.category.urlname + "' class='category-a d-block'> " + blog.category.name + "</a>\n" +
                        "                                </li>\n" +
                        "                                <li class='d-flex'>\n" +
                        "                                    <a href='/view/" + blog.category.urlname + "/" + blog.urltitle + "' class='title-a d-block'>" + blog.title + "</a>\n" +
                        "                                </li>\n" +
                        "                                <li class='d-flex'>\n" +
                        "                                    <div class='datetime-p'> " + blog.dateDisplay + "</div>\n" +
                        "                                </li>\n" +
                        "                                <li>\n" +
                        "                                    <span class='m-0 p-0 text-break text-secondary content-span'> " + blog.headContent + "</span>\n" +
                        "                                </li>\n" +
                        "                                <li class='mb-0 mt-auto d-flex'>\n" +
                        "                                    <a href='/view/" + blog.category.urlname + "/" + blog.urltitle + "' class='text-decoration-none btn btn-primary pt-1 pb-1 ml-0'>Chi tiết....</a>\n" +
                        "                                    <a href='/edit-blog/ " + blog.id + "' class='btn btn-warning pt-1 pb-1 ml-auto mr-1'>Chỉnh sửa</a>\n" +
                        "                                    <button class='btn btn-danger pt-1 pb-1 ml-1 mr-0' th:onclick=\"'javascript:deleteBlog(' + ${ " + blog.id + "} + ');'\">Xoá</button>\n" +
                        "                                </li>\n" +
                        "                            </ul>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                </div>";
                    $(".showBlog").append(output)
                })
            }
        });
        number++;
    });
    $("#searchBlog").click(function () {
        let valueSearch = $("#inputValue").val();
        ulrAPI = "http://localhost:8080/api/search?value=" + valueSearch + "&page=";
        number = 1;
        $(".showBlog").html("");
        $.ajax({
            url: "http://localhost:8080/api/search",
            data: {
                value: valueSearch
            },
            type: "GET",
            dataType: "json",
            success: function (response) {
                // console.log(response);
                if (response.totalPages === 1){
                    $("#nextPage").hide();
                }else {
                    $("#nextPage").show();
                }
                $.each(response.content, function (i, blog) {
                    let output = "";
                    if (i===0){
                        output+="<div class=\"row shadow rounded-lg p-3 card mb-4 mt-4\">\n";
                    }else {
                        output+="<div class=\"row shadow rounded-lg p-3 card mb-4\">\n";
                    }
                    output+= "<div class='col-12 d-flex p-0'>\n" +
                        "                        <img src='" + blog.category.img + "' alt='image' class='mr-3' style='height: 180px; width: 180px;'>\n" +
                        "                        <div class='w-100'>\n" +
                        "                            <ul class='list-group h-100'>\n" +
                        "                                <li class='d-flex'>\n" +
                        "                                    <a href='/" + blog.category.urlname + "' class='category-a d-block'> " + blog.category.name + "</a>\n" +
                        "                                </li>\n" +
                        "                                <li class='d-flex'>\n" +
                        "                                    <a href='/view/" + blog.category.urlname + "/" + blog.urltitle + "' class='title-a d-block'>" + blog.title + "</a>\n" +
                        "                                </li>\n" +
                        "                                <li class='d-flex'>\n" +
                        "                                    <div class='datetime-p'> " + blog.dateDisplay + "</div>\n" +
                        "                                </li>\n" +
                        "                                <li>\n" +
                        "                                    <span class='m-0 p-0 text-break text-secondary content-span'> " + blog.headContent + "</span>\n" +
                        "                                </li>\n" +
                        "                                <li class='mb-0 mt-auto d-flex'>\n" +
                        "                                    <a href='/view/" + blog.category.urlname + "/" + blog.urltitle + "' class='text-decoration-none btn btn-primary pt-1 pb-1 ml-0'>Chi tiết....</a>\n" +
                        "                                    <a href='/edit-blog/ " + blog.id + "' class='btn btn-warning pt-1 pb-1 ml-auto mr-1'>Chỉnh sửa</a>\n" +
                        "                                    <button class='btn btn-danger pt-1 pb-1 ml-1 mr-0' th:onclick=\"'javascript:deleteBlog(' + ${ " + blog.id + "} + ');'\">Xoá</button>\n" +
                        "                                </li>\n" +
                        "                            </ul>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                </div>";
                    if (i !== response.content.length-1){
                        output+="\n<div class='progress' style='height: 1px;' >\n" +
                            "<div class='progress-bar' role='progressbar' style='width: 100%; background-color: #eee' aria-valuenow='100' aria-valuemin='0' aria-valuemax='100'></div>\n" +
                            "</div>\n";
                    }
                    $(".showBlog").append(output)
                })
            }
        });
    })
});