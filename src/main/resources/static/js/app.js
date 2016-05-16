var app = angular.module('app', ['ngResource']);

/**
 * NoticiaService - Responsável pela comunicação 
 * com a API JSON de noticias 
 */
app.factory('NoticiaService', function($resource) {
	return $resource('/noticias/:id', {}, {});
});

app.controller('NoticiaListController', function($scope, NoticiaService) {
	$scope.noticias = NoticiaService.query();	
});

app.controller('NoticiaShowController', function($scope, NoticiaService) {
	var id = getParameterByName("id");
	if(id) {
		$scope.noticia = NoticiaService.get({"id": id});
	}
	console.log('chegou aqui neste campo: ' + id);	
});

app.controller('NoticiaFormController', function($scope, NoticiaService) {
	var id = getParameterByName("id");
	if(id) {
		$scope.noticia = NoticiaService.get({"id": id});
	}else{
		$scope.noticia = {};//Utilizado pelo formulario de nova noticia
	}

	$scope.save = function() {
		NoticiaService.save($scope.noticia); //Chama o service para salvar
    	location.href="index.html";
    }	
});


