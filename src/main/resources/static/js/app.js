var app = angular.module('app', ['ngResource']);

//NoticiaService
app.factory('NoticiaService', function($resource) {
	return $resource('/noticias/:id', {}, {
		aprovar: { 
			method: 'POST',
			url: '/noticias/:id/aprovar',
			params: { id: "@id" }
		}		
	});
});

//NoticiaListController
app.controller('NoticiaListController', function($scope, NoticiaService) {
	$scope.noticias = NoticiaService.query();	
});

//NoticiaShowController
app.controller('NoticiaShowController', function($scope, NoticiaService) {
	var id = getParameterByName("id");
	if(id) {
		$scope.noticia = NoticiaService.get({"id": id});
	}
	$scope.remove = function() {
    	NoticiaService.remove($scope.noticia, function(){
    		location.href = "noticia.html";
    	});
    }
	$scope.aprovar = function() {
    	$scope.noticia = NoticiaService.aprovar($scope.noticia);
    }	
});

//NoticiaFormController
app.controller('NoticiaFormController', function($scope, NoticiaService) {
	var id = getParameterByName("id");
	if(id) {
		$scope.noticia = NoticiaService.get({"id": id}); //Edicao
	}else{
		$scope.noticia = {}; //Nova
	}

	$scope.save = function() {
		NoticiaService.save($scope.noticia);
    	location.href = "noticia.html";
    }	
});

