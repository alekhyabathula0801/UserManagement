function getAgeChart(age) {
    var dashboardAgeChart = document.getElementById("dashboard-age-chart").getContext('2d');
    var myChart = new Chart(dashboardAgeChart, {
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