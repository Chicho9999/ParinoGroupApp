(function() {
    'use strict';

    angular
        .module('parinoGroupApp')
        .controller('Agua_provenienteDialogController', Agua_provenienteDialogController);

    Agua_provenienteDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Agua_proveniente'];

    function Agua_provenienteDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Agua_proveniente) {
        var vm = this;

        vm.agua_proveniente = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.agua_proveniente.id !== null) {
                Agua_proveniente.update(vm.agua_proveniente, onSaveSuccess, onSaveError);
            } else {
                Agua_proveniente.save(vm.agua_proveniente, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('parinoGroupApp:agua_provenienteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
