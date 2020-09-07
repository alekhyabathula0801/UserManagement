function getAgeChart(age) {
    let dashboardAgeChart = document.getElementById("dashboard-age-chart").getContext('2d');
    let myChart = new Chart(dashboardAgeChart, {
        type: 'horizontalBar',
        data: {
            labels: ["Under 18", "18-22", "23-27", "28-32", "33-37", "38-42","Over 42"],
            datasets: [{
                label: ' Users',
                data: age,
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

function getRegisteredUserChart(registedUsersLabel,numberOfUsersRegisteredValues) {
    const registeredUsersDate = registedUsersLabel;
    const registeredUserDataLabel = registeredUsersDate.substring(1,registeredUsersDate.length-1).split(',');
    console.log(registeredUserDataLabel);
    let dashboardAllTimeUsersChart = document.getElementById("dashboard-all-time-users-chart").getContext('2d');
    let myChart = new Chart(dashboardAllTimeUsersChart, {
        type: 'line',
        data: {
            labels: registeredUserDataLabel,
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
                // position:'centre'
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