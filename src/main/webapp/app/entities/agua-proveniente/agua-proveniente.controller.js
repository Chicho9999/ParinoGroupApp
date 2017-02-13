(function() {
    'use strict';

    angular
        .module('parinoGroupApp')
        .controller('Agua_provenienteController', Agua_provenienteController);

    Agua_provenienteController.$inject = ['$scope', '$state', 'Agua_proveniente'];

    function Agua_provenienteController ($scope, $state, Agua_proveniente) {
        var vm = this;

        vm.agua_provenientes = [];

        loadAll();

        function loadAll() {
            Agua_proveniente.query(function(result) {
                vm.agua_provenientes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
