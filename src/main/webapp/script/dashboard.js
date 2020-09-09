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
    loadRegisteredUsersChart(0);
    loadAgeChart(0);
});

$(document).on("click", "#dashboard-all-time-registered-users", function() {
    loadRegisteredUsersChart(0);
    loadAgeChart(0);
});

$(document).on("click", "#dashboard-current-year-registered-users", function() {
    loadRegisteredUsersChart(1);
    loadAgeChart(1);
});

$(document).on("click", "#dashboard-current-month-registered-users", function() {
    loadRegisteredUsersChart(2);
    loadAgeChart(2);
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
        else
            getRegisteredUserChart(label,values);
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
        else
            getAgeChart(label,values);
    });
}