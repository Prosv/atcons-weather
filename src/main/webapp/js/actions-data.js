function listAllData() {
    $.getJSON("/weather/rest/data/list").done(function (data) {

        var table = document.getElementById("data-tbody");
        table.innerHTML = "";

        $.each(data, function (index, value) {
            var row = table.insertRow(index);

            var cell_checkbox = row.insertCell(0);
            var cell_id = row.insertCell(1);
            var cell_DATA = row.insertCell(2);
            var cell_src = row.insertCell(3);
            var cell_upd = row.insertCell(4);

            value.data = value.data.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\"/g, "&quot;");

            var value_data_content = (value.data.length < 180) ? value.data : value.data.substring(0, 177) + "...";

            var data = "" + value.data + "";
            cell_checkbox.innerHTML = "<div class=\"checkbox\"><label><input type=\"checkbox\"></label></div>";
            cell_id.innerHTML = value.id;
            cell_DATA.innerHTML = "<p class=\"disabled\" id=\"data-view-table-" + value.id + "\">"
                + value_data_content + "</p>";
            document.getElementById("data-view-table-" + value.id).onclick = function() {
                constructBodyModalViewData(data)
            };
            cell_src.innerHTML = value.source.id;
            cell_upd.innerHTML = new Date(value.last_update).toUTCString();

        })
    });

}

function updateData() {
    $('#data-tbody').find('tr').filter(':has(:checkbox:checked)').each(function () {
        var row = $(this);
        var id = row.find("td:nth-child(2)").html();
        $.getJSON("/weather/rest/data/" + id + "/update").done(function (value) {

            value.data = value.data.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\"/g, "&quot;");

            var value_data_content = (value.data.length < 180) ? value.data : value.data.substring(0, 177) + "...";

            var data = "" + value.data + "";
            row.find("td:nth-child(1)").html("<div class=\"checkbox\"><label><input type=\"checkbox\"></label></div>");
            row.find("td:nth-child(3)").html("<p class=\"disabled\" id=\"data-view-table-" + value.id + "\">"
                + value_data_content + "</p>");
            document.getElementById("data-view-table-" + value.id).onclick = function() {
                constructBodyModalViewData(data)
            };
            row.find("td:nth-child(5)").html(new Date(value.last_update).toUTCString());

        });
    });
}

function updateAllData() {
    $.post("/weather/rest/data/update");
    listAllData();
}

function launchAutoUpdate() {
    listAllData();
    setInterval(function() {
        listAllData();
    }, 60000);
}

function constructBodyModalViewData(data) {
    var viewDataBody = document.getElementById("viewdata-modal-body");
    viewDataBody.innerHTML = data;
    $("#dataViewDialog").modal("show");
}