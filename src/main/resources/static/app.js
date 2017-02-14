var appModule = angular.module('myApp', []);

appModule.controller('MainCtrl', ['$scope', '$http',
        function($scope, $http) {
            $http.get('/pi').then(function(response) {
                $scope.pi = response.data;
            });
        } ]);

