<script type="text/javascript" src="/weather/js/actions-data.js"></script>
<div id="data-content">
    <script type="text/javascript">
        launchAutoUpdate();
    </script>
    <div id="data-actions">
        <div id="data-buttons">
            <br/>
            <button type="button" class="btn btn-default action-button" onclick="updateData()">
                <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>    Update
            </button>
            <br/>
            <button type="button" class="btn btn-default action-button" onclick="updateAllData()">
                <span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span>    Update all
            </button>
            <br/>
        </div>

        <div id="data-ds-link-div">
            <a href="/weather/datasource/" class="btn btn-lg btn-outline" id="data-ds-link">
                <div style="margin-top: 40%">
                    <span class="glyphicon glyphicon-chevron-left"></span> Data sources
                </div>
            </a>
        </div>

    </div>

    <div id="data1-table">
        <table id="dataentries-table" data-toggle="table" data-height="299"
               class="table table-responsive table-bordered" data-click-to-select="true">
            <thead>
            <tr>
                <th data-field="checkbox" data-checkbox="true" width="20px"></th>
                <th data-field="id" width="70px">Entry ID</th>
                <th data-field="data">Data</th>
                <th data-field="source" width="70px">Source ID</th>
                <th data-field="last-upd" width="200px">Last updated</th>
            </tr>
            </thead>
            <tbody id="data-tbody">

            </tbody>
        </table>
    </div>
</div>

<div class="modal fade" id="dataViewDialog"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">View data</h4>
            </div>
            <div class="modal-body" id="viewdata-modal-body">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
