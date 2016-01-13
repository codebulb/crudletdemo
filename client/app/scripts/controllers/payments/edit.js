'use strict';

angular.module('clientApp')
        .controller('PaymentEditCtrl', function ($scope, $routeParams, $location, $filter, Restangular) {
            // as in http://stackoverflow.com/a/26676972/1399395
            $scope.$location = $location;

            $scope.save = function () {
                $scope.entity.post().then(function () {
                    $location.path("/customers/" + $routeParams.customerId);
                }, function (response) {
                    $scope.validationErrors = response.data.validationErrors;
                    $scope.error = response.data.error;
                });
            };

            $scope.initEntity = function () {
                $scope.customerId = $routeParams.customerId;
                if ($routeParams.id === "new") {
                    $scope.entity = Restangular.one("customers", $routeParams.customerId).one("payments");
                }
                else {
                    Restangular.one("customers", $routeParams.customerId).one("payments", $routeParams.id).get().then(function (entity) {
                        entity.date = new Date($filter('date')(entity.date, "yyyy-MM-dd"));
                        $scope.entity = entity;
                    }, function (response) {
                        $location.path("/customers/" + $routeParams.customerId).search({errorNotFound: $routeParams.id});
                    });
                }
            };

            $scope.deleteEntity = function () {
                $scope.entity.remove().then(function () {
                    $location.path("/customers/" + $routeParams.customerId);
                }, function (response) {
                    $scope.error = response.data.error;
                });
            };

            $scope.initEntity();
        });
