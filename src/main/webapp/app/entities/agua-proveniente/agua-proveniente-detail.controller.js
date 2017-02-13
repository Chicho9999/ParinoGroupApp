(function() {
    'use strict';

    angular
        .module('parinoGroupApp')
        .controller('Agua_provenienteDetailController', Agua_provenienteDetailController);

    Agua_provenienteDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Agua_proveniente'];

    function Agua_provenienteDetailController($scope, $rootScope, $stateParams, previousState, entity, Agua_proveniente) {
        var vm = this;

        vm.agua_proveniente = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('parinoGroupApp:agua_provenienteUpdate', function(event, result) {
            vm.agua_proveniente = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
