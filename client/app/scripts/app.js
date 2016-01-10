'use strict';

angular
        .module('clientApp', [
            'ngCookies',
            'ngResource',
            'ngRoute',
            'ngSanitize',
            'restangular',
            'pascalprecht.translate',
        ])
        .config(['$httpProvider', function ($httpProvider) {
                $httpProvider.defaults.useXDomain = true;
                $httpProvider.defaults.headers.common['X-Requested-With'];
            }
        ])
        .config(function ($routeProvider) {
            $routeProvider
                    .when('/customers', {
                        templateUrl: 'views/customers/list.html',
                        controller: 'CustomerListCtrl'
                    })
                    .when('/customers/:id', {
                        templateUrl: 'views/customers/edit.html',
                        controller: 'CustomerEditCtrl'
                    })
                    .when('/customers/:customerId/payments/:id', {
                        templateUrl: 'views/payments/edit.html',
                        controller: 'PaymentEditCtrl'
                    })
                    .otherwise({
                        redirectTo: '/customers'
                    });
        })
        .config(function (RestangularProvider) {
            RestangularProvider.setBaseUrl('http://localhost:8080/CrudletDemo.server/');
        })
        .config(['$translateProvider', function ($translateProvider) {
                $translateProvider.translations('en', translations);
                $translateProvider.preferredLanguage('en');
                $translateProvider.useMissingTranslationHandlerLog();
                $translateProvider.useSanitizeValueStrategy('sanitize');
            }])
        ;
