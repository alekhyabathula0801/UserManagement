const monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
    "November", "December"];
const date = new Date();

var ageChart = null;
function getAgeChart(ageLabel,ageValues) {
    let dashboardAgeChart = document.getElementById("dashboard-age-chart").getContext('2d');
    ageChart = new Chart(dashboardAgeChart, {
        type: 'horizontalBar',
        data: {
            labels: ageLabel,
            datasets: [{
                label: ' Users',
                data: ageValues,
                backgroundColor: 'rgba(245,165,35,0.6)',
            }]
        },
        options: {
            responsive: true,
            legend: {
                display: false
            },
            scales: {
                xAxes: [{
                    display: false,
                    ticks: {
                        min: 0
                    }
                }],
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    },
                    barPercentage: 1,
                    gridLines: {
                        display: false,
                        drawBorder: false,
                    }
                }]
            }
        }
    });
}
var registeredUsersChart = null;
function getRegisteredUserChart(registeredUsersLabel,numberOfUsersRegisteredValues) {
    let dashboardAllTimeUsersChart = document.getElementById("dashboard-all-time-users-chart").getContext('2d');
    registeredUsersChart = new Chart(dashboardAllTimeUsersChart, {
        type: 'line',
        data: {
            labels: registeredUsersLabel,
            datasets: [{
                label: ' Users ',
                data: numberOfUsersRegisteredValues,
                fill: false,
                borderWidth: 2,
                pointRadius: 3,
                pointHoverRadius: 5,
                borderColor: '#45aeef',
                backgroundColor: '#fff',
            }]
        },
        options: {
            responsive: true,
            legend: {
                display: false,
            },
            scales: {
                xAxes: [{
                    display: true,
                    scaleLabel: {
                        display: false,
                        labelString: ''
                    },
                    gridLines: {
                        display: false
                    }
                }],
                yAxes: [{
                    type: "linear",
                    display: true,
                    stacked: true,
                    scaleLabel: {
                        display: false,
                        labelString: ''
                    },
                    gridLines: {
                        color: '#eff3f6',
                        drawBorder: false
                    },
                    label: {
                        show: true
                    },
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
}

$(document).ready(function () {
    document.getElementById("dashboard-current-month-registered-users").innerText = monthNames[date.getMonth()];
    loadTopLocations(0,3);
    loadRegisteredUsersChart(0);
    loadAgeChart(0);
    loadGenderRatio(0);
});

$(document).on("click", "#dashboard-all-time-registered-users", function() {
    loadTopLocations(0,3);
    loadRegisteredUsersChart(0);
    loadAgeChart(0);
    loadGenderRatio(0);
    $("#dashboard-all-locations").on("click", function () {
        $(this).attr("href", "TopLocations?numberOfCountries=10&userChoice=0&activePageId=1");
    });
});

$(document).on("click", "#dashboard-current-year-registered-users", function() {
    loadTopLocations(1,3);
    loadRegisteredUsersChart(1);
    loadAgeChart(1);
    loadGenderRatio(1);
    $("#dashboard-all-locations").on("click", function () {
        $(this).attr("href", "TopLocations?numberOfCountries=10&userChoice=1&activePageId=1");
    });
});

$(document).on("click", "#dashboard-current-month-registered-users", function() {
    loadTopLocations(2,3);
    loadRegisteredUsersChart(2);
    loadAgeChart(2);
    loadGenderRatio(2);
    $("#dashboard-all-locations").on("click", function () {
        $(this).attr("href", "TopLocations?numberOfCountries=10&userChoice=2&activePageId=1");
    });
});

function loadRegisteredUsersChart(userChoice) {
    $.get("NumberOfRegisteredUsers?userChoice="+userChoice, function(response) {
        let label = [];
        let values = [];
        $.each(response, function (key,value){
            label.push(key);
            values.push(value);
        })
        if(registeredUsersChart !== null) {
            registeredUsersChart.destroy();
        }
        if(label.length === 0)
            document.getElementById("dashboard-registered-users-no-data-message").style.display = "block";
        else {
            document.getElementById("dashboard-registered-users-no-data-message").style.display = "none";
            getRegisteredUserChart(label, values);
        }
    });
}

function loadAgeChart(userChoice) {
    $.get("Age?userChoice="+userChoice, function (response){
        let label = [];
        let values = [];
        $.each(response, function (key,value){
            label.push(key);
            values.push(value);
        })
        if(ageChart !== null) {
            ageChart.destroy();
        }
        if(label.length === 0)
            document.getElementById("dashboard-age-chart-no-data-message").style.display = "block";
        else {
            document.getElementById("dashboard-age-chart-no-data-message").style.display = "none";
            getAgeChart(label, values);
        }
    });
}

function loadGenderRatio(userChoice) {
    $.get("Gender?userChoice="+userChoice, function (response){
        if(response.length === 0) {
            document.getElementById("dashboard-gender-no-data-message").style.display = "block";
            let genderBlocks = document.getElementsByClassName("dashboard-main-users-gender-ratio-content");
            for(let block of genderBlocks)
                block.style.display = "none";
        }
        else {
            document.getElementById("dashboard-gender-no-data-message").style.display = "none";
            let genderBlocks = document.getElementsByClassName("dashboard-main-users-gender-ratio-content");
            for(let block of genderBlocks)
                block.style.display = "block";
            setGenderRatio(response[0], response[1]);
        }
    });
}

function setGenderRatio(femaleRatio, maleRatio) {
    document.getElementById("dashboard-male-ratio-span-value").innerText = maleRatio+"%";
    document.getElementById("dashboard-male-ratio-style-width").style.width = maleRatio+"%";
    document.getElementById("dashboard-female-ratio-span-value").innerText = femaleRatio+"%";
    document.getElementById("dashboard-female-ratio-style-width").style.width = femaleRatio+"%";
}

function loadTopLocations(userChoice,numberOfUsers) {
    $.post("TopLocations?userChoice="+userChoice+"&numberOfUsers="+numberOfUsers, function (response){
        setTopLocations(response);
    });
}

function setTopLocations(response) {
    $("#dashboard-top-location tr>td").remove();
    var $tbody = $("#dashboard-top-location");
    if(response.length === 0) {
        $("<tr>").appendTo($tbody)
            .append($("<td colspan=\"3\" class=\"dashboard-main-users-no-data-message\">").text("No Data Available"))
    } else {
        $.each(response, function (index, country) {
            console.log(index);
            console.log(country);
            $("<tr>").appendTo($tbody)
                .append($("<td>").text(index + 1))
                .append($("<td>").text(country.country))
                .append($("<td>").text(country.numberOfUsers));
        })
    }
}

