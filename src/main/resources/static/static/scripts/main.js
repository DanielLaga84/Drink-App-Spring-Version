let app = angular.module('agendaEditor', []);

app.controller('formCtrl', function ($scope) {
    $scope.list = [];

    $scope.list.push({ingredient: "", measure: ""});

    $scope.myResult = function () {
        let result = [];
        for (let i = 0; i < $scope.list.length; i++) {
            let concat = "„" + $scope.list[i].ingredient + "” – " + " " + $scope.list[i].measure;
            if ($scope.list[i].title !== "") {
                result.push(concat);
            }
        }
        return result;
    };

    $scope.addItem = function () {
        $scope.list.push({ingredient: "", measure: ""});
    };
});