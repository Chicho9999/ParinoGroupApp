(function() {
    'use strict';

    angular
        .module('parinoGroupApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('agua-proveniente', {
            parent: 'entity',
            url: '/agua-proveniente',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Agua_provenientes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/agua-proveniente/agua-provenientes.html',
                    controller: 'Agua_provenienteController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('agua-proveniente-detail', {
            parent: 'agua-proveniente',
            url: '/agua-proveniente/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Agua_proveniente'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/agua-proveniente/agua-proveniente-detail.html',
                    controller: 'Agua_provenienteDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Agua_proveniente', function($stateParams, Agua_proveniente) {
                    return Agua_proveniente.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'agua-proveniente',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('agua-proveniente-detail.edit', {
            parent: 'agua-proveniente-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agua-proveniente/agua-proveniente-dialog.html',
                    controller: 'Agua_provenienteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Agua_proveniente', function(Agua_proveniente) {
                            return Agua_proveniente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('agua-proveniente.new', {
            parent: 'agua-proveniente',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agua-proveniente/agua-proveniente-dialog.html',
                    controller: 'Agua_provenienteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                descripcion: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('agua-proveniente', null, { reload: 'agua-proveniente' });
                }, function() {
                    $state.go('agua-proveniente');
                });
            }]
        })
        .state('agua-proveniente.edit', {
            parent: 'agua-proveniente',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agua-proveniente/agua-proveniente-dialog.html',
                    controller: 'Agua_provenienteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Agua_proveniente', function(Agua_proveniente) {
                            return Agua_proveniente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('agua-proveniente', null, { reload: 'agua-proveniente' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('agua-proveniente.delete', {
            parent: 'agua-proveniente',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/agua-proveniente/agua-proveniente-delete-dialog.html',
                    controller: 'Agua_provenienteDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Agua_proveniente', function(Agua_proveniente) {
                            return Agua_proveniente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('agua-proveniente', null, { reload: 'agua-proveniente' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
