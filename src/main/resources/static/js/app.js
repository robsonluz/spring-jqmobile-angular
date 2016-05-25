var app = angular.module('app', ['ngResource']);

//Interceptor de autenticação
app.factory('authInterceptorService', ['$q', function ($q){
    return {
        responseError: function (rejection) {
            if (rejection.status === 401) {
                location.href = "login.html";
            }
            return $q.reject(rejection);
        }
    };
}]);
app.config(['$httpProvider', function($httpProvider) {
    $httpProvider.interceptors.push('authInterceptorService');
}]);


//LoginController
app.controller('LoginController', function($scope, $http, $httpParamSerializer) {
	$scope.usuario = {};
	
	$scope.login = function() {
		$http({
		  url: '/login',
		  method: 'POST',
		  data: $httpParamSerializer({
				email: $scope.usuario.email,
				senha: $scope.usuario.senha
		  }),
		  headers: {
		    'Content-Type': 'application/x-www-form-urlencoded'
		  }
		}).success(function(response) {  
			//Login com sucesso
			location.href = "noticia.html";
		}).error(function(response){
			//Login inválido
			$scope.message = "Login e/ou senha inválidos!";
		});		
	}
});


//NoticiaService
app.factory('NoticiaService', function($resource) {
	return $resource('/api/noticias/:id', {}, {});
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
    	//location.href = "noticia.html";
    }	
});

