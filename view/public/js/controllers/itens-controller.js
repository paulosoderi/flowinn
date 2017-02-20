angular.module('leilao').controller('ItensController', function ($scope, $http) {
	$scope.itens = [];
	$scope.filtro = '';
	$scope.mensagem = '';
	
	$scope.remover = function (item) {
		console.log(item);
		console.log(item.id);
		$http.post('http://localhost:8080/api/v1/item', item.id)
			.success(function (retorno) {
				console.log(retorno);
				var indiceDoItem = $scope.itens.indexOf(item);
				$scope.itens.splice(indiceDoItem, 1);
				$scope.itens = retorno;
				$scope.mensagem = 'Item ' + item.descricao + ' removido com sucesso!';
			})
			.error(function (erro) {
				console.log(erro);
				$scope.mensagem = 'Não foi possível apagar o item ' + item.descricao;
			})
	}
	
	$http.get('http://localhost:8080/api/v1/itens')
		.success(function (retorno) {
			console.log(retorno);
			$scope.itens = retorno; // não precisa fazer retorno.data
		})
		.error(function (erro) {
			console.log(erro);
		});
});