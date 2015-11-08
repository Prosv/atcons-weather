function addNewDataSource() {
    var source_url = $("#url-input").value;
    var source_type = $("input[name=optradio]:checked").val();

    RegExp.url = '(?:https?://)?(?:[\\-\\w]+:[\\-\\w]+@)?(?:[0-9a-z][\\-0-9a-z]*[0-9a-z]\\.)+[a-z]{2,6}(?::\\d{1,5})?(?:[?/\\\\#][?!^$.(){}:|=[\\]+\\-/\\\\*;&~#@,%\\wА-Яа-я]*)?';

    if (source_url == "" || source_url == null || source_type == "" || source_type == null
        || !source_url.match(RegExp.url)) {
        alert ("Incorrect URL or data type");
    }
    else {
        $.post("/weather/rest/datasources/add", {url: source_url, type: source_type}, function (returned_data) {

        }).done(function () {
            alert("New datasource added");
        }).error(function () {
            alert("ERROR");
        });
    }


}

function constructBodyForRemoveModal() {
    var removebody = $("#remove-modal-body");
    var removeSize = $('#ds-tbody').find('input[type="checkbox"]:checked').size();
    if (removeSize == 0) {
        removebody.html("You have not chosen any data sources for remove");
        $('#btn-ds-rmv-modal').prop('disabled', true);
    } else {
        removebody.html("Are you sure you want to remove " + removeSize + " data sources?");
        $('#btn-ds-rmv-modal').prop('disabled', false);
    }
}

function removeDataSources() {

    $('#ds-tbody').find('tr').filter(':has(:checkbox:checked)').each(function () {
        var id = $(this).find("td:nth-child(2)").html();
        console.log("Remove datasource with id=" + id);
        $.post("/weather/rest/datasources/" + id + "/remove");
    });
}

function listAllDataSources() {
    $.getJSON("/weather/rest/datasources/list").done(function (datasources) {

        var table = document.getElementById("ds-tbody");
        table.innerHTML = "";

        $.each(datasources, function (index, value) {
            var row = table.insertRow(index);

            var cell_checkbox = row.insertCell(0);
            var cell_id = row.insertCell(1);
            var cell_url = row.insertCell(2);
            var cell_type = row.insertCell(3);
            var value_url_content = (value.url.length < 100) ? value.url : value.url.substring(0, 97) + "...";

            cell_checkbox.innerHTML = "<div class=\"checkbox\"><label><input type=\"checkbox\"></label></div>";
            cell_id.innerHTML = value.id;
            cell_url.innerHTML = "<a href=\"" + value.url + "\" target=\"_newtab\">"
                + value_url_content + "</a>";
            cell_type.innerHTML = value.type;
        })
    });

}

function launchAutoUpdate() {
    listAllDataSources();
    setInterval(function () {
        listAllDataSources();
    }, 60000);
}