angular.module('leilao').controller('ItemController', function($scope,$http) {
	


		$scope.item = 	{
			"dataFechamento": null,
			"descricao": null,
			"valorDefaultIncremento": null,
			"valorInicial": null
			}

		$scope.mensagem = '';

		$scope.submeter = function() {
			if ($scope.formulario.$valid) {
				console.log($scope.item);
				$http.post('http://localhost:8080/api/v1/item', $scope.item)
                .success(function() {
                    $scope.item = {};
                    $scope.mensagem = 'Item cadastrado com sucesso';
                })
                .error(function(erro) {
                    console.log(erro);
                    $scope.mensagem = 'Não foi possível cadastrar o item';
                })
			}
		}

	});