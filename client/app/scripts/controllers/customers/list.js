'use strict';

angular.module('clientApp')
        .controller("CustomerListCtrl", function ($scope, $route, $routeParams, $location, Restangular) {
            $scope.errorNotFound = $routeParams.errorNotFound;

            Restangular.all("customers").getList().then(function (entities) {
                $scope.entities = entities;
            });

            $scope.delete = function (entity) {
                entity.remove().then(function () {
                    // remove search parameters
                    $location.url($location.path());
                    $route.reload();
                }, function (response) {
                    $scope.error = response.data.error;
                });
            };
        });
