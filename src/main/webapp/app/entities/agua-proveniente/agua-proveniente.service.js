(function() {
    'use strict';
    angular
        .module('parinoGroupApp')
        .factory('Agua_proveniente', Agua_proveniente);

    Agua_proveniente.$inject = ['$resource'];

    function Agua_proveniente ($resource) {
        var resourceUrl =  'api/agua-provenientes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
