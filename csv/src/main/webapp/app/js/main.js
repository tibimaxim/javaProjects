/**
 * Main AngularJS Web Application
 */
var app = angular.module('app', [
  'ngRoute'
]);
/**
 * Configure the Routes
 */
app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Home
    .when("/", {templateUrl: "partials/csv.html", controller: "PageCtrl"})
    // Pages
    .when("/search", {templateUrl: "partials/search.html", controller: "SearchCtrl"})
    .when("/client/:id", {templateUrl: "partials/client.html", controller: "ClientCtrl"})
    .when("/create", {templateUrl: "partials/client.html", controller: "AddClientCtrl"})
    .when("/services", {templateUrl: "partials/services.html", controller: "PageCtrl"})
    .when("/contact", {templateUrl: "partials/contact.html", controller: "PageCtrl"})
    // Blog
    .when("/blog", {templateUrl: "partials/blog.html", controller: "BlogCtrl"})
    .when("/blog/post", {templateUrl: "partials/blog_item.html", controller: "BlogCtrl"})
    .otherwise({redirectTo: '/'});
}]);


/**
 * Controls all other Pages
 */
app.controller('PageCtrl', function (/* $scope, $location, $http */) {
  // Activates the Carousel
  $('.carousel').carousel({
    interval: 5000
  });
  

  
  
});