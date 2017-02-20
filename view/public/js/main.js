angular.module('leilao', ['minhasDiretivas','ngAnimate', 'ngRoute', 'ngResource'])
	.config(function($routeProvider, $locationProvider) {

		$locationProvider.html5Mode(true);

		$routeProvider.when('/itens', {
			templateUrl: 'partials/principal.html',
			controller: 'ItensController'
		});

		$routeProvider.when('/itens/cadastro', {
			templateUrl: 'partials/cadastroItem.html',
			controller: 'ItemController'
		});

		$routeProvider.when('/lance/:item', {
			templateUrl: 'partials/lance.html',
			controller: 'LanceController'
		});

		$routeProvider.otherwise({redirectTo: '/itens'});

	});