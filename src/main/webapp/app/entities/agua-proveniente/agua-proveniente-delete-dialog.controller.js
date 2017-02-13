(function() {
    'use strict';

    angular
        .module('parinoGroupApp')
        .controller('Agua_provenienteDeleteController',Agua_provenienteDeleteController);

    Agua_provenienteDeleteController.$inject = ['$uibModalInstance', 'entity', 'Agua_proveniente'];

    function Agua_provenienteDeleteController($uibModalInstance, entity, Agua_proveniente) {
        var vm = this;

        vm.agua_proveniente = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Agua_proveniente.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
