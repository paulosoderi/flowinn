angular.module('leilao').controller('LanceController', function ($scope, $http, $routeParams) {
	
	$scope.item =angular.fromJson( $routeParams.item);
	$scope.valorLance = $scope.item.valorAtual + $scope.item.valorDefaultIncremento;
	
	$scope.lance = {
		idItem: $scope.item.id,
		valorLance : null
	}

	$scope.submeter = function() {
			if ($scope.formulario.$valid) {
				console.log($scope.item);
				$http.post('http://localhost:8080/api/v1/item/licitar', $scope.lance)
                .success(function() {
                    $scope.lance.valorLance = null;
                    $scope.mensagem = 'Lance efetuado com sucesso';
                })
                .error(function(erro) {
                    console.log(erro);
                    $scope.mensagem = 'Não foi possível dar o lance - ' + erro.message; 
                })
			}
		}

});
 