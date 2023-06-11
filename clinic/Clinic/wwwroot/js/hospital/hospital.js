var dataTable;

$(document).ready(function () {
    loadDataTable();
});

function loadDataTable() {
    dataTable = $('#tblData').DataTable({
        "ajax": {
            "url": "/Admin/Hospital/GetAll"
        },
        "columns": [
            { "data": "name", "width": "20%" },
            {
                "data": "id",
                "render": function (data) {
                    return `
                            <div>
                                <a href="/Admin/HospitalDepartment/Index/${data}" class="btn btn-success text-white" style="cursor:pointer">
                                    Departments
                                </a>
                            </div>
                           `;
                }, "width": "20%"
            },
            {
                "data": "id",
                "render": function (data) {
                    return `
                            <div>
                                <a href="/Admin/HospitalLab/Index/${data}" class="btn btn-success text-white" style="cursor:pointer">
                                    Labs
                                </a>
                            </div>
                           `;
                }, "width": "20%"
            },
            {
                "data": "id",
                "render": function (data) {
                    return `
                            <div>
                                <a href="/Admin/HospitalDoctor/Index/${data}" class="btn btn-success text-white" style="cursor:pointer">
                                    Doctors
                                </a>
                            </div>
                           `;
                }, "width": "20%"
            },
            {
                "data": "id",
                "render": function (data) {
                    return `
                            <div>
                                <a href="/Admin/Hospital/Upsert/${data}" class="btn btn-success text-white" style="cursor:pointer">
                                    <i class="far fa-edit"></i>
                                </a>
                                <a onClick=Delete("/Admin/Hospital/Delete/${data}") class="btn btn-danger text-white" style="cursor:pointer">
                                    <i class="fas fa-trash"></i>
                                </a>
                            </div>
                           `;
                }, "width": "20%"
            }
        ]
    });
}

function Delete(url) {
    swal({
        title: "Are you sure you want to Delete ",
        text: "You will not be able to restore the data!",
        icon: "warning",
        buttons: true,
        dangerMode: true
    }).then((willDelete) => {
        if (willDelete) {
            $.ajax({
                type: "DELETE",
                url: url,
                success: function (data) {
                    if (data.success) {
                        toastr.success(data.message);
                        dataTable.ajax.reload();
                    } else {
                        toastr.error(data.message);
                    }
                }
            })
        }
    });
}