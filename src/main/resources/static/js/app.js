var app = angular.module('app', ['ngResource']);

/**
 * NoticiaService - Responsável pela comunicação 
 * com a API JSON de noticias 
 */
app.factory('NoticiaService', function($resource) {
	return $resource('/noticias/:id', {}, {});
});

/**
 * NoticiaController: Controller para as telas de noticías
 */
app.controller('NoticiaController', function($scope, NoticiaService) {
	//Chama o serviço /noticias e guarda na propriedade noticias
	$scope.noticias = NoticiaService.query();
	
	//Utilizado pelo formulario de nova noticia
	$scope.noticia = {};
	
	$scope.save = function() {
    		//Chama o service para salvar
		NoticiaService.save(this.noticia, function(){
    			//atualiza as noticias
    			$scope.noticias = NoticiaService.query(); 
    		});
    		//Volta para a tela anterior
    		window.history.back();
    }	
});

