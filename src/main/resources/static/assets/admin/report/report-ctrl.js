app.controller("report-ctrl", function ($scope, $http,$location) {
    $scope.labels = [
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"
    ];
    $scope.data = [];
    var currentYear = new Date().getFullYear();
    $scope.options = Array.from({ length: 40  }, (v, i) => (currentYear - i).toString());

    $scope.selectedOption = $scope.options[0]; // Default selected option

    // Load data from URL parameter if available
    var initialYear = $location.search().year || $scope.selectedOption;
    $scope.selectedOption = initialYear;

    $scope.initialize = function (year) {
        $http.get(`/rest/orders/report/${year}`).then((resp) => {
            let data = resp.data;
            $scope.data = Array(12).fill(0); // Khởi tạo mảng 12 phần tử với giá trị 0

            // Cập nhật dữ liệu từ phản hồi
            for (let index = 0; index < data.length; index++) {
                $scope.data[index]=data[index][1]
            }

            // Đảm bảo biểu đồ được cập nhật
            console.log('Updated data:', $scope.data);
        }).catch((error) => {
            console.error('Error fetching data:', error);
        });
    };

    // Khởi tạo dữ liệu với năm mặc định hoặc từ URL
    $scope.initialize($scope.selectedOption);

    // Cập nhật dữ liệu và URL khi năm thay đổi
    $scope.updateData = function () {
        $scope.initialize($scope.selectedOption);
        $location.search('year', $scope.selectedOption); // Cập nhật URL với tham số query 'year'
    };

    $scope.label = "Số lượng order";
});
app.directive('chartjs', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var chart;
            scope.$watchGroup(['labels', 'data', 'label'], function (newValues) {
                if (chart) {
                    chart.destroy();  // Xóa biểu đồ cũ trước khi tạo mới
                }
                var ctx = element[0].getContext('2d');
                chart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: newValues[0],
                        datasets: [{
                            label: newValues[2],
                            data: newValues[1],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            }, true); // Thêm tham số 'true' để so sánh sâu (deep watch)
        }
    };
});
