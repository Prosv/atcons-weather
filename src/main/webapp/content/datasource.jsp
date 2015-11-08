<script type="text/javascript" src="/weather/js/actions-ds.js"></script>
<div id="datasource-content">
    <script type="text/javascript">
        launchAutoUpdate();
    </script>
    <div id="datasource-actions">
        <div id="datasource-buttons">
            <button type="button" class="btn btn-default action-button" id="add-ds-btn"
                    data-toggle="modal" data-target="#addDialog">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add
            </button>
            <br/>
            <button type="button" class="btn btn-default action-button" id="upd-ds-btn" onclick="listAllDataSources()">
                <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span> Update
            </button>
            <br/>
            <button type="button" class="btn btn-default action-button" id="rmv-ds-btn"
                    data-toggle="modal" data-target="#removeDialog" onclick="constructBodyForRemoveModal();">
                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Remove
            </button>
            <br/>
        </div>


        <div id="ds-data-link-div">
            <a href="/weather/data/" class="btn btn-lg btn-outline" id="ds-data-link">
                <div style="margin-top: 40%">
                    <span class="glyphicon glyphicon-chevron-left"></span> Data
                </div>
            </a>
        </div>

    </div>
    <div id="datasource-table">
        <table id="datasources-table" data-toggle="table" data-height="299"
               class="table table-responsive table-bordered" data-click-to-select="true">
            <thead>
            <tr>
                <th data-field="state" data-checkbox="true" width="25px"></th>
                <th data-field="id" width="100px">Data source ID</th>
                <th data-field="url">URL</th>
                <th data-field="type" width="80px">Data type</th>
            </tr>
            </thead>
            <tbody id="ds-tbody">

            </tbody>
        </table>
    </div>
</div>


<div class="modal fade" id="addDialog"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Add new data source</h4>
            </div>
            <div class="modal-body">

                <div class="form-group">
                    <label for="url-input">Resource URL:</label>
                    <input type="text" class="form-control" id="url-input" placeholder="http://example.com">
                </div>
                <div class="container">
                    <form role="form" id="data-type-button">
                        <label class="radio-inline">
                            <input type="radio" name="optradio" value="json" checked="">json
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="optradio" value="xml">xml
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="optradio" value="html">html
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="optradio" value="text">text
                        </label>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary" onclick="addNewDataSource()" data-dismiss="modal">Add source</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="removeDialog"
     tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" onload="">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Confirm remove data sources</h4>
            </div>
            <div class="modal-body" id="remove-modal-body">


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-danger" onclick="removeDataSources()" id="btn-ds-rmv-modal"
                        data-dismiss="modal">Remove sources
                </button>
            </div>
        </div>
    </div>
</div>
