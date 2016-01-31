'use strict';

angular.module('clientApp')
        .controller('CustomerEditCtrl', function ($scope, $routeParams, $location, Restangular) {
            // as in http://stackoverflow.com/a/26676972/1399395
            $scope.$location = $location;

            $scope.errorNotFound = $routeParams.errorNotFound;

            $scope.save = function () {
                $scope.entity.save().then(function () {
                    $location.path("/customers");
                }, function (response) {
                    $scope.validationErrors = response.data.validationErrors;
                    $scope.error = response.data.error;
                });
            };

            $scope.initEntity = function () {
                if ($routeParams.id === "new") {
                    $scope.entity = Restangular.one("customers");
                    $scope.entity.employmentStatus = 'Unemployed';
                }
                else {
                    Restangular.one("customers", $routeParams.id).get().then(function (entity) {
                        $scope.initPayments();
                        $scope.entity = entity;
                    }, function (response) {
                        $location.path("/customers").search({errorNotFound: $routeParams.id});
                    });
                }
            };

            $scope.initPayments = function () {
                Restangular.one("customers", $routeParams.id).all("payments").getList().then(function (payments) {
                    $scope.entity.payments = payments;
                });
            }

            $scope.deleteEntity = function () {
                $scope.entity.remove().then(function () {
                    $location.path("/customers");
                }, function (response) {
                    $scope.error = response.data.error;
                });
            };

            $scope.changeEmploymentStatus = function () {
                if ($scope.entity.employmentStatus === "Unemployed") {
                    $scope.entity.companyName = null;
                }
            };

            $scope.deletePayment = function (payment) {
                payment.remove().then(function () {
                    $scope.initPayments();
                }, function (response) {
                    $scope.error = response.data.error;
                });
            };

            $scope.initEntity();
        });
