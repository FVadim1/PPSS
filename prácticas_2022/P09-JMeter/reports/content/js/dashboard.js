/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 100.0, "KoPercent": 0.0};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [1.0, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [1.0, 500, 1500, "Reptiles"], "isController": false}, {"data": [1.0, 500, 1500, "/jpetstore/actions/Account.action-98"], "isController": false}, {"data": [1.0, 500, 1500, "/jpetstore/actions/Account.action-101"], "isController": false}, {"data": [1.0, 500, 1500, "Main Catalog"], "isController": false}, {"data": [1.0, 500, 1500, "Birds"], "isController": false}, {"data": [1.0, 500, 1500, "/jpetstore/actions/Account.action-99"], "isController": false}, {"data": [1.0, 500, 1500, "Login"], "isController": false}, {"data": [1.0, 500, 1500, "Login-0"], "isController": false}, {"data": [1.0, 500, 1500, "Login-1"], "isController": false}, {"data": [1.0, 500, 1500, "Home-0"], "isController": false}, {"data": [1.0, 500, 1500, "/jpetstore/actions/Catalog.action-97"], "isController": false}, {"data": [1.0, 500, 1500, "Home-1"], "isController": false}, {"data": [1.0, 500, 1500, "Dogs"], "isController": false}, {"data": [1.0, 500, 1500, "Sign in"], "isController": false}, {"data": [1.0, 500, 1500, "/jpetstore/actions/Account.action-101-1"], "isController": false}, {"data": [1.0, 500, 1500, "Sign Out"], "isController": false}, {"data": [1.0, 500, 1500, "Sign Out-0"], "isController": false}, {"data": [1.0, 500, 1500, "/jpetstore/actions/Account.action-99-1"], "isController": false}, {"data": [1.0, 500, 1500, "/jpetstore/actions/Account.action-101-0"], "isController": false}, {"data": [1.0, 500, 1500, "Home"], "isController": false}, {"data": [1.0, 500, 1500, "Sign Out-1"], "isController": false}, {"data": [1.0, 500, 1500, "/jpetstore/actions/Account.action-99-0"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 500, 0, 0.0, 1.1339999999999986, 0, 3, 1.0, 2.0, 2.0, 3.0, 19.530487090348032, 64.33426367622359, 18.268443798582087], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Reptiles", 8, 0, 0.0, 1.25, 1, 2, 1.0, 2.0, 2.0, 2.0, 0.9878982464806124, 3.58113114349222, 0.24311558409483822], "isController": false}, {"data": ["/jpetstore/actions/Account.action-98", 25, 0, 0.0, 1.2000000000000004, 0, 2, 1.0, 2.0, 2.0, 2.0, 2.609058651638489, 9.996261708150698, 2.1759141489250675], "isController": false}, {"data": ["/jpetstore/actions/Account.action-101", 25, 0, 0.0, 1.6799999999999997, 1, 2, 2.0, 2.0, 2.0, 2.0, 2.6041666666666665, 13.364156087239584, 4.305521647135417], "isController": false}, {"data": ["Main Catalog", 25, 0, 0.0, 1.1600000000000001, 1, 2, 1.0, 2.0, 2.0, 2.0, 2.592823065753993, 12.234883841526655, 0.5519877229827836], "isController": false}, {"data": ["Birds", 8, 0, 0.0, 1.375, 1, 2, 1.0, 2.0, 2.0, 2.0, 0.9556803249313105, 3.4624746147413688, 0.23238711026161749], "isController": false}, {"data": ["/jpetstore/actions/Account.action-99", 25, 0, 0.0, 1.8399999999999996, 1, 3, 2.0, 3.0, 3.0, 3.0, 2.6235701542659253, 13.48166616381572, 5.572524497586316], "isController": false}, {"data": ["Login", 25, 0, 0.0, 2.08, 1, 3, 2.0, 3.0, 3.0, 3.0, 2.6164311878597593, 13.444981357927787, 5.557361165620095], "isController": false}, {"data": ["Login-0", 25, 0, 0.0, 1.2800000000000002, 1, 2, 1.0, 2.0, 2.0, 2.0, 2.616705045007327, 0.4931875719593887, 3.1047818649256853], "isController": false}, {"data": ["Login-1", 25, 0, 0.0, 0.7599999999999998, 0, 1, 1.0, 1.0, 1.0, 1.0, 2.616978959489166, 12.954556978174395, 2.4534177745210926], "isController": false}, {"data": ["Home-0", 25, 0, 0.0, 0.44000000000000006, 0, 1, 0.0, 1.0, 1.0, 1.0, 2.5949761262196387, 0.36998683049615944, 0.49416049278596635], "isController": false}, {"data": ["/jpetstore/actions/Catalog.action-97", 25, 0, 0.0, 1.6399999999999995, 1, 2, 2.0, 2.0, 2.0, 2.0, 2.6036242449489686, 14.650471581441366, 1.8484715098416995], "isController": false}, {"data": ["Home-1", 25, 0, 0.0, 0.4000000000000001, 0, 1, 0.0, 1.0, 1.0, 1.0, 2.5947067981318113, 3.4714338998443175, 0.49664309807991697], "isController": false}, {"data": ["Dogs", 9, 0, 0.0, 1.4444444444444444, 1, 3, 1.0, 3.0, 3.0, 3.0, 0.943594044873139, 3.958671891381841, 0.22852668274271334], "isController": false}, {"data": ["Sign in", 25, 0, 0.0, 1.1600000000000006, 0, 2, 1.0, 2.0, 2.0, 2.0, 2.614789247986612, 10.01760488573371, 2.180693376738835], "isController": false}, {"data": ["/jpetstore/actions/Account.action-101-1", 25, 0, 0.0, 0.76, 0, 1, 1.0, 1.0, 1.0, 1.0, 2.6044379622877383, 12.518597314824461, 2.1415398088342537], "isController": false}, {"data": ["Sign Out", 25, 0, 0.0, 1.7599999999999996, 1, 3, 2.0, 2.0, 2.6999999999999993, 3.0, 2.6175269605276936, 13.43271892341116, 4.327610492356821], "isController": false}, {"data": ["Sign Out-0", 25, 0, 0.0, 0.96, 0, 2, 1.0, 1.4000000000000021, 2.0, 2.0, 2.6175269605276936, 0.8512074979059784, 2.1753080502041673], "isController": false}, {"data": ["/jpetstore/actions/Account.action-99-1", 25, 0, 0.0, 0.7199999999999999, 0, 1, 1.0, 1.0, 1.0, 1.0, 2.6238455079764904, 12.988547734309403, 2.4598551637279598], "isController": false}, {"data": ["/jpetstore/actions/Account.action-101-0", 25, 0, 0.0, 0.8799999999999999, 0, 1, 1.0, 1.0, 1.0, 1.0, 2.6044379622877383, 0.8469510170330242, 2.164430376862173], "isController": false}, {"data": ["Home", 25, 0, 0.0, 0.8799999999999998, 0, 2, 1.0, 1.0, 1.6999999999999993, 2.0, 2.5947067981318113, 3.841382330046705, 0.9907523028022833], "isController": false}, {"data": ["Sign Out-1", 25, 0, 0.0, 0.6000000000000001, 0, 1, 1.0, 1.0, 1.0, 1.0, 2.617801047120419, 12.582828861256544, 2.1525278141361257], "isController": false}, {"data": ["/jpetstore/actions/Account.action-99-0", 25, 0, 0.0, 1.12, 0, 2, 1.0, 2.0, 2.0, 2.0, 2.6235701542659253, 0.4944814841536363, 3.112927477962011], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": []}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 500, 0, null, null, null, null, null, null, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
