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
app.controller('NoticiaController', function(NoticiaService) {
	//Chama o serviço /noticias e guarda na propriedade noticias
	this.noticias = NoticiaService.query();
});