

  //$.mobile.ajaxEnabled = false;

var app = angular.module('app', ['ngResource', 'ngRoute']);





/**
 * Configuração das Rotas (páginas do sistema)
 */
app.config(['$routeProvider', function($routerProvider){
	$routerProvider
		.when('/', {
			templateUrl: 'menu.html'
		})
		
		.when('/noticias', {
			templateUrl: 'noticias-list.html',
			controller: 'NoticiaController'
		})
	;
}]);


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
	
});


app.run(['$rootScope', function($rootScope){
	$rootScope.$on("$routeChangeSuccess", function(currentRoute, previousRoute){
	    
	    //$rootScope.title = $route.current.title;
		console.log('carregou');

	});
	//$.mobile.initializePage();
	setTimeout(function(){
		$.mobile.initializePage();
	}, 10);
}]);
