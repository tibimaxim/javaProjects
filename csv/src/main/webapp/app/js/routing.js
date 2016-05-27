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
    .when("/logout", {templateUrl: "partials/logout.html", controller: "LogoutCtrl"})
    .when("/accounts", {templateUrl: "partials/accounts.html", controller: "AccountsCtrl"})
    .when("/account/:id", {templateUrl: "partials/account.html", controller: "AccountCtrl"})
    .when("/adaugare", {templateUrl: "partials/addAccount.html", controller: "AddAccountCtrl"})
    .when("/myAccount", {templateUrl: "partials/account.html", controller: "MyAccountCtrl"})
    .otherwise({redirectTo: '/search'});
}]);

