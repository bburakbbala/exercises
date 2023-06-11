var dataTable;

$(document).ready(function () {
    loadDataTable();
});

function loadDataTable() {
    dataTable = $('#tblData').DataTable({
        "ajax": {
            "url": "/Admin/Lab/GetAll"
        },
        "columns": [
            { "data": "name", "width": "18%" },
            { "data": "addressDetail", "width": "18%" },
            { "data": "province.name", "width": "18%" },
            { "data": "city.name", "width": "18%" },
            { "data": "countryOrRegion.name", "width": "18%" },
            {
                "data": "id",
                "render": function (data) {
                    return `
                            <div>
                                <a href="/Admin/Lab/Upsert/${data}" class="btn btn-success text-white" style="cursor:pointer">
                                    <i class="far fa-edit"></i>
                                </a>
                                <a onClick=Delete("/Admin/Lab/Delete/${data}") class="btn btn-danger text-white" style="cursor:pointer">
                                    <i class="fas fa-trash"></i>
                                </a>
                            </div>
                           `;2
                }, "width": "10%"
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