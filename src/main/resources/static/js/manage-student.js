var userId;

function editStudent(id) {

    $.ajax({
        url: '/students/getOne/' + id,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (resp) {
            $("#stuId").val(resp.stuId);
            $("#studentName1").val(resp.studentName);
            $("#depName1").val(resp.depName);
            $("#batchName1").val(resp.batchName);
            $("#depId1").val(resp.depId);
            $("#batchId1").val(resp.batchId);
            $("#editModel").modal('show');
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function deleteStudent(id) {
    userId = id;
    $("#deleteModal").modal('show');
}

$(document).ready(function () {

    $.ajax({
        url: '/students/getAll',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        success: function (resp) {

            $.each(resp, function (i, item) {
                var row = `<tr>
                            <td>${item.stuId}</td>
                                 <td>${item.studentName}</td>
                                 <td>${item.depName}</td>
                                 <td>${item.batchName}</td>
                                 <td>${item.semesterName}</td>
                                 <td>${item.courseNames}</td>
                                 <td>
                                 <button type="submit"class="btn btn-success" data-dismiss="modal"onclick="editStudent(${item.stuId})">Edit Info</button>
                                 <button type="submit"class="btn btn-danger" data-dismiss="modal"onclick="deleteStudent(${item.stuId})">Delete</button>
                                  </td>
                        </tr>`;
                $(".table-body").append(row);
            });
        },
        error: function (err) {
            console.log(err);
        }
    });

    $(".form").on('submit', function (e) {
        e.preventDefault();
        var data = JSON.stringify($(this).serializeObject());
        var url = '/students/save';

        $.ajax({
            data: data,
            url: url,
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function (resp) {
                var row = `<tr>
                                 <td>${resp.stuId}</td>
                                 <td>${resp.studentName}</td>
                                 <td>${resp.depName}</td>
                                 <td>${resp.batchName}</td>
                                 <td>${resp.semesterName}</td>
                                 <td>${resp.courseNames}</td>
                                 <td>
                                 <button type="submit"class="btn btn-success" data-dismiss="modal"onclick="editStudent(${resp.stuId})">Edit Info</button>
                                   <button type="submit"class="btn btn-danger" data-dismiss="modal"onclick="deleteStudent(${resp.stuId})">Delete</button>
                                    </td>
                                 </tr>`;
                $(".table-body").append(row);
                $("input").val("");
                $(".all-semester").empty();
                $(".all-course").empty();
                $(".all-dep").empty();
                $(".all-batch").empty();
            },
            error: function (err) {
                console.log(err);
            }
        });
    });


    $(".form1").on('submit', function (e) {

        e.preventDefault();
        var data = JSON.stringify($(this).serializeObject());
        var url = '/students/update';

        $.ajax({
            data: data,
            url: url,
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function (resp) {

                $("#editModel").modal('hide');
                $(".table-body tr").remove();
                $.each(resp, function (i, item) {
                    var row = `<tr>
                            <td>${item.stuId}</td>
                                 <td>${item.studentName}</td>
                                 <td>${item.depName}</td>
                                 <td>${item.batchName}</td>
                                 <td>
                                 <button type="submit"class="btn btn-success" data-dismiss="modal"onclick="editStudent(${item.stuId})">Edit Info</button>
                                    <button type="submit"class="btn btn-danger" data-dismiss="modal"onclick="deleteStudent(${item.stuId})">Delete</button>
                                    </td>
                        </tr>`;
                    $(".table-body").append(row);
                });
            },
            error: function (err) {
                console.log(err);
            }
        });
    });

    $("#depId").change(function () {
        var id1 = $(this).val();
        $.ajax({
            type: 'GET',
            url: "/students/getSemesters/" + id1,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function (data) {
                $(".all-semester").empty();
                $(".all-course").empty();
                var row = `<option value="" disabled selected>Select a semester</option>`;
                $.each(data, function (i, item) {
                    row += `<option value="${item.semesterId}"> ${item.semesterName} </option>`;
                });
                $(".all-semester").append(row);
            },
            error: function () {
                alert("error");
            }
        });
    });

    $("#semesterId").change(function () {
        var id = $(this).val();
        $.ajax({
            type: 'GET',
            url: "/students/getCourses/" + id,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function (data) {
                $(".all-course").empty();
                var row = `<option value="" disabled selected>Select your courses</option>`;
                $.each(data, function (i, item) {
                    row += `<option value="${item.courseId}"> ${item.courseName} </option>`;
                });
                $(".all-course").append(row);
            },
            error: function () {
                alert("error");
            }
        });
    });


    $("#deleteOne1").click(function (event) {
        $.ajax({
            url: '/students/delete/' + userId,
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            success: function (resp) {
                $("#deleteModal").modal('hide');
                alert("User id : " + userId + " delete successfully.");
                $(".table-body tr").remove();
                $.each(resp, function (i, item) {
                    var row = `<tr>
                            <td>${item.stuId}</td>
                                 <td>${item.studentName}</td>
                                 <td>${item.depName}</td>
                                 <td>${item.batchName}</td>
                                 <td>
                                 <button type="submit"class="btn btn-success" data-dismiss="modal"onclick="editStudent(${item.stuId})">Edit Info</button>
                                    <button type="submit"class="btn btn-danger" data-dismiss="modal"onclick="deleteStudent(${item.stuId})">Delete</button>
                                    </td>
                        </tr>`;
                    $(".table-body").append(row);
                });
            },
            error: function (err) {
                console.log(err);
            }
        });
    });


    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
});
